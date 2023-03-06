package com.example.proyectodomino2;

import com.example.modelo.ListaDeFichas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import com.example.modelo.Ficha;

import java.net.MalformedURLException;
import java.util.ArrayList;

import static com.example.proyectodomino2.MainController.*;

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
    private DialogPane dialogo;

    @FXML
    void repartirFichas(ActionEvent event) throws MalformedURLException {

        crearFichas();

        repartirFichasPorJugador(ListaDeFichas.fichasJugador1);
        repartirFichasPorJugador(ListaDeFichas.fichasComputadora);

        mostrarFichas();
        mostrarFichasCompu();

        ArrayList<Integer> lista = quienEmpieza();

        dialogo.setVisible(true);

        Ficha ficha = null;

        if (lista.get(0) == 1) {

            dialogo.setContentText("Empieza el jugador");

            for (int i = 0; i < ListaDeFichas.fichasJugador1.size(); i++) {

                ficha = (Ficha) ListaDeFichas.fichasJugador1.get(i);

                if (ficha.getMula() == lista.get(1)) {

                    ListaDeFichas.fichasJugador1.remove(i);
                    mostrarFichasTablero(ficha);
                    mostrarFichas();

                }

            }

        } else {

            dialogo.setContentText("Empieza la computadora");

            for (int i = 0; i < ListaDeFichas.fichasComputadora.size(); i++) {

                ficha = (Ficha) ListaDeFichas.fichasComputadora.get(i);

                if (ficha.getMula() == lista.get(1)) {

                    ListaDeFichas.fichasComputadora.remove(i);
                    mostrarFichasTablero(ficha);
                    mostrarFichasCompu();

                }

            }

        }



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

    public void mostrarFichasTablero(Ficha ficha) {

        ImageView imagenFicha = new ImageView(ficha.getImagenFicha());

        imagenFicha.setPreserveRatio(true);
        imagenFicha.setFitWidth(70);

        GridPane.setHalignment(imagenFicha, HPos.CENTER);
        GridPane.setValignment(imagenFicha, VPos.CENTER);

        if (ficha.getMula() != 0) {

            imagenFicha.setRotate(90);

        }

        gridTablero.add(imagenFicha, 3, 3);


    }

    public void mostrarFichasCompu () {

        System.out.println("Fichas de la computadora: ");

        for (int i = 0; i < ListaDeFichas.fichasComputadora.size(); i++) {

            Ficha ficha = (Ficha) ListaDeFichas.fichasComputadora.get(i);

            System.out.println(ficha.getV1() + ":|:" + ficha.getV2() + " ");

        }

    }

}
