package com.prueba.prueba.envio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.graphql.data.GraphQlRepository;


@GraphQlRepository
public interface EnvioRepositorio extends JpaRepository<Envio, Integer>, QueryByExampleExecutor<Envio> {
}
