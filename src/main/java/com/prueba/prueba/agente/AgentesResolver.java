package com.prueba.prueba.agente;


import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.prueba.prueba.utilities.PasswordEncryptor;

import java.util.List;



@Controller
public class AgentesResolver {
    private final  AgentesRepositorio agentesRepositorio;
    private final PasswordEncryptor passwordEncryptor;

    public AgentesResolver(AgentesRepositorio agentesRepositorio, PasswordEncryptor passwordEncryptor) {
        this.agentesRepositorio = agentesRepositorio;
        this.passwordEncryptor = passwordEncryptor;
    }

    @QueryMapping
    public List<Agente> listaAgentes() {
        return  agentesRepositorio.findAll();
    }

    @QueryMapping
    public Agente buscaAgente(@Argument Integer idAgente) {
        return  agentesRepositorio.findById(idAgente).orElseThrow(() -> new RuntimeException("Agente no encontrado"));
    }

    public boolean validarLogin(String nombreUsuario, String contrasenaHash) {
        Agente agentes = agentesRepositorio.findByNombreUsuario(nombreUsuario).orElse(null);
        if (agentes == null) return false;
        try {
            return passwordEncryptor.matches(contrasenaHash, agentes.getContrasenaHash());
        } catch (Exception e) {
            return false;
        }
    }

    @MutationMapping(name = "LoginAgente")
    public Boolean loginAgente(@Argument String nombreUsuario, @Argument String contrasenaHash) {
        return validarLogin(nombreUsuario, contrasenaHash);
    }


    public record AgentesInput(
            String nombreUsuario,
            String contrasenaHash,
            String direccion,
            String telefono,
            String correoElectronico
    ) {}


    @MutationMapping(name = "insertarAgente")
    public Agente insertarAgente(@Argument AgentesInput agentesInput) {
        Agente agentes = new Agente();
        // Encriptar la contraseña antes de guardar
        agentes.setContrasenaHash(passwordEncryptor.encrypt(agentesInput.contrasenaHash()));
        agentes.setNombreUsuario(agentesInput.nombreUsuario());
        agentes.setDireccion(agentesInput.direccion());
        agentes.setTelefono(agentesInput.telefono());
        agentes.setCorreoElectronico(agentesInput.correoElectronico());
        return agentesRepositorio.save(agentes);
    }
}



