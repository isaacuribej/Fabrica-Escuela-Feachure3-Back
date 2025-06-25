package com.prueba.prueba.Comentario;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface ComentarioRepositorio extends JpaRepository<Comentario, Integer>, QueryByExampleExecutor<Comentario> {
}
