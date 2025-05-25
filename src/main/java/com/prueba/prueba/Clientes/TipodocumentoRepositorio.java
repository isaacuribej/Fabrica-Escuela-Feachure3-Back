package com.prueba.prueba.Clientes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface TipodocumentoRepositorio extends JpaRepository<Tipodocumento, Integer> {

}
