package com.prueba.prueba.Agentes;


import jakarta.persistence.*;

@Entity
public class Agentes {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAgente;

    private String nombreUsuario;

    private String contrasenaHash;

    private String direccion;

    private String telefono;

    private String correoElectronico;

    public Agentes(String nombreUsuario, String contrasenaHash, String direccion, String telefono, String correoElectronico) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenaHash = contrasenaHash;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }

    public Agentes() {
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Integer getId_agente() {
        return idAgente;
    }

    public void setId_agente(Integer idAgente) {
        this.idAgente = idAgente;
    }

    @Override
    public String toString() {
        return "Agentes{" +
                "id_agente=" + idAgente +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", contrasenaHash='" + contrasenaHash + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                '}';
    }
}
