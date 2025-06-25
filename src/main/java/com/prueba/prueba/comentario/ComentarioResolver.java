package com.prueba.prueba.comentario;


import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.prueba.prueba.envio.Envio;
import com.prueba.prueba.envio.EnvioRepositorio;

import java.time.LocalDate;
import java.util.List;


@Controller
public class ComentarioResolver {
    private final ComentarioRepositorio comentariosRepositorio;
    private final EnvioRepositorio enviosRepositorio;

    public ComentarioResolver(ComentarioRepositorio comentariosRepositorio, EnvioRepositorio enviosRepositorio) {
        this.comentariosRepositorio = comentariosRepositorio;
        this.enviosRepositorio = enviosRepositorio;
    }




    @SchemaMapping
    public Envio resolverEnvios(Comentario comentarios) {
        return comentarios.getIdEnvio();
    }

    @QueryMapping
    public List<Comentario> comentariosporEnvio(@Argument Integer id_envio) {
        // Trae todos los comentarios
        List<Comentario> todos = comentariosRepositorio.findAll();

        // Filtra manualmente los que tengan el id_envio solicitado
        return todos.stream()
                .filter(c -> c.getIdEnvio().getIdEnvio().equals(id_envio))
                .toList();
    }

    @MutationMapping
    public Comentario agregarComentario(@Argument Integer idEnvio, @Argument String contenido) {
        // Busca el envío por id
        Envio envio = enviosRepositorio.findById(idEnvio)
                .orElseThrow(() -> new RuntimeException("Envio no encontrado"));

        // Crea el nuevo comentario
        Comentario nuevoComentario = new Comentario();
        nuevoComentario.setIdEnvio(envio);
        nuevoComentario.setContenido(contenido);
        nuevoComentario.setFechaComentario(LocalDate.now());

        // Guarda y devuelve
        return comentariosRepositorio.save(nuevoComentario);
    }

}
