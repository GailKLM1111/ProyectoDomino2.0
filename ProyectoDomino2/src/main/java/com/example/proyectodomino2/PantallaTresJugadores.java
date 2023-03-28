package com.example.proyectodomino2;

import com.example.audio.SonidoBotones;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PantallaTresJugadores {

    @FXML
    public AnchorPane pantallaTresJugadores;
    @FXML
    private GridPane gridFichas;

    @FXML
    private GridPane gridTablero;

    @FXML
    private ImageView volverBoton;

    SonidoBotones sonidoBotones = new SonidoBotones();

    @FXML
    void aclarar(MouseEvent event) {

        volverBoton.setEffect(null);

    }

    @FXML
    void obscurecer(MouseEvent event) {

        Light.Distant light = new Light.Distant();
        light.setAzimuth(-135.0);

        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);

        volverBoton.setEffect(lighting);

    }

    @FXML
    void volver(MouseEvent event) {

        sonidoBotones.reproducirSonidoBotones();

        try {
            Parent noJuagadores = FXMLLoader.load(getClass().getResource("noJugadores.fxml"));

            Scene escenaNueva = new Scene(noJuagadores);

            Stage escenaActual = (Stage) pantallaTresJugadores.getScene().getWindow();
            escenaActual.setScene(escenaNueva);

        } catch (Exception e) {
            System.out.println("Error al cargar la ventana");
        }

    }

}