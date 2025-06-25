package com.prueba.prueba.Envio;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.prueba.prueba.Cliente.Cliente;
import com.prueba.prueba.Cliente.ClienteRepositorio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Controller
public class EnvioResolver {


    private final EnvioRepositorio enviosRepositorio;
    private final ClienteRepositorio clientesRepositorio;
    private final  EstadoenvioRepositorio estadoenvioRepositorio;

    public  EnvioResolver(EnvioRepositorio enviosRepositorio, ClienteRepositorio clientesRepositorio, EstadoenvioRepositorio estadoenvioRepositorio) {
        this.enviosRepositorio = enviosRepositorio;
        this.clientesRepositorio = clientesRepositorio;
        this.estadoenvioRepositorio = estadoenvioRepositorio;
    }

    @SchemaMapping(typeName = "Envios", field = "idEstado")
    public Estadoenvio resolverEstadoEnvio(Envio envios) {
        return  envios.getIdEstado();
    }

    @SchemaMapping(typeName = "Envios", field = "idCliente")
    public Cliente resolverCliente(Envio envios) {
        return envios.getIdCliente();
    }


    @QueryMapping
    public List<Envio> obtenerEnvios() {
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
    public List<Envio> enviosPorCliente(@Argument Integer idCliente) {
        return enviosRepositorio.findAll().stream()
                .filter(envio -> envio.getIdCliente() != null && envio.getIdCliente().getIdCliente().equals(idCliente))
                .toList();
    }

    @MutationMapping
    public Envio uptadeEnvio(@Argument Integer idEnvio, @Argument EnviosInput enviosInput) {
        Envio envio = enviosRepositorio.findById(idEnvio)
                .orElseThrow(() -> new RuntimeException("Envio no encontrado"));
        Cliente cliente = clientesRepositorio.findById(enviosInput.idCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Estadoenvio estado = estadoenvioRepositorio.findById(enviosInput.idEstado())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        envio.setIdCliente(cliente);
        envio.setIdEstado(estado);
        envio.setNumeroGuia(enviosInput.numeroGuia());
        envio.setDireccionEnvio(enviosInput.direccionEnvio());
        envio.setFechaCompra(LocalDate.parse(enviosInput.fechaCompra()));
        envio.setPrecio(BigDecimal.valueOf(enviosInput.precio()));

        return  enviosRepositorio.save(envio);
    }

}
