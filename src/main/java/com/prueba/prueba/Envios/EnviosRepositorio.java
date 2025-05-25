package com.prueba.prueba.Envios;

import com.prueba.prueba.Clientes.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.graphql.data.GraphQlRepository;


@GraphQlRepository
public interface EnviosRepositorio extends JpaRepository<Envios, Integer>, QueryByExampleExecutor<Envios> {
}
