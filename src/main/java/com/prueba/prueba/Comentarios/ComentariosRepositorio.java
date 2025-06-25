package com.prueba.prueba.Comentarios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface ComentariosRepositorio extends JpaRepository<Comentarios, Integer>, QueryByExampleExecutor<Comentarios> {
}
