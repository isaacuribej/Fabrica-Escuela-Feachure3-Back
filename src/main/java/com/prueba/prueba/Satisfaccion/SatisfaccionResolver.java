package com.prueba.prueba.Satisfaccion;


import com.prueba.prueba.Clientes.Clientes;
import com.prueba.prueba.Clientes.ClientesRepositorio;
import com.prueba.prueba.Envios.Envios;
import com.prueba.prueba.Envios.EnviosRepositorio;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SatisfaccionResolver {
    private final SatisfaccionRepositorio satisfaccionRepositorio;
    private final ClientesRepositorio clientesRepositorio;
    private final EnviosRepositorio enviosRepositorio;

    public SatisfaccionResolver(SatisfaccionRepositorio satisfaccionRepositorio, ClientesRepositorio clientesRepositorio, EnviosRepositorio enviosRepositorio) {
        this.satisfaccionRepositorio = satisfaccionRepositorio;
        this.clientesRepositorio = clientesRepositorio;
        this.enviosRepositorio = enviosRepositorio;
    }


    @SchemaMapping
    public Clientes resolverCliente(Satisfaccion satisfaccion) {
        return satisfaccion.getId_cliente();
    }
    @SchemaMapping
    public Envios resolverEnvio(Satisfaccion satisfaccion) {
        return satisfaccion.getId_envio();
    }

    //Lista todas las satisfacciones

    @QueryMapping
    public List<Satisfaccion> listaSatisfacciones() {
        return satisfaccionRepositorio.findAll();
    }


}

