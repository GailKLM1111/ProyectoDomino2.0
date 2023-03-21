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

            if (!fichita.getEstado()) {
                contador--;
            } else {
                fichita.setEstado(false);
                lj.add(fichita);
            }

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

    public static ArrayList<Integer> quienEmpieza () {

        ArrayList<Integer> mulasPorJugador = new ArrayList<>();

        mulasPorJugador.add(evaluarMulas(ListaDeFichas.fichasJugador1));

        if (ListaDeFichas.fichasJugador2.size() != 0) {
            mulasPorJugador.add(evaluarMulas(ListaDeFichas.fichasJugador2));
        }

        if (ListaDeFichas.fichasJugador3.size() != 0) {
            mulasPorJugador.add(evaluarMulas(ListaDeFichas.fichasJugador3));
        }

        if (ListaDeFichas.fichasJugador4.size() != 0) {
            mulasPorJugador.add(evaluarMulas(ListaDeFichas.fichasJugador4));
        }

        if (ListaDeFichas.fichasComputadora.size() != 0) {
            mulasPorJugador.add(evaluarMulas(ListaDeFichas.fichasComputadora));
        }

        int indiceDeQuienEmpieza = 0;

        for (int x = 0; x < mulasPorJugador.size(); x++) {
            if (mulasPorJugador.get(x) > mulasPorJugador.get(indiceDeQuienEmpieza)) {
                indiceDeQuienEmpieza = x;
            }
        }

        int mayor = mulasPorJugador.get(indiceDeQuienEmpieza);

        ArrayList<Integer> lista = new ArrayList<>();
        lista.add(indiceDeQuienEmpieza + 1);
        lista.add(mayor);

        return lista;

    }

    public static int evaluarMulas (ArrayList lj) {

        int mulaValor = 0;
        int mulaValorTemp = 0;

        for (int i=0;i< lj.size();i++) {

            Ficha ficha = (Ficha)lj.get(i);
            mulaValor = ficha.getMula();

            if (mulaValor > mulaValorTemp) {
                mulaValorTemp = mulaValor;
            }

        }

        mulaValor = mulaValorTemp;

        return mulaValor;

    }

//    public static void evalularJugador (ArrayList lj, String j, int z) {
//
//        int r = -1;
//        System.out.println("Jugador(a) "+j+" Puedes jugar con la(s) ficha(s):");
//
//        for (int i=0;i< lj.size();i++) {
//
//            Ficha f=null;
//            f=(Ficha)lj.get(i);
//
//            if (true==f.getJugar()) {
//
//                if (v1==f.getV1()||v1==f.getV2()||v2==f.getV1()||v2==f.getV2()) {
//
//                    int h=i+1;
//                    System.out.println("|" + f.getV1() + ":" + f.getV2() +"| .........."+h+" \n");
//                    r++;
//                    sb=0;
//
//                }
//
//            }
//
//        }
//
//        if (r==-1) {
//
//            System.out.println("No tienes fichas para jugar");
//            tomar_ficha(lj,j,z);
//
//        } else {
//
//            System.out.println("Digita el numero que indica tu ficha para jugarla");
//            int t=Leer.datoInt();
//            t=t-1;
//            Ficha fi=null;
//            fi=(Ficha)lj.get(t);
//
//            if (v1!=v2) {
//
//                if (v1==fi.getV1())
//                    v1=fi.getV2();
//                else
//                if (v1==fi.getV2())
//                    v1=fi.getV1();
//                else
//                if (v2==fi.getV1())
//                    v2=fi.getV2();
//                else
//                if (v2==fi.getV2())
//                    v2=fi.getV1();
//
//            } else {
//
//                if (v1==fi.getV1()) {
//                    v2=fi.getV2();
//                } else
//                    v1=fi.getV1();
//
//            }
//
//            fi.setJugar(false);
//
//        }
//    }

    public static ArrayList<Integer> buscarFichasValidas (ArrayList fichasJugador, Ficha fichaEnJuego) {

        Ficha ficha = null;
        ArrayList<Integer> listaFichasValidas = new ArrayList<>();

        for (int i = 0; i< fichasJugador.size(); i++) {

            ficha = (Ficha)fichasJugador.get(i);

            if (ficha.getJugar()) {

                if (fichaEnJuego.getV1() == ficha.getV1() || fichaEnJuego.getV1() == ficha.getV2()
                        || fichaEnJuego.getV2() == ficha.getV1() || fichaEnJuego.getV2() == ficha.getV2()) {

                    listaFichasValidas.add(i);

                }

            }

        }

        return listaFichasValidas;

    }

    public static int continuarJugando (int jugador2, int jugador3, int jugador4, int computadora) {

        int continuar1 = 0;
        int continuar2 = 0;
        int continuar3 = 0;
        int continuar4 = 0;
        int continuarC = 0;

        int noJuega = 99;

        int ganador = 0;

        Ficha ficha = null;

        for (int i=0;i< ListaDeFichas.fichasJugador1.size();i++) {
            ficha = (Ficha)ListaDeFichas.fichasJugador1.get(i);
            if (ficha.getJugar()) {
                continuar1++;
            }
        }

        if (jugador2 != 0) {
            for (int j=0;j< ListaDeFichas.fichasJugador2.size();j++) {
                ficha = (Ficha)ListaDeFichas.fichasJugador2.get(j);
                if (ficha.getJugar()) {
                    continuar2++;
                }
            }
        } else {
            continuar2 = noJuega;
        }

        if (jugador3 != 0) {
            for (int k=0;k< ListaDeFichas.fichasJugador3.size();k++) {
                ficha = (Ficha)ListaDeFichas.fichasJugador3.get(k);
                if (ficha.getJugar()) {
                    continuar3++;
                }
            }
        } else {
            continuar3 = noJuega;
        }

        if (jugador4 != 0) {
            for (int l=0;l< ListaDeFichas.fichasJugador4.size();l++) {
                ficha = (Ficha)ListaDeFichas.fichasJugador4.get(l);
                if (ficha.getJugar()) {
                    continuar4++;
                }
            }
        } else {
            continuar4 = noJuega;
        }

        if (computadora != 0) {
            for (int m=0;m< ListaDeFichas.fichasComputadora.size();m++) {
                ficha = (Ficha)ListaDeFichas.fichasComputadora.get(m);
                if (ficha.getJugar()) {
                    continuarC++;
                }
            }
        } else {
            continuarC = noJuega;
        }

        if (continuar1 == 0 || continuar2 == 0 || continuar3 == 0
                || continuar4 == 0 || continuarC == 0) {

            if (continuar1 == 0)
                ganador = 1;

            if (continuar2 == 0)
                ganador = 2;

            if (continuar3 == 0)
                ganador = 3;

            if (continuar4 == 0)
                ganador = 4;

            if (continuarC == 0)
                ganador = 5;

        }

        return ganador;

    }

    public static boolean validarIzquierda (Ficha ficha, Ficha fichaEnJuego) {

        boolean validar = false;

        if (fichaEnJuego.getV1() == ficha.getV2()) {
            validar = true;
        }

        return validar;

    }

    public static boolean validarDerecha (Ficha ficha, Ficha fichaEnJuego) {

        boolean validar = false;

        if (fichaEnJuego.getV2() == ficha.getV1()) {
            validar = true;
        }

        return validar;

    }

    public static void tomarFicha(ArrayList listaFichas) {

        int bandera = 0;
        int indice = 0;

//        if (sb!=3) {

            while (bandera < 1) {

                indice = ListaDeFichas.getIndiceFicha();
                Ficha ficha = (Ficha)ListaDeFichas.fichasTotales.get(indice);

                if (ficha.getEstado()) {

                    bandera = 1;
                    ficha.setEstado(false);
                    listaFichas.add(ficha);
                    System.out.println("La ficha que tomaste es: " + ficha.getV1() + ":" + ficha.getV2());

//                    int bandera2 = 0;
//
//                    while (bandera2 < 1) {
//
//                        listaFichas.add(ficha);
//                        System.out.println("Tu ficha es");
//                        System.out.println("|" + ficha.getV1() + ":" + ficha.getV2() +"| \n");
//
//                        if(ficha.getV1()==v1||ficha.getV2()==v2||ficha.getV1()==v2||ficha.getV2()==v1)  {
//
//                            bandera++;
//                            bandera2++;
//                            evalular_jugador(listaFichas,a,z);
//
//                        } else {
//
//                            System.out.println("NO SACASTE UNA FICHA INDICADA");
//                            System.out.println("DIGITA ALGUN NUMERO PARA TOMAR OTRA");
//                            int h = Leer.datoInt();
//                            bandera2++;
//
//                        }
//
//                    }

                } else {

                    for (Object listaFicha : listaFichas) {
                        Ficha ficha2 = (Ficha) listaFicha;
                        if (ficha2.getEstado()) {
                            bandera++;
                        }
                    }

                }

            }

//        } else {
//
//            System.out.println("SE HA SERRADO EL JUEGO");
//            juego_cerrado(z);
//
//        }

    }

    public static boolean evaluarGanador (ArrayList listaFichas) {

        boolean ganador = false;

        if (listaFichas.size() == 0) {
            ganador = true;
        }

        return ganador;

    }

    public static Ficha nuevaFichaEnJuego (int v1, int v2) {

        Ficha nuevaFicha = new Ficha();

        nuevaFicha.setV1(v1);
        nuevaFicha.setV2(v2);

        return nuevaFicha;

    }

}
