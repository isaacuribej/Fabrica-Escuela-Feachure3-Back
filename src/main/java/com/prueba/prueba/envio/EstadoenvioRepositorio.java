package com.prueba.prueba.envio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;


@GraphQlRepository
public interface EstadoenvioRepositorio extends JpaRepository<Estadoenvio, Integer> {
}


