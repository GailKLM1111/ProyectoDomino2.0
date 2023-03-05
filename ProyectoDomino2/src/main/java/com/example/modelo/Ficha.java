package com.example.modelo;
public class Ficha {

    int v1;
    int v2;
    String fichaURL;
    boolean estado;
    boolean jugar;

    public Ficha(int v1, int v2, boolean s, boolean j) {

        this.v1=v1;
        this.v2=v2;
        this.estado=s;
        this.jugar=j;

        this.fichaURL = "src/main/resources/sprites/fichas/" + this.v1 + "-" + this.v2 +".png";

    }

    public int getV1() {
        return this.v1;
    }

    public int getV2() {
        return this.v2;
    }

    public boolean getEstado() {
        return estado;
    }

    public String getFichaURL() {
        return fichaURL;
    }

    public void setFichaURL(String fichaURL) {
        this.fichaURL = fichaURL;
    }

    public void setEstado(boolean c) {
        this.estado=c;
    }

    public boolean getJugar () {
        return jugar;
    }

    public void setJugar (boolean g) {
        this.jugar=g;
    }

}
