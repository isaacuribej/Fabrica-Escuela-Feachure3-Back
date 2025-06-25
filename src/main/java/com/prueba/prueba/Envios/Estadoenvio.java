package com.prueba.prueba.Envios;

import jakarta.persistence.*;

@Entity
@Table(name = "estadoenvios")
public class Estadoenvio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEstado", nullable = false)
    private Integer idEstado;

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId_estado() {
        return idEstado;
    }

    public void setId_estado(Integer id_estado) {
        this.idEstado = id_estado;
    }

    //TODO [Reverse Engineering] generate columns from DB
}