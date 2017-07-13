package com.damasofc.fruitworld;

import android.widget.ImageView;

public class Frutas {
    private String nombre;
    private String origen;
    int idImagen;
    public Frutas(String nombre, String origen, int idImagen){
        this.nombre = nombre;
        this.origen = origen;
        this.idImagen = idImagen;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }
}
