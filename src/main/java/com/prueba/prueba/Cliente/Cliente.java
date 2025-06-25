package com.prueba.prueba.Cliente;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipo_documento", nullable = false)
    private Tipodocumento idTipoDocumento;

    private String nombre;

    private String apellido;

    private String numeroDocumento;

    private String correoElectronico;

    private String contrasenaHash;

    private String direccion;

    private String telefono;


    public Cliente(Tipodocumento idTipoDocumento, String nombre, String numeroDocumento, String apellido, String direccion, String correoElectronico, String contrasenaHash, String telefono, LocalDate fechaCreacion) {
        this.idTipoDocumento = idTipoDocumento;
        this.nombre = nombre;
        this.numeroDocumento = numeroDocumento;
        this.apellido = apellido;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
        this.contrasenaHash = contrasenaHash;
        this.telefono = telefono;
    }

    public Cliente() {

    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipodocumento getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Tipodocumento idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "Clientes{" +
                "id_cliente=" + idCliente +
                ", idTipoDocumento=" + idTipoDocumento +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", contrasenaHash='" + contrasenaHash + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
