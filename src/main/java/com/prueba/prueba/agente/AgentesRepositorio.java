package com.prueba.prueba.agente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.graphql.data.GraphQlRepository;
import java.util.Optional;

@SuppressWarnings("unused")
@GraphQlRepository
public interface AgentesRepositorio extends JpaRepository<Agente, Integer>, QueryByExampleExecutor <Agente> {
    Optional<Agente> findByNombreUsuario(String nombreUsuario);
}
