package codigo.proyectodomino2;

import codigo.modelo.ListaDeFichas;
import codigo.modelo.Ficha;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.ArrayList;

import static codigo.proyectodomino2.MainController.*;

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

    int izquierda = -1;
    int derecha = 1;
    private int[] coordenadasTablero1 = new int[]{3,3};
    private int[] coordenadasTablero2 = new int[]{3,3};
    private int turno;

    Ficha fichaEnJuego = new Ficha();

    @FXML
    void repartirFichas(ActionEvent event) {

        crearFichas();

        repartirFichasPorJugador(ListaDeFichas.fichasJugador1);
        repartirFichasPorJugador(ListaDeFichas.fichasComputadora);

        mostrarFichas();
        mostrarFichasCompu();

        ArrayList<Integer> lista = quienEmpieza();

        dialogo.setVisible(true);
        dialogo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-font-family: 'Arial'; -fx-background-color: #408000;");

        Ficha ficha = null;
        Ficha fichaTemp = null;

        if (lista.get(0) == 1) {

            dialogo.setContentText("Empieza el jugador");

            for (int i = 0; i < ListaDeFichas.fichasJugador1.size(); i++) {

                ficha = (Ficha) ListaDeFichas.fichasJugador1.get(i);

                if (ficha.getMula() == lista.get(1)) {

                    ListaDeFichas.fichasJugador1.remove(i);
                    mostrarFichasTablero(ficha);
                    mostrarFichas();
                    fichaEnJuego = ficha;
                    fichaTemp = ficha;

                }

            }

            evaluarJugador(ListaDeFichas.fichasComputadora, 0);

//            do {
//
//                if (turno == 1) {
//                    evaluarJugador(ListaDeFichas.fichasJugador1, fichaEnJuego, 1);
//                    fichaTemp = fichaEnJuego;
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//
//                if (fichaTemp != fichaEnJuego && turno == 0 ){
//                    evaluarJugador(ListaDeFichas.fichasComputadora, fichaEnJuego, 0);
//                }
//
//            } while (!evaluarGanador(ListaDeFichas.fichasJugador1) || !evaluarGanador(ListaDeFichas.fichasComputadora));

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

            evaluarJugador(ListaDeFichas.fichasJugador1, 1);


//            fichaTemp = fichaEnJuego;
//
//            do {
//
//                if (fichaTemp != fichaEnJuego && turno == 0){
//                    evaluarJugador(ListaDeFichas.fichasComputadora, fichaEnJuego, 0);
//                }
//
//                if (turno == 1){
//                    evaluarJugador(ListaDeFichas.fichasJugador1, fichaEnJuego, 1);
//                    fichaTemp = fichaEnJuego;
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//
//            } while (!evaluarGanador(ListaDeFichas.fichasJugador1) || !evaluarGanador(ListaDeFichas.fichasComputadora));

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

        ImageView imagenFicha = new ImageView(ficha.getImagenFicha());

        imagenFicha.setPreserveRatio(true);
        imagenFicha.setFitWidth(70);

        GridPane.setHalignment(imagenFicha, HPos.CENTER);
        GridPane.setValignment(imagenFicha, VPos.CENTER);

        if (ficha.getMula() != 0 || (ficha.getV1() == 0 && ficha.getV2() == 0)) {

            imagenFicha.setRotate(90);

        }

        if (invertir) {

            imagenFicha.setRotate(180);

        }

        if (direccion < 0) {

            if (coordenadasTablero1[0] == 0) {
                if (coordenadasTablero1[1] == 0) {
                    limpiarTablero();
                    coordenadasTablero1[0] = coordenadasTablero1[0] + 1;
                    if (invertir) {
                        imagenFicha.setRotate(0);
                    } else {
                        imagenFicha.setRotate(180);
                    }
                    gridTablero.add(imagenFicha, coordenadasTablero1[0], coordenadasTablero1[1]);
                    fichaEnJuego = nuevaFichaEnJuego(ficha.getV1(), fichaEnJuego.getV2());
                } else  {
                    limpiarTablero();
                    coordenadasTablero1[1] = coordenadasTablero1[1] + direccion;
                    if (invertir) {
                        imagenFicha.setRotate(270);
                    } else {
                        imagenFicha.setRotate(90);
                    }
                    gridTablero.add(imagenFicha, coordenadasTablero1[0], coordenadasTablero1[1]);
                    fichaEnJuego = nuevaFichaEnJuego(ficha.getV1(), fichaEnJuego.getV2());
                }
            } else {

                if (coordenadasTablero1[1] == 0) {
                    limpiarTablero();
                    coordenadasTablero1[0] = coordenadasTablero1[0] + 1;
                    if (invertir) {
                        imagenFicha.setRotate(0);
                    } else {
                        imagenFicha.setRotate(180);
                    }
                    gridTablero.add(imagenFicha, coordenadasTablero1[0], coordenadasTablero1[1]);
                    fichaEnJuego = nuevaFichaEnJuego(ficha.getV1(), fichaEnJuego.getV2());
                } else {
                    limpiarTablero();
                    coordenadasTablero1[0] = coordenadasTablero1[0] + direccion;
                    gridTablero.add(imagenFicha, coordenadasTablero1[0], coordenadasTablero1[1]);
                    fichaEnJuego = nuevaFichaEnJuego(ficha.getV1(), fichaEnJuego.getV2());
                }

            }

        } else {

            if (coordenadasTablero2[0] == 6) {
                if (coordenadasTablero2[1] == 6) {
                    limpiarTablero();
                    coordenadasTablero2[0] = coordenadasTablero2[0] - 1;
                    if (invertir) {
                        imagenFicha.setRotate(0);
                    } else {
                        imagenFicha.setRotate(180);
                    }
                    gridTablero.add(imagenFicha, coordenadasTablero2[0], coordenadasTablero2[1]);
                    fichaEnJuego = nuevaFichaEnJuego(fichaEnJuego.getV1(), ficha.getV2());
                } else  {
                    limpiarTablero();
                    coordenadasTablero2[1] = coordenadasTablero2[1] + direccion;
                    if (invertir) {
                        imagenFicha.setRotate(270);
                    } else {
                        imagenFicha.setRotate(90);
                    }
                    gridTablero.add(imagenFicha, coordenadasTablero2[0], coordenadasTablero2[1]);
                    fichaEnJuego = nuevaFichaEnJuego(fichaEnJuego.getV1(), ficha.getV2());
//                    fichaEnJuego = nuevaFichaEnJuego(ficha.getV2(), fichaEnJuego.getV1());
                }
            } else {

                if (coordenadasTablero2[1] == 6) {
                    limpiarTablero();
                    coordenadasTablero2[0] = coordenadasTablero2[0] - 1;
                    if (invertir) {
                        imagenFicha.setRotate(0);
                    } else {
                        imagenFicha.setRotate(180);
                    }
                    gridTablero.add(imagenFicha, coordenadasTablero2[0], coordenadasTablero2[1]);
                    fichaEnJuego = nuevaFichaEnJuego(fichaEnJuego.getV1(), ficha.getV2());
                } else {
                    limpiarTablero();
                    coordenadasTablero2[0] = coordenadasTablero2[0] + direccion;
                    gridTablero.add(imagenFicha, coordenadasTablero2[0], coordenadasTablero2[1]);
                    fichaEnJuego = nuevaFichaEnJuego(fichaEnJuego.getV1(), ficha.getV2());
//                    fichaEnJuego = nuevaFichaEnJuego(ficha.getV2(), fichaEnJuego.getV1());
                    // verificar que sea correcto o los valores invertidos
                }
            }

        }

    }

    public void mostrarFichasCompu () {

        System.out.println("Fichas de la computadora: ");

        for (int i = 0; i < ListaDeFichas.fichasComputadora.size(); i++) {

            Ficha ficha = (Ficha) ListaDeFichas.fichasComputadora.get(i);

            System.out.println(ficha.getV1() + ":|:" + ficha.getV2() + " ");

        }

    }

    public void evaluarJugador (ArrayList fichasJugador, int jugador) {

        if (!evaluarGanador(fichasJugador)) {

            ArrayList<Integer> listaFichasValidas = buscarFichasValidas(fichasJugador, fichaEnJuego);

            if (listaFichasValidas.size() == 0) {

                if (jugador == 1) {

                    dialogo.setContentText("No tienes fichas validas, tienes que comer");
                    System.out.println("El jugador no tiene fichas validas, tiene que comer");
                    comerFichas(fichasJugador, jugador);

                } else {

                    System.out.println("No hay fichas validas, la computadora tiene que comer");
                    comerFichas(fichasJugador, jugador);

                }

            } else {

                if (jugador == 1) {

                    dialogo.setContentText("Puedes jugar con las fichas marcadas");
                    marcarFichas(listaFichasValidas);
                    clickEnFicha(listaFichasValidas, fichasJugador, fichaEnJuego);


                } else {

                    mostrarFichasCompu();
                    System.out.println("Fichas validas: " + listaFichasValidas);
                    Ficha fichaCompu = (Ficha) fichasJugador.get(listaFichasValidas.get(0));
                    fichasJugador.remove(fichaCompu);
                    tiradaCompu(fichaCompu);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    if (evaluarGanador(fichasJugador)) {
                        dialogo.setContentText("Perdiste");
                        System.out.println("Perdiste");
                    } else {
                        evaluarJugador(ListaDeFichas.fichasJugador1, 1);
                    }

                }

            }

        } else {

            if (jugador == 1) {

                dialogo.setContentText("Ganaste");
                System.out.println("Ganaste");

            } else {

                dialogo.setContentText("Perdiste");
                System.out.println("Perdiste");

            }

        }

    }

    private void comerFichas(ArrayList fichasJugador, int jugador) {

        if (jugador == 1) {
            limpiarFichas();
            crearBoton(fichasJugador, jugador);
        } else {
            tomarFicha(fichasJugador);
            evaluarJugador(fichasJugador, jugador);
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

    public void clickEnFicha(ArrayList<Integer> listaFichasValidas, ArrayList fichasJugador, Ficha fichaEnJuego) {

        for (int i = 0; i < listaFichasValidas.size(); i++) {

            int posicion = listaFichasValidas.get(i);

            gridFichas.getChildren().get(posicion).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    limpiarTablero();

                    int posicion = gridFichas.getChildren().indexOf(event.getSource());

                    Ficha ficha = (Ficha) fichasJugador.get(posicion);
                    Ficha fichaInvertida = new Ficha();
                    fichaInvertida.setV1(ficha.getV2());
                    fichaInvertida.setV2(ficha.getV1());
                    fichaInvertida.setFichaURL(ficha.getFichaURL());
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

            if (coordenadasTablero1[0] == 0) {

                if (coordenadasTablero1[1] == 0) {
                    gridTablero.add(panel, coordenadasTablero1[0] + 1, coordenadasTablero1[1]);
                } else {
                    gridTablero.add(panel, coordenadasTablero1[0], coordenadasTablero1[1] - 1);
                }

            } else {

                if (coordenadasTablero1[1] == 0) {
                    gridTablero.add(panel, coordenadasTablero1[0] + 1, coordenadasTablero1[1]);
                } else {
                    gridTablero.add(panel, coordenadasTablero1[0] + direccion, coordenadasTablero1[1]);
                }

            }

        } else {

            if (coordenadasTablero2[0] == 6) {

                if (coordenadasTablero2[1] == 6) {
                    gridTablero.add(panel, coordenadasTablero2[0] - 1, coordenadasTablero2[1]);
                } else {
                    gridTablero.add(panel, coordenadasTablero2[0], coordenadasTablero2[1] + 1);
                }

            } else {

                if (coordenadasTablero2[1] == 6) {
                    gridTablero.add(panel, coordenadasTablero2[0] - 1, coordenadasTablero2[1]);
                } else {
                    gridTablero.add(panel, coordenadasTablero2[0] + direccion, coordenadasTablero2[1]);
                }

            }

        }

        return panel;
    }

    public void limpiarTablero () {

        for (int i = 0; i < gridTablero.getChildren().size(); i++) {

            if (!(gridTablero.getChildren().get(i) instanceof ImageView)) {

                gridTablero.getChildren().remove(i);

            }

        }

        gridTablero.setGridLinesVisible(true);

    }

    public void clickEnCasilla (Pane panel, Ficha ficha, int direccion, boolean invertida) {

        panel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (invertida) {
                    System.out.println("Se inserta la ficha de la siguiente manera: " + ficha.getV1() + ":|:" + ficha.getV2());
                } else {
                    System.out.println("Se inserta la ficha de la siguiente manera: " + ficha.getV1() + ":|:" + ficha.getV2());
                }

                limpiarTablero();
                mostrarFichasTablero(ficha, direccion, invertida);
                quitarFicha(ficha);
                mostrarFichas();

                if (evaluarGanador(ListaDeFichas.fichasJugador1)) {
                    dialogo.setContentText("Ganaste");
                    System.out.println("Ganaste");
                } else {
                    evaluarJugador(ListaDeFichas.fichasComputadora, 0);
                }

            }
        });

    }

    public void quitarFicha (Ficha ficha) {

        Ficha fichaDelJugador = null;

        for (int i = 0; i < ListaDeFichas.fichasJugador1.size(); i++) {

            fichaDelJugador = (Ficha) ListaDeFichas.fichasJugador1.get(i);

            if (ficha.getFichaURL().equals(fichaDelJugador.getFichaURL())) {

                ListaDeFichas.fichasJugador1.remove(i);

            }

        }


    }

    public void tiradaCompu (Ficha ficha){

        Ficha fichaInvertida = new Ficha();
        fichaInvertida.setV1(ficha.getV2());
        fichaInvertida.setV2(ficha.getV1());
        fichaInvertida.setFichaURL(ficha.getFichaURL());
        fichaInvertida.setImagenFicha(ficha.getImagenFicha());

        System.out.println("Ficha seleccionada: " + ficha.getV1() + ":|:" + ficha.getV2());

        if (validarDerecha(ficha, fichaEnJuego)) {
            mostrarFichasTablero(ficha, derecha, false);
            return;
        }

        if (validarIzquierda(ficha, fichaEnJuego)) {
            mostrarFichasTablero(ficha, izquierda, false);
            return;
        }

        if (validarDerecha(fichaInvertida, fichaEnJuego)) {
            mostrarFichasTablero(fichaInvertida, derecha, true);
            return;
        }

        if (validarIzquierda(fichaInvertida, fichaEnJuego)) {
            mostrarFichasTablero(fichaInvertida, izquierda, true);
        }

    }

    public void crearBoton (ArrayList fichasJugador, int jugador) {

//        crearGridSimetrico();

        Button boton = new Button("Tomar ficha");
        boton.setPrefWidth(Region.USE_COMPUTED_SIZE);
        boton.setPrefHeight(Region.USE_COMPUTED_SIZE);

        boton.setOnMouseClicked (new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                tomarFicha(fichasJugador);
                mostrarFichas();
                evaluarJugador(fichasJugador, jugador);

            }
        });

        gridFichas.add(boton, 0, 3);

    }

}
