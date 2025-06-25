package com.prueba.prueba.Comentario;


import jakarta.persistence.*;

import java.time.LocalDate;

import com.prueba.prueba.Envio.Envio;

@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComentario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idEnvio", nullable = false)
    private Envio idEnvio;

    private LocalDate fechaComentario;

    private String contenido;

    public Comentario(Envio idEnvio, LocalDate fechaComentario, String contenido) {
        this.idEnvio = idEnvio;
        this.fechaComentario = fechaComentario;
        this.contenido = contenido;
    }

    public Comentario() {
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(LocalDate fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public Envio getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Envio idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Integer getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Integer idComentario) {
        this.idComentario = idComentario;
    }

    @Override
    public String toString() {
        return "Comentarios{" +
                "id_comentario=" + idComentario +
                ", id_envio=" + idEnvio +
                ", fechaComentario=" + fechaComentario +
                ", contenido='" + contenido + '\'' +
                '}';
    }
}
