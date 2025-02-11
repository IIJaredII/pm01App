package com.example.pm01app.models;

public class Cliente {

    private Integer id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String imagen;

    public Cliente() {
    }

    public Cliente(Integer id, String correo, String nombres, String apellidos, String imagen) {
        this.id = id;
        this.correo = correo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.imagen = imagen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getImagen(){ return imagen; }

    public void setImagen(String imagen){this.imagen = imagen;}
}
