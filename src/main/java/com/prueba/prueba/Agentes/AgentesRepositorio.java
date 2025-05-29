package com.prueba.prueba.Agentes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.graphql.data.GraphQlRepository;
import java.util.Optional;

@GraphQlRepository
public interface AgentesRepositorio extends JpaRepository<Agentes, Integer>, QueryByExampleExecutor <Agentes> {
    Optional<Agentes> findByNombreUsuario(String nombreUsuario);
}
