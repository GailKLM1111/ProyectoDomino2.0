package codigo.proyectodomino2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        File urlMusica = new File("/Users/gaillopez/Desktop/OCTAVO SEMESTRE /Videojuegos/ProyectoDomino2.0/ProyectoDomino2/src/main/resources/audio/AllemIversomTheRidge.wav");
        String urlMusicaTemp = urlMusica.toURI().toURL().toExternalForm();

        File urlMusica2 = new File(urlMusicaTemp);

        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(urlMusica);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }


    }

    public static void main(String[] args) {
        launch();
    }

}