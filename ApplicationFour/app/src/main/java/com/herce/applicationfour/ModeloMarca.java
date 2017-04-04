package com.herce.applicationfour;

/**
 * Created by herce on 3/16/17.
 */

public class ModeloMarca {
    private String nombre;

    public ModeloMarca() {

    }

    public ModeloMarca (String nombre) {
        this.nombre = nombre;
    }

    public String getNombre () {
        return nombre;
    }

    public void setNombre (String nombre) {
        this.nombre = nombre;
    }
}
