package com.example.proyectodomino2;

import com.example.modelo.Ficha;
import com.example.modelo.ListaDeFichas;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainController {

    @FXML
    private VBox pantallaInicio;

    @FXML
    private ImageView jugarBoton;

    @FXML
    void jugar(MouseEvent event) {

        try {
            Parent noJuagadores = FXMLLoader.load(getClass().getResource("noJugadores.fxml"));

            Scene escenaNueva = new Scene(noJuagadores);

            Stage escenaActual = (Stage) pantallaInicio.getScene().getWindow();
            escenaActual.setScene(escenaNueva);

        } catch (Exception e) {
            System.out.println("Error al cargar la ventana");
        }

    }

    @FXML
    void obscurecer(MouseEvent event) {

//        Lighting efectoDeLuz = new Lighting();
//        efectoDeLuz.setDiffuseConstant(0.5);
//        efectoDeLuz.setSpecularConstant(0.5);
//        efectoDeLuz.setSpecularExponent(0.5);
//        efectoDeLuz.setSurfaceScale(0.5);
//
//        jugarBoton.setEffect(efectoDeLuz);


        Light.Distant light = new Light.Distant();
        light.setAzimuth(-135.0);

        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);

        jugarBoton.setEffect(lighting);

    }

    @FXML
    void aclarar(MouseEvent event) {

        jugarBoton.setEffect(null);

    }

    public static void repartirFichasPorJugador (ArrayList lj) {

        int i=0;

        int contador = 0;
        Ficha fichita = null;
        int indice = 0;

        int maxFichas = 7;

        for (contador = 0; contador < maxFichas; contador++) {

            indice = ListaDeFichas.getIndiceFicha();

            fichita = (Ficha) ListaDeFichas.fichasTotales.get(indice);
            fichita.setEstado(false);
            lj.add(fichita);

        }

    }

    public static void crearFichas() {

        int valorMaximo = 6;

        for(int valor1 = 0; valor1 <= valorMaximo ; valor1++) {

            for(int valor2 = valor1; valor2 <= valorMaximo; valor2++) {

                Ficha f = new Ficha(valor1,valor2,true,true);
                ListaDeFichas.agregarFichas(ListaDeFichas.fichasTotales,f);

            }
        }

    }

}
