package com.prueba.prueba.satisfaccion;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.graphql.data.GraphQlRepository;

import java.time.LocalDate;
import java.util.List;

@GraphQlRepository
public interface SatisfaccionRepositorio extends JpaRepository<Satisfaccion,Integer>, QueryByExampleExecutor<Satisfaccion> {
    List<Satisfaccion> findByFechaEncuestaBetween(LocalDate fechaInicio, LocalDate fechaFin);
}
