package com.prueba.prueba.Satisfaccion;


import com.prueba.prueba.Cliente.Cliente;
import com.prueba.prueba.Envio.Envio;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Satisfaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_satisfaccion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente idCliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idEnvio", nullable = false)
    private Envio idEnvio;

    private LocalDate fechaEncuesta;

    private Integer calificacion;

    private String comentarioSatisfaccion;


    public Satisfaccion(Cliente idCliente, Envio idEnvio, LocalDate fechaEncuesta, Integer calificacion, String comentarioSatisfaccion) {
        this.idCliente = idCliente;
        this.idEnvio = idEnvio;
        this.fechaEncuesta = fechaEncuesta;
        this.calificacion = calificacion;
        this.comentarioSatisfaccion = comentarioSatisfaccion;
    }

    public Satisfaccion() {
    }

    public String getComentario_satisfaccion() {
        return comentarioSatisfaccion;
    }

    public void setComentario_satisfaccion(String comentarioSatisfaccion) {
        this.comentarioSatisfaccion = comentarioSatisfaccion;
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

    public Envio getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Envio idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdSatisfaccion() {
        return id_satisfaccion;
    }

    public void setIdSatisfaccion(Integer idSatisfaccion) {
        this.id_satisfaccion = idSatisfaccion;
    }

    @Override
    public String toString() {
        return "Satisfaccion{" +
                "idSatisfaccion=" + id_satisfaccion +
                ", idCliente=" + idCliente +
                ", idEnvio=" + idEnvio +
                ", fechaEncuesta=" + fechaEncuesta +
                ", calificacion=" + calificacion +
                ", comentarioSatisfaccion='" + comentarioSatisfaccion + '\'' +
                '}';
    }
}
