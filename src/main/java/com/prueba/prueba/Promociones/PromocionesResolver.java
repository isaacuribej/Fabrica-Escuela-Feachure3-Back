package com.prueba.prueba.Promociones;


import com.prueba.prueba.Clientes.Clientes;
import com.prueba.prueba.Clientes.ClientesRepositorio;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PromocionesResolver {

    private final PromocionRepositorio promocionRepositorio;
    private final ClientesRepositorio clientesRepositorio;

    public PromocionesResolver(PromocionRepositorio promocionRepositorio, ClientesRepositorio clientesRepositorio) {
        this.promocionRepositorio = promocionRepositorio;
        this.clientesRepositorio = clientesRepositorio;
    }


    @SchemaMapping(typeName = "Promociones", field = "idCliente")
    public Clientes resolverCliente(Promociones promociones) {
        return promociones.getId_cliente();
    }

    @QueryMapping
    public List<Promociones> promocionesPorCliente(@Argument Integer idCliente) {
        // Recupera todas las promociones y filtra por ID de cliente
        return promocionRepositorio.findAll().stream()
                .filter(promo -> promo.getId_cliente() != null && promo.getId_cliente().getId_cliente().equals(idCliente))
                .toList();
    }




}
