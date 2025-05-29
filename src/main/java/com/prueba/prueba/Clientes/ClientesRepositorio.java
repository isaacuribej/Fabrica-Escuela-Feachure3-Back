package com.prueba.prueba.Clientes;


import com.prueba.prueba.Agentes.Agentes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.Optional;


@GraphQlRepository
public interface ClientesRepositorio extends JpaRepository<Clientes,Integer>, QueryByExampleExecutor <Clientes> {
    Optional<Clientes> findByCorreoElectronico(String correoElectronico);

}
