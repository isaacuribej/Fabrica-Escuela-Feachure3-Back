package com.prueba.prueba.Comentarios;


import com.prueba.prueba.Envios.Envios;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Comentarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComentario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idEnvio", nullable = false)
    private Envios idEnvio;

    private LocalDate fechaComentario;

    private String contenido;

    public Comentarios(Envios id_envio, LocalDate fechaComentario, String contenido) {
        this.idEnvio = id_envio;
        this.fechaComentario = fechaComentario;
        this.contenido = contenido;
    }

    public Comentarios() {
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

    public Envios getId_envio() {
        return idEnvio;
    }

    public void setId_envio(Envios id_envio) {
        this.idEnvio = id_envio;
    }

    public Integer getId_comentario() {
        return idComentario;
    }

    public void setId_comentario(Integer id_comentario) {
        this.idComentario = id_comentario;
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
