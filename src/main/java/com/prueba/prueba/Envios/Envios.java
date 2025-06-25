package com.prueba.prueba.Envios;

import com.prueba.prueba.Clientes.Clientes;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Envios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnvio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCliente", nullable = false)
    private Clientes idCliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idEstado", nullable = false)
    private Estadoenvio idEstado;

    private String numeroGuia;

    private String direccionEnvio;

    private LocalDate fechaCompra;

    private BigDecimal precio;    
    
    public Envios(Estadoenvio idEstado, String direccionEnvio, BigDecimal precio, LocalDate fechaCompra, String numeroGuia, Clientes idCliente) {
        this.idEstado = idEstado;
        this.direccionEnvio = direccionEnvio;
        this.precio = precio;
        this.fechaCompra = fechaCompra;
        this.numeroGuia = numeroGuia;
        this.idCliente = idCliente;
    }

    public Envios() {
    }

    public Integer getId_envio() {
        return idEnvio;
    }

    public void setId_envio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Clientes getId_cliente() {
        return idCliente;
    }

    public void setId_cliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    public Estadoenvio getId_estado() {
        return idEstado;
    }

    public void setId_estado(Estadoenvio idEstado) {
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
