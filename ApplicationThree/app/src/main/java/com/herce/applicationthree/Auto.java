package com.herce.applicationthree;

import java.io.Serializable;

/**
 * Created by herce on 2/9/17.
 */

public class Auto implements Serializable {
    private String Marca;
    private String Auto;
    private String Imagen;

    public String getMarca(){
        return Marca;
    }

    public void setMarca(String Marca){
        this.Marca = Marca;
    }

    public String getAuto(){
        return Auto;
    }

    public void setAuto(String Auto){
        this.Auto = Auto;
    }

    public String getImagen(){
        return Imagen;
    }

    public void setImagen(String Imagen){
        this.Imagen = Imagen;
    }
}
