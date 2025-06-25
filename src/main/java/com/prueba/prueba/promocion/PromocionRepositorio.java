package com.prueba.prueba.promocion;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.graphql.data.GraphQlRepository;



@GraphQlRepository
public interface PromocionRepositorio extends JpaRepository<Promocion, Integer>, QueryByExampleExecutor<Promocion> {
}
