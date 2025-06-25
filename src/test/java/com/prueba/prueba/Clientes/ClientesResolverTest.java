package com.prueba.prueba.Clientes;

import com.prueba.prueba.Utilities.PasswordEncryptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class ClientesResolverTest {

    @Mock
    private ClientesRepositorio clientesRepositorio;

    @Mock
    private TipodocumentoRepositorio tipodocumentoRepositorio;

    @Mock
    private PasswordEncryptor passwordEncryptor;

    @InjectMocks
    private ClientesResolver clientesResolver;

    private Tipodocumento tipoDocumento;
    private Clientes cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tipoDocumento = new Tipodocumento();
        tipoDocumento.setIdTipoDocumento(1);
        tipoDocumento.setNombre("Cédula");

        cliente = new Clientes();
        cliente.setId_cliente(1);
        cliente.setIdTipoDocumento(tipoDocumento);
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setNumeroDocumento("123456789");
        cliente.setCorreoElectronico("juan.perez@example.com");
        cliente.setContrasenaHash("hashedPassword");
        cliente.setDireccion("Calle 123");
        cliente.setTelefono("555-1234");
    }

    @Test
    void listaClientes_debeRetornarListaDeClientes() {
        when(clientesRepositorio.findAll()).thenReturn(Arrays.asList(cliente));

        List<Clientes> resultado = clientesResolver.listaClientes();

        assertEquals(1, resultado.size());
        assertEquals("Juan", resultado.get(0).getNombre());
    }

    @Test
    void buscaCliente_conIdExistente_debeRetornarCliente() {
        when(clientesRepositorio.findById(1)).thenReturn(Optional.of(cliente));

        Clientes resultado = clientesResolver.buscaCliente(1);

        assertEquals("Juan", resultado.getNombre());
    }

    @Test
    void buscaCliente_conIdNoExistente_debeLanzarExcepcion() {
        when(clientesRepositorio.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> clientesResolver.buscaCliente(1));
    }

    @Test
    void insertarCliente_conDatosValidos_debeInsertarCliente() {
        ClientesResolver.ClientesInput input = new ClientesResolver.ClientesInput(
                1, "Maria", "Gomez", "987654321", "maria.gomez@example.com", "password", "Av. 456", "555-5678");

        when(tipodocumentoRepositorio.findById(1)).thenReturn(Optional.of(tipoDocumento));
        when(passwordEncryptor.encrypt("password")).thenReturn("hashedPassword");
        when(clientesRepositorio.save(any(Clientes.class))).thenReturn(cliente);

        Clientes resultado = clientesResolver.insertarCliente(input);

        assertEquals("Juan", resultado.getNombre());
        verify(clientesRepositorio).save(any(Clientes.class));
    }

    @Test
    void insertarCliente_conTipoDocumentoNoExistente_debeLanzarExcepcion() {
        ClientesResolver.ClientesInput input = new ClientesResolver.ClientesInput(
                99, "Maria", "Gomez", "987654321", "maria.gomez@example.com", "password", "Av. 456", "555-5678");

        when(tipodocumentoRepositorio.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> clientesResolver.insertarCliente(input));
    }

    @Test
    void insertarCliente_conEmailInvalido_debeLanzarExcepcion() {
        ClientesResolver.ClientesInput input = new ClientesResolver.ClientesInput(
                1, "Maria", "Gomez", "987654321", "emailinvalido", "password", "Av. 456", "555-5678");

        assertThrows(RuntimeException.class, () -> clientesResolver.insertarCliente(input));
    }

    @Test
    void deleteCliente_conIdExistente_debeEliminarCliente() {
        doNothing().when(clientesRepositorio).deleteById(1);

        Boolean resultado = clientesResolver.deleteCliente(1);

        assertTrue(resultado);
        verify(clientesRepositorio).deleteById(1);
    }

    @Test
    void updateCliente_conDatosValidos_debeActualizarCliente() {
        ClientesResolver.ClientesInput input = new ClientesResolver.ClientesInput(
                1, "Maria", "Gomez", "987654321", "maria.gomez@example.com", "newpassword", "Av. 456", "555-5678");

        when(clientesRepositorio.findById(1)).thenReturn(Optional.of(cliente));
        when(tipodocumentoRepositorio.findById(1)).thenReturn(Optional.of(tipoDocumento));
        when(passwordEncryptor.encrypt("newpassword")).thenReturn("newHashedPassword");
        when(clientesRepositorio.save(any(Clientes.class))).thenReturn(cliente);

        Clientes resultado = clientesResolver.updateCliente(1, input);

        assertEquals("Juan", resultado.getNombre());
        verify(clientesRepositorio).save(any(Clientes.class));
    }

    @Test
    void updateCliente_conIdNoExistente_debeLanzarExcepcion() {
        ClientesResolver.ClientesInput input = new ClientesResolver.ClientesInput(
                1, "Maria", "Gomez", "987654321", "maria.gomez@example.com", "newpassword", "Av. 456", "555-5678");

        when(clientesRepositorio.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> clientesResolver.updateCliente(1, input));
    }

    @Test
    void updateClienteProfile_conDatosValidos_debeActualizarPerfil() {
        ClientesResolver.ClientesUpdateInput input = new ClientesResolver.ClientesUpdateInput(
                1, "Ana", "Lopez", "987654321", "ana.lopez@example.com", "Av. 789", "555-9012");

        when(clientesRepositorio.findById(1)).thenReturn(Optional.of(cliente));
        when(tipodocumentoRepositorio.findById(1)).thenReturn(Optional.of(tipoDocumento));
        when(clientesRepositorio.save(any(Clientes.class))).thenReturn(cliente);

        Clientes resultado = clientesResolver.updateClienteProfile(1, input);

        assertEquals("Juan", resultado.getNombre()); // El nombre no se actualiza en este test
        verify(clientesRepositorio).save(any(Clientes.class));
    }

    @Test
    void updateClienteProfile_conIdNoExistente_debeLanzarExcepcion() {
        ClientesResolver.ClientesUpdateInput input = new ClientesResolver.ClientesUpdateInput(
                1, "Ana", "Lopez", "987654321", "ana.lopez@example.com", "Av. 789", "555-9012");

        when(clientesRepositorio.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> clientesResolver.updateClienteProfile(1, input));
    }

    @Test
    void validarLogin_conCredencialesCorrectas_debeRetornarTrue() {
        when(clientesRepositorio.findByCorreoElectronico("juan.perez@example.com")).thenReturn(Optional.of(cliente));
        when(passwordEncryptor.matches("password", "hashedPassword")).thenReturn(true);

        boolean resultado = clientesResolver.validarLogin("juan.perez@example.com", "password");

        assertTrue(resultado);
    }

    @Test
    void validarLogin_conCredencialesIncorrectas_debeRetornarFalse() {
        when(clientesRepositorio.findByCorreoElectronico("juan.perez@example.com")).thenReturn(Optional.of(cliente));
        when(passwordEncryptor.matches("wrongpassword", "hashedPassword")).thenReturn(false);

        boolean resultado = clientesResolver.validarLogin("juan.perez@example.com", "wrongpassword");

        assertFalse(resultado);
    }

    @Test
    void validarLogin_conUsuarioNoExistente_debeRetornarFalse() {
        when(clientesRepositorio.findByCorreoElectronico(any())).thenReturn(Optional.empty());

        boolean resultado = clientesResolver.validarLogin("nonexistent@example.com", "password");

        assertFalse(resultado);
    }
}