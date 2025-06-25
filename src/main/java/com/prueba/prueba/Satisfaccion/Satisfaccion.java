package com.prueba.prueba.Satisfaccion;


import com.prueba.prueba.Clientes.Clientes;
import com.prueba.prueba.Envios.Envios;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Satisfaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_satisfaccion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCliente", nullable = false)
    private Clientes idCliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idEnvio", nullable = false)
    private Envios idEnvio;

    private LocalDate fechaEncuesta;

    private Integer calificacion;

    private String comentario_satisfaccion;


    public Satisfaccion(Clientes idCliente, Envios idEnvio, LocalDate fechaEncuesta, Integer calificacion, String comentarioSatisfaccion) {
        this.idCliente = idCliente;
        this.idEnvio = idEnvio;
        this.fechaEncuesta = fechaEncuesta;
        this.calificacion = calificacion;
        this.comentario_satisfaccion = comentarioSatisfaccion;
    }

    public Satisfaccion() {
    }

    public String getComentario_satisfaccion() {
        return comentario_satisfaccion;
    }

    public void setComentario_satisfaccion(String comentario_satisfaccion) {
        this.comentario_satisfaccion = comentario_satisfaccion;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public LocalDate getFechaEncuesta() {
        return fechaEncuesta;
    }

    public void setFechaEncuesta(LocalDate fechaEncuesta) {
        this.fechaEncuesta = fechaEncuesta;
    }

    public Envios getId_envio() {
        return idEnvio;
    }

    public void setId_envio(Envios id_envio) {
        this.idEnvio = id_envio;
    }

    public Clientes getId_cliente() {
        return idCliente;
    }

    public void setId_cliente(Clientes id_cliente) {
        this.idCliente = id_cliente;
    }

    public Integer getId_satisfaccion() {
        return id_satisfaccion;
    }

    public void setId_satisfaccion(Integer id_satisfaccion) {
        this.id_satisfaccion = id_satisfaccion;
    }

    @Override
    public String toString() {
        return "Satisfaccion{" +
                "id_satisfaccion=" + id_satisfaccion +
                ", id_cliente=" + idCliente +
                ", id_envio=" + idEnvio +
                ", fechaEncuesta=" + fechaEncuesta +
                ", calificacion=" + calificacion +
                ", comentario_satisfaccion='" + comentario_satisfaccion + '\'' +
                '}';
    }
}
