package com.prueba.prueba.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.Optional;


@GraphQlRepository
public interface ClienteRepositorio extends JpaRepository<Cliente,Integer>, QueryByExampleExecutor <Cliente> {
    Optional<Cliente> findByCorreoElectronico(String correoElectronico);

}
