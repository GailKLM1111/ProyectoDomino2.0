package com.example.proyectodomino2;

import com.example.modelo.ListaDeFichas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import com.example.modelo.Ficha;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

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

    private int[] coordenadasTablero1 = new int[]{3,3};

    private int[] coordenadasTablero2 = new int[]{3,3};

    @FXML
    void repartirFichas(ActionEvent event) {

        crearFichas();

        repartirFichasPorJugador(ListaDeFichas.fichasJugador1);
        repartirFichasPorJugador(ListaDeFichas.fichasComputadora);

        mostrarFichas();
        mostrarFichasCompu();

        ArrayList<Integer> lista = quienEmpieza();

        dialogo.setVisible(true);

        Ficha ficha = null;
        Ficha fichaEnJuego = null;

        if (lista.get(0) == 1) {

            dialogo.setContentText("Empieza el jugador");

            for (int i = 0; i < ListaDeFichas.fichasJugador1.size(); i++) {

                ficha = (Ficha) ListaDeFichas.fichasJugador1.get(i);

                if (ficha.getMula() == lista.get(1)) {

                    ListaDeFichas.fichasJugador1.remove(i);
                    mostrarFichasTablero(ficha);
                    mostrarFichas();
                    fichaEnJuego = ficha;

                }

            }

            evaluarJugador(ListaDeFichas.fichasComputadora, fichaEnJuego, 0);

        } else {

            dialogo.setContentText("Empieza la computadora");

            for (int i = 0; i < ListaDeFichas.fichasComputadora.size(); i++) {

                ficha = (Ficha) ListaDeFichas.fichasComputadora.get(i);

                if (ficha.getMula() == lista.get(1)) {

                    ListaDeFichas.fichasComputadora.remove(i);
                    mostrarFichasTablero(ficha);
                    mostrarFichasCompu();
                    fichaEnJuego = ficha;

                }

            }

            evaluarJugador(ListaDeFichas.fichasJugador1, fichaEnJuego, 1);

        }



    }

    public void mostrarFichas(){

        inicioBoton.setVisible(false);

        limpiarFichas();

        int columna = 0;

        for (int i = 0; i < ListaDeFichas.fichasJugador1.size(); i++) {

            Ficha ficha = (Ficha) ListaDeFichas.fichasJugador1.get(i);

            ImageView imagenFicha = new ImageView(ficha.getImagenFicha());

            imagenFicha.setPreserveRatio(true);
            imagenFicha.setFitWidth(200);
            imagenFicha.setLayoutX(30);

            Pane panel = new Pane(imagenFicha);
            panel.setPrefWidth(Region.USE_COMPUTED_SIZE);
            panel.setPrefHeight(Region.USE_COMPUTED_SIZE);

            GridPane.setHalignment(panel, HPos.CENTER);
            GridPane.setValignment(panel, VPos.CENTER);

            if (i >= 7) {

                gridFichas.addRow(i, panel);
                gridFichas.setPrefHeight(gridFichas.getPrefHeight() + 116);

            } else {

                gridFichas.add(panel, columna, i);

            }



        }

    }

    public  void limpiarFichas() {

        gridFichas.getChildren().clear();

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

    public void mostrarFichasTablero(Ficha ficha, int direccion, boolean invertir) {

        limpiarTablero();

        ImageView imagenFicha = new ImageView(ficha.getImagenFicha());

        imagenFicha.setPreserveRatio(true);
        imagenFicha.setFitWidth(70);

        GridPane.setHalignment(imagenFicha, HPos.CENTER);
        GridPane.setValignment(imagenFicha, VPos.CENTER);

        if (ficha.getMula() != 0) {

            imagenFicha.setRotate(90);

        }

        if (invertir) {

            imagenFicha.setRotate(180);

        }

        if (direccion < 0) {

            coordenadasTablero1[0] = coordenadasTablero1[0] + direccion;
            gridTablero.add(imagenFicha, coordenadasTablero1[0], coordenadasTablero1[1]);

        } else {

            coordenadasTablero2[0] = coordenadasTablero2[0] + direccion;
            gridTablero.add(imagenFicha, coordenadasTablero2[0], coordenadasTablero2[1]);

        }


    }

    public void mostrarFichasCompu () {

        System.out.println("Fichas de la computadora: ");

        for (int i = 0; i < ListaDeFichas.fichasComputadora.size(); i++) {

            Ficha ficha = (Ficha) ListaDeFichas.fichasComputadora.get(i);

            System.out.println(ficha.getV1() + ":|:" + ficha.getV2() + " ");

        }

    }

    public void evaluarJugador (ArrayList fichasJugador, Ficha fichaEnJuego, int jugador) {

        ArrayList<Integer> listaFichasValidas = buscarFichasValidas(fichasJugador, fichaEnJuego);

        if (listaFichasValidas.size() == 0) {

            if (jugador == 1) {

                dialogo.setContentText("No tienes fichas validas, toca la computadora");

            } else {

                dialogo.setContentText("No tienes fichas validas, toca el jugador");

            }

        } else {

            if (jugador == 1) {

                dialogo.setContentText("Puedes jugar con las fichas marcadas");
                marcarFichas(listaFichasValidas);
                clickEnFicha(listaFichasValidas, fichasJugador, fichaEnJuego);


            } else {

                System.out.println("Fichas validas: " + listaFichasValidas);

            }

        }

    }

    public void marcarFichas(ArrayList<Integer> listaFichasValidas) {

        for (int i = 0; i < listaFichasValidas.size(); i++) {

            int posicion = listaFichasValidas.get(i);

            gridFichas.getChildren().get(posicion).setStyle("-fx-border-color: red; -fx-border-width: 5px;");
            gridFichas.getChildren().get(posicion).setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    gridFichas.getChildren().get(posicion).setStyle("-fx-border-color: green; -fx-border-width: 5px;");

                }
            });

            gridFichas.getChildren().get(posicion).setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    gridFichas.getChildren().get(posicion).setStyle("-fx-border-color: red; -fx-border-width: 5px;");

                }
            });

        }

    }

//    public void colocarFicha(){
//
//        Ficha ficha = (Ficha) ListaDeFichas.fichasJugador1.get(posicion);
//
//        ListaDeFichas.fichasJugador1.remove(posicion);
//        mostrarFichasTablero(ficha);
//        mostrarFichas();
//
//        evaluarJugador(ListaDeFichas.fichasComputadora, ficha, 0);
//
//    }

    public void clickEnFicha(ArrayList<Integer> listaFichasValidas, ArrayList fichasJugador, Ficha fichaEnJuego) {

        limpiarTablero();

        int izquierda = -1;
        int derecha = 1;

        for (int i = 0; i < listaFichasValidas.size(); i++) {

            int posicion = listaFichasValidas.get(i);

            gridFichas.getChildren().get(posicion).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    int posicion = gridFichas.getChildren().indexOf(event.getSource());

                    Ficha ficha = (Ficha) fichasJugador.get(posicion);
                    Ficha fichaInvertida = new Ficha();
                    fichaInvertida.setV1(ficha.getV2());
                    fichaInvertida.setV2(ficha.getV1());
                    fichaInvertida.setImagenFicha(ficha.getImagenFicha());

                    dialogo.setContentText("Ficha seleccionada: " + ficha.getV1() + ":|:" + ficha.getV2());

                    Pane panel = null;

                    if (validarIzquierda(ficha, fichaEnJuego)) {
                        panel = mostrarCasillasValidas(izquierda);
                        clickEnCasilla(panel, ficha, izquierda, false);
                    }

                    if (validarDerecha(ficha, fichaEnJuego)) {
                        panel = mostrarCasillasValidas(derecha);
                        clickEnCasilla(panel, ficha, derecha, false);
                    }

                    if (validarIzquierda(fichaInvertida, fichaEnJuego)) {
                        panel = mostrarCasillasValidas(izquierda);
                        clickEnCasilla(panel, fichaInvertida, izquierda, true);
                    }

                    if (validarDerecha(fichaInvertida, fichaEnJuego)) {
                        panel = mostrarCasillasValidas(derecha);
                        clickEnCasilla(panel, fichaInvertida, derecha, true);
                    }


                }
            });

        }

    }

    public Pane mostrarCasillasValidas (int direccion) {

        Pane panel = new Pane();
        panel.setPrefWidth(Region.USE_COMPUTED_SIZE);
        panel.setPrefHeight(Region.USE_COMPUTED_SIZE);

        panel.setStyle("-fx-border-color: yellow; -fx-border-width: 2px;");
        panel.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                panel.setStyle("-fx-border-color: green; -fx-border-width: 2px;");

            }
        });

        panel.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                panel.setStyle("-fx-border-color: yellow; -fx-border-width: 2px;");

            }
        });

        GridPane.setHalignment(panel, HPos.CENTER);
        GridPane.setValignment(panel, VPos.CENTER);

        if (direccion < 0) {

            gridTablero.add(panel, coordenadasTablero1[0] - 1, coordenadasTablero1[1]);

        } else {

            gridTablero.add(panel, coordenadasTablero2[0] + 1, coordenadasTablero2[1]);

        }

        return panel;
    }


    public void limpiarTablero () {

        for (int i = 0; i < gridTablero.getChildren().size(); i++) {

            if (!(gridTablero.getChildren().get(i) instanceof ImageView)) {

                gridTablero.getChildren().remove(i);

            }

        }

    }

    public void clickEnCasilla (Pane panel, Ficha ficha, int direccion, boolean invertida) {

        panel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (invertida) {
                    System.out.println("Se inserta la ficha de la siguiente manera: " + ficha.getV2() + ":|:" + ficha.getV1());
                } else {
                    System.out.println("Se inserta la ficha de la siguiente manera: " + ficha.getV1() + ":|:" + ficha.getV2());
                }

                mostrarFichasTablero(ficha, direccion, invertida);

            }
        });

    }

}
