package com.prueba.prueba.Agente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.graphql.data.GraphQlRepository;
import java.util.Optional;

@GraphQlRepository
public interface AgentesRepositorio extends JpaRepository<Agente, Integer>, QueryByExampleExecutor <Agente> {
    Optional<Agente> findByNombreUsuario(String nombreUsuario);
}
