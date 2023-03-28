package com.example.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class TemaPrincipal {

    public void reproducirTemaPrincipal() {

        File urlMusica = new File("/Users/gaillopez/Desktop/OCTAVO SEMESTRE /Videojuegos/ProyectoDomino2.0/ProyectoDomino2/src/main/resources/audio/AllemIversomTheRidge.wav");

        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(urlMusica);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            System.out.println("Reproduciendo");

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
