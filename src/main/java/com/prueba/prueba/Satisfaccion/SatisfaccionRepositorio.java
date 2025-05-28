package com.prueba.prueba.Satisfaccion;


import com.prueba.prueba.Clientes.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface SatisfaccionRepositorio extends JpaRepository<Satisfaccion,Integer>, QueryByExampleExecutor<Satisfaccion> {
}
