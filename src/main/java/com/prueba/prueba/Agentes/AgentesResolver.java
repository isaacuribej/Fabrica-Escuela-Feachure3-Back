package com.prueba.prueba.Agentes;


import com.prueba.prueba.Clientes.Clientes;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AgentesResolver {
    private final  AgentesRepositorio agentesRepositorio;

    public AgentesResolver(AgentesRepositorio agentesRepositorio) {
        this.agentesRepositorio = agentesRepositorio;
    }

    @QueryMapping
    public List<Agentes> listaAgentes() {
        return  agentesRepositorio.findAll();
    }

    @QueryMapping
    public Agentes buscaAgente(@Argument Integer id_agente) {
        return  agentesRepositorio.findById(id_agente).orElseThrow(() -> new RuntimeException("Agente no encontrado"));
    }

}



