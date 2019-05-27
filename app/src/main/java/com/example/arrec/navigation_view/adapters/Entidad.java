package com.example.arrec.navigation_view.adapters;

public class Entidad {

    private int imgMiniatura;
    private String titulo;
    private String descripcion;

    public Entidad(int imgMiniatura, String titulo, String descripcion) {
        this.imgMiniatura = imgMiniatura;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public int getImgMiniatura() {
        return imgMiniatura;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
