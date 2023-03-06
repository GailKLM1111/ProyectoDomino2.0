package com.example.proyectodomino2;

import com.example.modelo.ListaDeFichas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import com.example.modelo.Ficha;

import java.net.MalformedURLException;

import static com.example.proyectodomino2.MainController.crearFichas;
import static com.example.proyectodomino2.MainController.repartirFichasPorJugador;

public class PantallaUnJugador {

    @FXML
    private GridPane gridFichas;

    @FXML
    private GridPane gridTablero;

    @FXML
    private ImageView tableroUnJugador;

    @FXML
    private Button inicioBoton;

    @FXML
    void repartirFichas(ActionEvent event) throws MalformedURLException {

//        String imgAddress = "/Users/gaillopez/Desktop/OCTAVO SEMESTRE /Videojuegos/ProyectoDomino2.0/ProyectoDomino2/src/main/resources/sprites/fichas/0-0.png";
//        String tmpAddress;
//
//        File file2 = new File(imgAddress);
//        tmpAddress = file2.toURI().toURL().toExternalForm();
//        Image image = new Image(tmpAddress);
//
//        ImageView imagenTablero = new ImageView(image);
//
//        gridFichas.add(imagenTablero, 0, 0);

        crearFichas();

        repartirFichasPorJugador(ListaDeFichas.fichasJugador1);

        mostrarFichas();

    }

    public void mostrarFichas(){

        inicioBoton.setVisible(false);

        int columna = 0;

        for (int i = 0; i < ListaDeFichas.fichasJugador1.size(); i++) {

            Ficha ficha = (Ficha) ListaDeFichas.fichasJugador1.get(i);

            ImageView imagenFicha = new ImageView(ficha.getImagenFicha());

            imagenFicha.setPreserveRatio(true);
            imagenFicha.setFitWidth(200);

            GridPane.setHalignment(imagenFicha, HPos.CENTER);
            GridPane.setValignment(imagenFicha, VPos.CENTER);

            if (i >= 7) {

                gridFichas.addRow(i, imagenFicha);
                gridFichas.setPrefHeight(gridFichas.getPrefHeight() + 116);

            } else {

                gridFichas.add(imagenFicha, columna, i);

            }

        }

    }

}
