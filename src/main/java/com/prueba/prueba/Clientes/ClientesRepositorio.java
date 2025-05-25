package com.prueba.prueba.Clientes;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.graphql.data.GraphQlRepository;


@GraphQlRepository
public interface ClientesRepositorio extends JpaRepository<Clientes,Integer>, QueryByExampleExecutor <Clientes> {

}
