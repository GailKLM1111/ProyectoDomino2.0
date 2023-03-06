package com.example.proyectodomino2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NoJugadores {

    @FXML
    private AnchorPane pantallaNoJugadores;

    @FXML
    private Button unJugador;

    @FXML
    private Button dosJugadores;

    @FXML
    private Button tresJugadores;

    @FXML
    void unJugador(ActionEvent event) {

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
    void dosJugadores(ActionEvent event) {

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
    void tresJugadores(ActionEvent event) {

        try {
            Parent pantallaDeJuego = FXMLLoader.load(getClass().getResource("pantallaTresJugadores.fxml"));

            Scene escenaNueva = new Scene(pantallaDeJuego);

            Stage escenaActual = (Stage) pantallaNoJugadores.getScene().getWindow();
            escenaActual.setScene(escenaNueva);

        } catch (Exception e) {
            System.out.println("Error al cargar la ventana");
        }

    }

}
