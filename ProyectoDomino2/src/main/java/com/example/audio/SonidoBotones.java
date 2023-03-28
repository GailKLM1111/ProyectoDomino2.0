package com.example.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SonidoBotones {

    public void reproducirSonidoBotones() {

        double random = 0;
        int numero = 0;

        do {

            random = Math.random();
            random = random * 100;
            numero = (int) random;

        } while (numero >= 5 || numero <= 0);

        File urlMusica = new File("/Users/gaillopez/Desktop/OCTAVO SEMESTRE /Videojuegos/ProyectoDomino2.0/ProyectoDomino2/src/main/resources/audio/boton" + numero + ".wav");

        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(urlMusica);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (UnsupportedAudioFileException e) {
            System.out.println("Error");
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            System.out.println("Error");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
