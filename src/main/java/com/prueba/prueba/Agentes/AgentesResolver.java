package com.prueba.prueba.Agentes;


import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;


import com.prueba.prueba.Utilities.PasswordEncryptor;



@Controller
public class AgentesResolver {
    private final  AgentesRepositorio agentesRepositorio;
    private final PasswordEncryptor passwordEncryptor;

    public AgentesResolver(AgentesRepositorio agentesRepositorio, PasswordEncryptor passwordEncryptor) {
        this.agentesRepositorio = agentesRepositorio;
        this.passwordEncryptor = passwordEncryptor;
    }

    @QueryMapping
    public List<Agentes> listaAgentes() {
        return  agentesRepositorio.findAll();
    }

    @QueryMapping
    public Agentes buscaAgente(@Argument Integer id_agente) {
        return  agentesRepositorio.findById(id_agente).orElseThrow(() -> new RuntimeException("Agente no encontrado"));
    }

    public boolean validarLogin(String nombreUsuario, String contrasenaHash) {
        Agentes agentes = agentesRepositorio.findByNombreUsuario(nombreUsuario).orElse(null);
        if (nombreUsuario == null) return false;
        return passwordEncryptor.matches(contrasenaHash, agentes.getContrasenaHash());
    }

    @MutationMapping(name = "LoginAgente")
    public Boolean LoginAgente(@Argument String nombreUsuario, @Argument String contrasenaHash) {
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
    public Agentes insertarAgente(@Argument AgentesInput agentesInput) {
        Agentes agentes = new Agentes();
        // Encriptar la contraseña antes de guardar
        agentes.setContrasenaHash(passwordEncryptor.encrypt(agentesInput.contrasenaHash()));
        agentes.setNombreUsuario(agentesInput.nombreUsuario());
        agentes.setDireccion(agentesInput.direccion());
        agentes.setTelefono(agentesInput.telefono());
        agentes.setCorreoElectronico(agentesInput.correoElectronico());
        return agentesRepositorio.save(agentes);
    }
}



