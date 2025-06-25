package com.prueba.prueba.Envios;

import com.prueba.prueba.Clientes.Clientes;
import com.prueba.prueba.Clientes.ClientesRepositorio;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Controller
public class EnviosResolver {


    private final EnviosRepositorio enviosRepositorio;
    private final ClientesRepositorio clientesRepositorio;
    private final  EstadoenvioRepositorio estadoenvioRepositorio;

    public  EnviosResolver(EnviosRepositorio enviosRepositorio, ClientesRepositorio clientesRepositorio, EstadoenvioRepositorio estadoenvioRepositorio) {
        this.enviosRepositorio = enviosRepositorio;
        this.clientesRepositorio = clientesRepositorio;
        this.estadoenvioRepositorio = estadoenvioRepositorio;
    }

    @SchemaMapping(typeName = "Envios", field = "idEstado")
    public Estadoenvio resolverEstadoEnvio(Envios envios) {
        return  envios.getId_estado();
    }

    @SchemaMapping(typeName = "Envios", field = "idCliente")
    public Clientes resolverCliente(Envios envios) {
        return envios.getId_cliente();
    }


    @QueryMapping
    public List<Envios> obtenerEnvios() {
        return enviosRepositorio.findAll();
    }

    public record EnviosInput(
            Integer idCliente,
            Integer idEstado,
            String numeroGuia,
            String direccionEnvio,
            String fechaCompra,
            Float precio
    ){}

    @QueryMapping
    public List<Envios> enviosPorCliente(@Argument Integer idCliente) {
        return enviosRepositorio.findAll().stream()
                .filter(envio -> envio.getId_cliente() != null && envio.getId_cliente().getId_cliente().equals(idCliente))
                .toList();
    }

    @MutationMapping
    public Envios uptadeEnvio(@Argument Integer idEnvio, @Argument EnviosInput enviosInput) {
        Envios envio = enviosRepositorio.findById(idEnvio)
                .orElseThrow(() -> new RuntimeException("Envio no encontrado"));
        Clientes cliente = clientesRepositorio.findById(enviosInput.idCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Estadoenvio estado = estadoenvioRepositorio.findById(enviosInput.idEstado())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        envio.setId_cliente(cliente);
        envio.setId_estado(estado);
        envio.setNumeroGuia(enviosInput.numeroGuia());
        envio.setDireccionEnvio(enviosInput.direccionEnvio());
        envio.setFechaCompra(LocalDate.parse(enviosInput.fechaCompra()));
        envio.setPrecio(BigDecimal.valueOf(enviosInput.precio()));

        return  enviosRepositorio.save(envio);
    }

}
