package com.prueba.prueba.Promociones;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.graphql.data.GraphQlRepository;



@GraphQlRepository
public interface PromocionRepositorio extends JpaRepository<Promociones, Integer>, QueryByExampleExecutor<Promociones> {
}
