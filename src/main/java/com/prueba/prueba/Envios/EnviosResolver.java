package com.prueba.prueba.Envios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class EnviosResolver {


    @Autowired
    private EnviosRepositorio enviosRepositorio;

    @QueryMapping
    public List<Envios> obtenerEnvios() {
        return enviosRepositorio.findAll();
    }
}
