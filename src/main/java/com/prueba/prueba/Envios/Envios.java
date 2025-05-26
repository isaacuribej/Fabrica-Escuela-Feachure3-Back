package com.prueba.prueba.Envios;

import com.prueba.prueba.Clientes.Clientes;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Envios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_envio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Clientes id_cliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_estado", nullable = false)
    private Estadoenvio id_estado;

    private String numeroGuia;

    private String direccionEnvio;

    private LocalDate fechaCompra;

    private BigDecimal precio;

    public Envios(Estadoenvio id_estado, String direccionEnvio, BigDecimal precio, LocalDate fechaCompra, String numeroGuia, Clientes id_cliente) {
        this.id_estado = id_estado;
        this.direccionEnvio = direccionEnvio;
        this.precio = precio;
        this.fechaCompra = fechaCompra;
        this.numeroGuia = numeroGuia;
        this.id_cliente = id_cliente;
    }

    public Envios() {
    }

    public Integer getId_envio() {
        return id_envio;
    }

    public void setId_envio(Integer id_envio) {
        this.id_envio = id_envio;
    }

    public Clientes getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Clientes id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Estadoenvio getId_estado() {
        return id_estado;
    }

    public void setId_estado(Estadoenvio id_estado) {
        this.id_estado = id_estado;
    }

    public String getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Envios{" +
                "id_envio=" + id_envio +
                ", id_cliente=" + id_cliente +
                ", id_estado=" + id_estado +
                ", numeroGuia='" + numeroGuia + '\'' +
                ", direccionEnvio='" + direccionEnvio + '\'' +
                ", fechaCompra=" + fechaCompra +
                ", precio=" + precio +
                '}';
    }
}
