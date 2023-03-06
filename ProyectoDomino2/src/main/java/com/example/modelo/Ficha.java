package com.example.modelo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.MalformedURLException;

public class Ficha {

    int v1;
    int v2;
    String fichaURL;
    Image imagenFicha;
    boolean estado;
    boolean jugar;

    public Ficha(int v1, int v2, boolean s, boolean j) {

        this.v1=v1;
        this.v2=v2;
        this.estado=s;
        this.jugar=j;
//
//        String imgAddress = "/Users/gaillopez/Desktop/OCTAVO SEMESTRE /Videojuegos/ProyectoDomino2.0/ProyectoDomino2/src/main/resources/sprites/fichas/0-0.png";
//        String tmpAddress;
//
//        File file2 = new File(imgAddress);
//        tmpAddress = file2.toURI().toURL().toExternalForm();
//        Image image = new Image(tmpAddress);

//        ../../../sprites/escenarios/unJugador.png
//        jetbrains://idea/navigate/reference?project=ProyectoDomino2.0&path=sprites/fichas/0-0.png
//        /Users/gaillopez/Desktop/OCTAVO SEMESTRE /Videojuegos/ProyectoDomino2.0/ProyectoDomino2/src/main/resources/sprites/fichas/0-0.png
//        file:/Users/gaillopez/Desktop/OCTAVO%20SEMESTRE%20/Videojuegos/ProyectoDomino2.0/ProyectoDomino2/target/classes/sprites/escenarios/unJugador.png

        try {
            this.fichaURL = "/Users/gaillopez/Desktop/OCTAVO SEMESTRE /Videojuegos/ProyectoDomino2.0/ProyectoDomino2/src/main/resources/sprites/fichas/" + this.v1 +  "-" + this.v2 +".png";
            String fichaURLTemp;

            File imagen = new File(fichaURL);
            fichaURLTemp = imagen.toURI().toURL().toExternalForm();

            this.imagenFicha = new Image(fichaURLTemp);
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen de la ficha");

        }

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

    public Image getImagenFicha() {
        return imagenFicha;
    }

    public void setImagenFicha(Image imagenFicha) {
        this.imagenFicha = imagenFicha;
    }

    public boolean getJugar () {
        return jugar;
    }

    public void setJugar (boolean g) {
        this.jugar=g;
    }

}
