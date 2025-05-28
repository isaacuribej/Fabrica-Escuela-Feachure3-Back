package com.prueba.prueba.Promociones;


import com.prueba.prueba.Clientes.Clientes;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
public class Promociones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_promocion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Clientes id_cliente;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    private String descripcion;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    public Promociones(LocalDate fechaFin, LocalDate fechaInicio, String descripcion, String titulo, Clientes id_cliente) {
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.id_cliente = id_cliente;
    }

    public Promociones() {
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Clientes getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Clientes id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getId_promocion() {
        return id_promocion;
    }

    public void setId_promocion(Integer id_promocion) {
        this.id_promocion = id_promocion;
    }

    @Override
    public String toString() {
        return "Promociones{" +
                "id_promocion=" + id_promocion +
                ", id_cliente=" + id_cliente +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }
}
