package com.prueba.prueba.Clientes;

import com.prueba.prueba.Agentes.Agentes;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

import java.util.List;
import java.util.regex.Pattern;
import com.prueba.prueba.Utilities.PasswordEncryptor;

@Controller

public class ClientesResolver {
    private final ClientesRepositorio clientesRepositorio;
    private final PasswordEncryptor passwordEncryptor;

    private final TipodocumentoRepositorio tipodocumentoRepositorio;

    // Patrón para validar email
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    public ClientesResolver(ClientesRepositorio clientesRepositorio, TipodocumentoRepositorio tipodocumentoRepositorio,
            PasswordEncryptor passwordEncryptor) {
        this.clientesRepositorio = clientesRepositorio;
        this.tipodocumentoRepositorio = tipodocumentoRepositorio;
        this.passwordEncryptor = passwordEncryptor;

    }

    @SchemaMapping(typeName = "Clientes", field = "idTipoDocumento")
    public Tipodocumento resolverTipoDocumento(Clientes cliente) {
        return cliente.getIdTipoDocumento();
    }

    @QueryMapping
    public List<Clientes> listaClientes() {
        return clientesRepositorio.findAll();
    }

    @QueryMapping
    public Clientes buscaCliente(@Argument Integer id_cliente) {
        return clientesRepositorio.findById(id_cliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public record ClientesInput(
            Integer idTipoDocumento,
            String nombre,
            String apellido,
            String numeroDocumento,
            String correoElectronico,
            String contrasenaHash,
            String direccion,
            String telefono) {
    }

    // Nuevo record para actualizaciones de perfil (sin contraseña)
    public record ClientesUpdateInput(
            Integer idTipoDocumento,
            String nombre,
            String apellido,
            String numeroDocumento,
            String correoElectronico,
            String direccion,
            String telefono) {
    }

    private void validarEmail(String email) {
        if (email != null) {
            String emailTrimmed = email.trim();
            if (!EMAIL_PATTERN.matcher(emailTrimmed).matches()) {
                throw new RuntimeException("El formato del email es inválido: " + email);
            }
        }
    }

    private String limpiarTexto(String texto) {
        return texto != null ? texto.trim() : null;
    }

    @MutationMapping(name = "insertarCliente")
    public Clientes insertarCliente(@Argument ClientesInput clientesInput) {
        // Validar email
        validarEmail(clientesInput.correoElectronico());

        Tipodocumento tipoDoc = tipodocumentoRepositorio.findById(clientesInput.idTipoDocumento())
                .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado"));

        Clientes cliente = new Clientes();
        cliente.setIdTipoDocumento(tipoDoc);
        cliente.setNombre(limpiarTexto(clientesInput.nombre()));
        cliente.setApellido(limpiarTexto(clientesInput.apellido()));
        cliente.setNumeroDocumento(limpiarTexto(clientesInput.numeroDocumento()));
        cliente.setCorreoElectronico(limpiarTexto(clientesInput.correoElectronico()));
        cliente.setContrasenaHash(passwordEncryptor.encrypt(clientesInput.contrasenaHash()));
        cliente.setDireccion(limpiarTexto(clientesInput.direccion()));
        cliente.setTelefono(limpiarTexto(clientesInput.telefono()));

        return clientesRepositorio.save(cliente);
    }

    @MutationMapping
    public Boolean deleteCliente(@Argument Integer id_cliente) {
        clientesRepositorio.deleteById(id_cliente);
        return true;
    }

    @MutationMapping
    public Clientes updateCliente(@Argument Integer id_cliente, @Argument ClientesInput clientesInput) {
        // Validar email
        validarEmail(clientesInput.correoElectronico());

        Clientes cliente = clientesRepositorio.findById(id_cliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Tipodocumento tipoDoc = tipodocumentoRepositorio.findById(clientesInput.idTipoDocumento())
                .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado"));

        cliente.setIdTipoDocumento(tipoDoc);
        cliente.setNombre(limpiarTexto(clientesInput.nombre()));
        cliente.setApellido(limpiarTexto(clientesInput.apellido()));
        cliente.setNumeroDocumento(limpiarTexto(clientesInput.numeroDocumento()));
        cliente.setCorreoElectronico(limpiarTexto(clientesInput.correoElectronico()));
        cliente.setContrasenaHash(passwordEncryptor.encrypt(clientesInput.contrasenaHash()));
        cliente.setDireccion(limpiarTexto(clientesInput.direccion()));
        cliente.setTelefono(limpiarTexto(clientesInput.telefono()));

        return clientesRepositorio.save(cliente);
    }

    // Nuevo método para actualizar perfil sin cambiar contraseña
    @MutationMapping
    public Clientes updateClienteProfile(@Argument Integer id_cliente,
            @Argument ClientesUpdateInput clientesUpdateInput) {
        Clientes cliente = clientesRepositorio.findById(id_cliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Actualizar solo los campos proporcionados (null-safe)
        if (clientesUpdateInput.idTipoDocumento() != null) {
            Tipodocumento tipoDoc = tipodocumentoRepositorio.findById(clientesUpdateInput.idTipoDocumento())
                    .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado"));
            cliente.setIdTipoDocumento(tipoDoc);
        }

        if (clientesUpdateInput.nombre() != null) {
            cliente.setNombre(limpiarTexto(clientesUpdateInput.nombre()));
        }

        if (clientesUpdateInput.apellido() != null) {
            cliente.setApellido(limpiarTexto(clientesUpdateInput.apellido()));
        }

        if (clientesUpdateInput.numeroDocumento() != null) {
            cliente.setNumeroDocumento(limpiarTexto(clientesUpdateInput.numeroDocumento()));
        }

        if (clientesUpdateInput.correoElectronico() != null) {
            // Validar email antes de actualizar
            validarEmail(clientesUpdateInput.correoElectronico());
            cliente.setCorreoElectronico(limpiarTexto(clientesUpdateInput.correoElectronico()));
        }

        if (clientesUpdateInput.direccion() != null) {
            cliente.setDireccion(limpiarTexto(clientesUpdateInput.direccion()));
        }

        if (clientesUpdateInput.telefono() != null) {
            cliente.setTelefono(limpiarTexto(clientesUpdateInput.telefono()));
        }

        // La contraseña NO se modifica
        return clientesRepositorio.save(cliente);
    }

    public boolean validarLogin(String correoElectronico, String contrasenaHash) {
        Clientes clientes = clientesRepositorio.findByCorreoElectronico(correoElectronico).orElse(null);
        if (clientes == null)
            return false;
        try {
            return passwordEncryptor.matches(contrasenaHash, clientes.getContrasenaHash());
        } catch (Exception e) {
            return false;
        }
    }

    @MutationMapping(name = "LoginCliente")
    public Boolean LoginCliente(@Argument String correoElectronico, @Argument String contrasenaHash) {
        return validarLogin(correoElectronico, contrasenaHash);
    }

}
