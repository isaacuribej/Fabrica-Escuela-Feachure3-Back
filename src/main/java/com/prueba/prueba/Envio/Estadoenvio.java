package com.prueba.prueba.Envio;

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

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    //TODO [Reverse Engineering] generate columns from DB
}