package com.prueba.prueba.Envios;

import jakarta.persistence.*;

@Entity
@Table(name = "estadoenvios")
public class Estadoenvio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado", nullable = false)
    private Integer id_estado;

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    //TODO [Reverse Engineering] generate columns from DB
}