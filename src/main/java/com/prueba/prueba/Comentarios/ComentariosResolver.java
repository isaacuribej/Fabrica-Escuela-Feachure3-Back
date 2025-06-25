package com.prueba.prueba.Comentarios;


import com.prueba.prueba.Envios.Envios;
import com.prueba.prueba.Envios.EnviosRepositorio;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;


@Controller
public class ComentariosResolver {
    private final ComentariosRepositorio comentariosRepositorio;
    private final EnviosRepositorio enviosRepositorio;

    public ComentariosResolver(ComentariosRepositorio comentariosRepositorio, EnviosRepositorio enviosRepositorio) {
        this.comentariosRepositorio = comentariosRepositorio;
        this.enviosRepositorio = enviosRepositorio;
    }




    @SchemaMapping
    public Envios resolverEnvios(Comentarios comentarios) {
        return comentarios.getId_envio();
    }

    @QueryMapping
    public List<Comentarios> comentariosporEnvio(@Argument Integer id_envio) {
        // Trae todos los comentarios
        List<Comentarios> todos = comentariosRepositorio.findAll();

        // Filtra manualmente los que tengan el id_envio solicitado
        return todos.stream()
                .filter(c -> c.getId_envio().getId_envio().equals(id_envio))
                .toList();
    }

    @MutationMapping
    public Comentarios agregarComentario(@Argument Integer id_envio, @Argument String contenido) {
        // Busca el envío por id
        Envios envio = enviosRepositorio.findById(id_envio)
                .orElseThrow(() -> new RuntimeException("Envio no encontrado"));

        // Crea el nuevo comentario
        Comentarios nuevoComentario = new Comentarios();
        nuevoComentario.setId_envio(envio);
        nuevoComentario.setContenido(contenido);
        nuevoComentario.setFechaComentario(LocalDate.now());

        // Guarda y devuelve
        return comentariosRepositorio.save(nuevoComentario);
    }

}
