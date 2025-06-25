package com.prueba.prueba.Clientes;

import com.prueba.prueba.cliente.Cliente;
import com.prueba.prueba.cliente.ClienteRepositorio;
import com.prueba.prueba.cliente.ClienteResolver;
import com.prueba.prueba.cliente.Tipodocumento;
import com.prueba.prueba.cliente.TipodocumentoRepositorio;
import com.prueba.prueba.utilities.PasswordEncryptor;

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
    private ClienteRepositorio clientesRepositorio;

    @Mock
    private TipodocumentoRepositorio tipodocumentoRepositorio;

    @Mock
    private PasswordEncryptor passwordEncryptor;

    @InjectMocks
    private ClienteResolver clientesResolver;

    private Tipodocumento tipoDocumento;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tipoDocumento = new Tipodocumento();
        tipoDocumento.setIdTipoDocumento(1);
        tipoDocumento.setNombre("Cédula");

        cliente = new Cliente();
        cliente.setIdCliente(1);
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

        List<Cliente> resultado = clientesResolver.listaClientes();

        assertEquals(1, resultado.size());
        assertEquals("Juan", resultado.get(0).getNombre());
    }

    @Test
    void buscaCliente_conIdExistente_debeRetornarCliente() {
        when(clientesRepositorio.findById(1)).thenReturn(Optional.of(cliente));

        Cliente resultado = clientesResolver.buscaCliente(1);

        assertEquals("Juan", resultado.getNombre());
    }

    @Test
    void buscaCliente_conIdNoExistente_debeLanzarExcepcion() {
        when(clientesRepositorio.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> clientesResolver.buscaCliente(1));
    }

    @Test
    void insertarCliente_conDatosValidos_debeInsertarCliente() {
        ClienteResolver.ClientesInput input = new ClienteResolver.ClientesInput(
                1, "Maria", "Gomez", "987654321", "maria.gomez@example.com", "password", "Av. 456", "555-5678");

        when(tipodocumentoRepositorio.findById(1)).thenReturn(Optional.of(tipoDocumento));
        when(passwordEncryptor.encrypt("password")).thenReturn("hashedPassword");
        when(clientesRepositorio.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = clientesResolver.insertarCliente(input);

        assertEquals("Juan", resultado.getNombre());
        verify(clientesRepositorio).save(any(Cliente.class));
    }

    @Test
    void insertarCliente_conTipoDocumentoNoExistente_debeLanzarExcepcion() {
        ClienteResolver.ClientesInput input = new ClienteResolver.ClientesInput(
                99, "Maria", "Gomez", "987654321", "maria.gomez@example.com", "password", "Av. 456", "555-5678");

        when(tipodocumentoRepositorio.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> clientesResolver.insertarCliente(input));
    }

    @Test
    void insertarCliente_conEmailInvalido_debeLanzarExcepcion() {
        ClienteResolver.ClientesInput input = new ClienteResolver.ClientesInput(
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
        ClienteResolver.ClientesInput input = new ClienteResolver.ClientesInput(
                1, "Maria", "Gomez", "987654321", "maria.gomez@example.com", "newpassword", "Av. 456", "555-5678");

        when(clientesRepositorio.findById(1)).thenReturn(Optional.of(cliente));
        when(tipodocumentoRepositorio.findById(1)).thenReturn(Optional.of(tipoDocumento));
        when(passwordEncryptor.encrypt("newpassword")).thenReturn("newHashedPassword");
        when(clientesRepositorio.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = clientesResolver.updateCliente(1, input);

        assertEquals("Juan", resultado.getNombre());
        verify(clientesRepositorio).save(any(Cliente.class));
    }

    @Test
    void updateCliente_conIdNoExistente_debeLanzarExcepcion() {
        ClienteResolver.ClientesInput input = new ClienteResolver.ClientesInput(
                1, "Maria", "Gomez", "987654321", "maria.gomez@example.com", "newpassword", "Av. 456", "555-5678");

        when(clientesRepositorio.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> clientesResolver.updateCliente(1, input));
    }

    @Test
    void updateClienteProfile_conDatosValidos_debeActualizarPerfil() {
        ClienteResolver.ClientesUpdateInput input = new ClienteResolver.ClientesUpdateInput(
                1, "Ana", "Lopez", "987654321", "ana.lopez@example.com", "Av. 789", "555-9012");

        when(clientesRepositorio.findById(1)).thenReturn(Optional.of(cliente));
        when(tipodocumentoRepositorio.findById(1)).thenReturn(Optional.of(tipoDocumento));
        when(clientesRepositorio.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = clientesResolver.updateClienteProfile(1, input);

        assertEquals("Juan", resultado.getNombre()); // El nombre no se actualiza en este test
        verify(clientesRepositorio).save(any(Cliente.class));
    }

    @Test
    void updateClienteProfile_conIdNoExistente_debeLanzarExcepcion() {
        ClienteResolver.ClientesUpdateInput input = new ClienteResolver.ClientesUpdateInput(
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