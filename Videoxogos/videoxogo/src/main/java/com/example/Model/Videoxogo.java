package com.example.Model;

public class Videoxogo {
    private String xogador;
    private String xogo;
    private int puntuacion;
    private int duracion;
    private int nivel;

    public Videoxogo(String xogador,String xogo,int puntuacion,int duracion,int nivel){
        this.xogador = xogador;
        this.xogo = xogo;
        this.puntuacion = puntuacion;
        this.duracion = duracion;
        this.nivel = nivel;
    }

    public String getXogador() {
        return xogador;
    }
    public void setXogador(String xogador) {
        this.xogador = xogador;
    }
    public String getXogo() {
        return xogo;
    }
    public void setXogo(String xogo) {
        this.xogo = xogo;
    }
    public int getPuntuacion() {
        return puntuacion;
    }
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    public int getDuracion() {
        return duracion;
    }
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "Videoxogo [xogador=" + xogador + ", xogo=" + xogo + ", puntuacion=" + puntuacion + ", duracion="
                + duracion + ", nivel=" + nivel + "]";
    }
}
