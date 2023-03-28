package com.example.modelo;

import java.util.ArrayList;

public class ListaDeFichas {

    public static ArrayList fichasTotales = new ArrayList();
    public static ArrayList fichasJugador1 = new ArrayList();
    public static ArrayList fichasJugador2 = new ArrayList();
    public static ArrayList fichasJugador3 = new ArrayList();
    public static ArrayList fichasJugador4 = new ArrayList();
    public static ArrayList fichasComputadora = new ArrayList();

    public static int getIndiceFicha () {

        int indice = 0;
        double random = 0;

        do {

            random = Math.random();
            random = random * 100;
            indice = (int) random;

        } while (indice >= 28);

        return indice;

    }

    public static void agregarFichas(ArrayList listaFichas, Ficha ficha) {

        listaFichas.add(ficha);

    }

    public static void limpiarArreglos() {

        fichasTotales.clear();
        fichasJugador1.clear();
        fichasJugador2.clear();
        fichasJugador3.clear();
        fichasJugador4.clear();
        fichasComputadora.clear();

    }

}
