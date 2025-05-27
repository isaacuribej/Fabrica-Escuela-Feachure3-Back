package com.prueba.prueba.Comentariios;


import com.prueba.prueba.Envios.Envios;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Comentarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_comentario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_envio", nullable = false)
    private Envios id_envio;

    private LocalDate fechaComentario;

    private String contenido;

    public Comentarios(Envios id_envio, LocalDate fechaComentario, String contenido) {
        this.id_envio = id_envio;
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
        return id_envio;
    }

    public void setId_envio(Envios id_envio) {
        this.id_envio = id_envio;
    }

    public Integer getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(Integer id_comentario) {
        this.id_comentario = id_comentario;
    }

    @Override
    public String toString() {
        return "Comentarios{" +
                "id_comentario=" + id_comentario +
                ", id_envio=" + id_envio +
                ", fechaComentario=" + fechaComentario +
                ", contenido='" + contenido + '\'' +
                '}';
    }
}
