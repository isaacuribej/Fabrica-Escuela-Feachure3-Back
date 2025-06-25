package com.prueba.prueba.satisfaccion;


import com.prueba.prueba.cliente.Cliente;
import com.prueba.prueba.cliente.ClienteRepositorio;
import com.prueba.prueba.envio.Envio;
import com.prueba.prueba.envio.EnvioRepositorio;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class SatisfaccionResolver {
    private final SatisfaccionRepositorio satisfaccionRepositorio;
    private final ClienteRepositorio clientesRepositorio;
    private final EnvioRepositorio enviosRepositorio;

    public SatisfaccionResolver(SatisfaccionRepositorio satisfaccionRepositorio, ClienteRepositorio clientesRepositorio, EnvioRepositorio enviosRepositorio) {
        this.satisfaccionRepositorio = satisfaccionRepositorio;
        this.clientesRepositorio = clientesRepositorio;
        this.enviosRepositorio = enviosRepositorio;
    }


    @SchemaMapping
    public Cliente resolverCliente(Satisfaccion satisfaccion) {
        return satisfaccion.getIdCliente();
    }
    @SchemaMapping
    public Envio resolverEnvio(Satisfaccion satisfaccion) {
        return satisfaccion.getIdEnvio();
    }

    //Lista todas las satisfacciones

    @QueryMapping
    public List<Satisfaccion> listaSatisfacciones() {
        return satisfaccionRepositorio.findAll();
    }

    @QueryMapping
    public List<Satisfaccion> satisfaccionesPorFecha(@Argument String fechaInicio, @Argument String fechaFin) {
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);
        return satisfaccionRepositorio.findByFechaEncuestaBetween(inicio, fin);
    }


}

