package com.arsltech.developer.mysqlloginregistration.logica;

public class Ente {

    public int id;
    public String nombre;
    public String telefono;
    public String identificacion;
    public String id_registro2;


    public Ente(int id, String nombre, String telefono, String identificacion, String id_registro2) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.identificacion = identificacion;
        this.id_registro2 = id_registro2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getId_registro2() {
        return id_registro2;
    }

    public void setId_registro2(String id_registro2) {
        this.id_registro2 = id_registro2;
    }

    @Override
    public String toString() {
        return "Ente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", id_registro2='" + id_registro2 + '\'' +
                '}';
    }
}
