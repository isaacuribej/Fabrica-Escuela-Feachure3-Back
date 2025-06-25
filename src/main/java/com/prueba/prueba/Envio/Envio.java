package com.prueba.prueba.Envio;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.prueba.prueba.Cliente.Cliente;

@Entity
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnvio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente idCliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idEstado", nullable = false)
    private Estadoenvio idEstado;

    private String numeroGuia;

    private String direccionEnvio;

    private LocalDate fechaCompra;

    private BigDecimal precio;    
    
    public Envio(Estadoenvio idEstado, String direccionEnvio, BigDecimal precio, LocalDate fechaCompra, String numeroGuia, Cliente idCliente) {
        this.idEstado = idEstado;
        this.direccionEnvio = direccionEnvio;
        this.precio = precio;
        this.fechaCompra = fechaCompra;
        this.numeroGuia = numeroGuia;
        this.idCliente = idCliente;
    }

    public Envio() {
    }

    public Integer getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Estadoenvio getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estadoenvio idEstado) {
        this.idEstado = idEstado;
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
                "id_envio=" + idEnvio +
                ", idCliente=" + idCliente +
                ", idEstado=" + idEstado +
                ", numeroGuia='" + numeroGuia + '\'' +                
                ", direccionEnvio='" + direccionEnvio + '\'' +
                ", fechaCompra=" + fechaCompra +
                ", precio=" + precio +
                '}';
    }
}
