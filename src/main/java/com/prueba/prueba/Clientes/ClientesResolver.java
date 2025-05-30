package com.prueba.prueba.Clientes;

import com.prueba.prueba.Agentes.Agentes;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

import java.util.List;
import com.prueba.prueba.Utilities.PasswordEncryptor;



@Controller

public class ClientesResolver {
    private  final  ClientesRepositorio clientesRepositorio;
    private final PasswordEncryptor passwordEncryptor;


    private final TipodocumentoRepositorio tipodocumentoRepositorio;

    public ClientesResolver(ClientesRepositorio clientesRepositorio, TipodocumentoRepositorio tipodocumentoRepositorio, PasswordEncryptor passwordEncryptor) {
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
        return  clientesRepositorio.findById(id_cliente).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public record ClientesInput(
            Integer idTipoDocumento,
            String nombre,
            String apellido,
            String numeroDocumento,
            String correoElectronico,
            String contrasenaHash,
            String direccion,
            String telefono
    ) {}

    @MutationMapping(name = "insertarCliente")
    public Clientes insertarCliente(@Argument ClientesInput clientesInput) {
        Tipodocumento tipoDoc = tipodocumentoRepositorio.findById(clientesInput.idTipoDocumento())
                .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado"));

        Clientes cliente = new Clientes();
        cliente.setIdTipoDocumento(tipoDoc);
        cliente.setNombre(clientesInput.nombre());
        cliente.setApellido(clientesInput.apellido());
        cliente.setNumeroDocumento(clientesInput.numeroDocumento());
        cliente.setCorreoElectronico(clientesInput.correoElectronico());
        cliente.setContrasenaHash(passwordEncryptor.encrypt(clientesInput.contrasenaHash()));
        cliente.setDireccion(clientesInput.direccion());
        cliente.setTelefono(clientesInput.telefono());

        return clientesRepositorio.save(cliente);
    }

    @MutationMapping
    public Boolean deleteCliente(@Argument Integer id_cliente) {
        clientesRepositorio.deleteById(id_cliente);
        return true;
    }

    @MutationMapping
    public Clientes updateCliente(@Argument Integer id_cliente, @Argument ClientesInput clientesInput) {
        Clientes cliente = clientesRepositorio.findById(id_cliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Tipodocumento tipoDoc = tipodocumentoRepositorio.findById(clientesInput.idTipoDocumento())
                .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado"));

        cliente.setIdTipoDocumento(tipoDoc);
        cliente.setNombre(clientesInput.nombre());
        cliente.setApellido(clientesInput.apellido());
        cliente.setNumeroDocumento(clientesInput.numeroDocumento());
        cliente.setCorreoElectronico(clientesInput.correoElectronico());
        cliente.setContrasenaHash(passwordEncryptor.encrypt(clientesInput.contrasenaHash()));
        cliente.setDireccion(clientesInput.direccion());
        cliente.setTelefono(clientesInput.telefono());

        return clientesRepositorio.save(cliente);
    }



    public boolean validarLogin(String correoElectronico, String contrasenaHash) {
        Clientes clientes = clientesRepositorio.findByCorreoElectronico(correoElectronico).orElse(null);
        if (clientes == null) return false;
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
