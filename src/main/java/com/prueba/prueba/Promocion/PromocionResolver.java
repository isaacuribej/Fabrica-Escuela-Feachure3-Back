package com.prueba.prueba.Promocion;


import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.prueba.prueba.Cliente.Cliente;
import com.prueba.prueba.Cliente.ClienteRepositorio;

import java.util.List;

@Controller
public class PromocionResolver {

    private final PromocionRepositorio promocionRepositorio;
    private final ClienteRepositorio clientesRepositorio;

    public PromocionResolver(PromocionRepositorio promocionRepositorio, ClienteRepositorio clientesRepositorio) {
        this.promocionRepositorio = promocionRepositorio;
        this.clientesRepositorio = clientesRepositorio;
    }


    @SchemaMapping(typeName = "Promociones", field = "idCliente")
    public Cliente resolverCliente(Promocion promociones) {
        return promociones.getIdCliente();
    }

    @QueryMapping
    public List<Promocion> promocionesPorCliente(@Argument Integer idCliente) {
        // Recupera todas las promociones y filtra por ID de cliente
        return promocionRepositorio.findAll().stream()
                .filter(promo -> promo.getIdCliente() != null && promo.getIdCliente().getIdCliente().equals(idCliente))
                .toList();
    }




}
