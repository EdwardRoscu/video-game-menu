package services;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class AudioService {

    private static MediaPlayer mediaPlayer;

    public static void start() {
        String musicFile = "src/main/resources/music/main_menu.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public static void setVolume(double value) {
        if (value > 100) value = 100;
        else if (value < 0) value = 0;
        mediaPlayer.setVolume(value / 100);
    }

    public static int getVolume() {
        return (int) (100 * mediaPlayer.getVolume());
    }

    public static String getSettings() {
        return  "[Audio]\n" +
                "Volume=" + getVolume() + "\n" +
                "; =[0,100]\n";
    }

}
