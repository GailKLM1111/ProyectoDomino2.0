package com.example.proyectodomino2;

import com.example.audio.SonidoBotones;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NoJugadores {

    @FXML
    public ImageView unJugadorBoton;
    @FXML
    public ImageView dosJugadoresrBoton;
    @FXML
    public ImageView tresJugadoresrBoton;
    @FXML
    private AnchorPane pantallaNoJugadores;

    @FXML
    private Button unJugador;

    @FXML
    private Button dosJugadores;

    @FXML
    private Button tresJugadores;

    SonidoBotones sonidoBotones = new SonidoBotones();

    @FXML
    void unJugador(MouseEvent event) {

        sonidoBotones.reproducirSonidoBotones();

        try {
            Parent pantallaDeJuego = FXMLLoader.load(getClass().getResource("pantallaUnJugador.fxml"));

            Scene escenaNueva = new Scene(pantallaDeJuego);

            Stage escenaActual = (Stage) pantallaNoJugadores.getScene().getWindow();
            escenaActual.setScene(escenaNueva);

        } catch (Exception e) {
            System.out.println("Error al cargar la ventana:" + e);
        }

    }

    @FXML
    void dosJugadores(MouseEvent event) {

        sonidoBotones.reproducirSonidoBotones();

        try {
            Parent pantallaDeJuego = FXMLLoader.load(getClass().getResource("pantallaDosJugadores.fxml"));

            Scene escenaNueva = new Scene(pantallaDeJuego);

            Stage escenaActual = (Stage) pantallaNoJugadores.getScene().getWindow();
            escenaActual.setScene(escenaNueva);

        } catch (Exception e) {
            System.out.println("Error al cargar la ventana");
        }

    }

    @FXML
    void tresJugadores(MouseEvent event) {

        sonidoBotones.reproducirSonidoBotones();

        try {
            Parent pantallaDeJuego = FXMLLoader.load(getClass().getResource("pantallaTresJugadores.fxml"));

            Scene escenaNueva = new Scene(pantallaDeJuego);

            Stage escenaActual = (Stage) pantallaNoJugadores.getScene().getWindow();
            escenaActual.setScene(escenaNueva);

        } catch (Exception e) {
            System.out.println("Error al cargar la ventana");
        }

    }
    @FXML
    void oscurecer1(MouseEvent event) {

        obscurecer(unJugadorBoton);

    }
    @FXML
    void aclarar1(MouseEvent event) {

        aclarar(unJugadorBoton);

    }
    @FXML
    void oscurecer2(MouseEvent event) {

        obscurecer(dosJugadoresrBoton);

    }
    @FXML
    void aclarar2(MouseEvent event) {

        aclarar(dosJugadoresrBoton);

    }
    @FXML
    void oscurecer3(MouseEvent event) {

        obscurecer(tresJugadoresrBoton);

    }
    @FXML
    void aclarar3(MouseEvent event) {

        aclarar(tresJugadoresrBoton);

    }

    void obscurecer(ImageView imagenBoton) {

        Light.Distant light = new Light.Distant();
        light.setAzimuth(-135.0);

        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);

        imagenBoton.setEffect(lighting);

    }

    void aclarar(ImageView imagenBoton) {

        imagenBoton.setEffect(null);

    }

}
