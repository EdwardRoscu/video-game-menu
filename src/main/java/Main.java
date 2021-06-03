import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import services.AudioService;
import services.FileSystemService;
import services.PlayerService;
import services.DisplayService;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void init() {
        PlayerService.initDatabase();
        AudioService.start();
        FileSystemService.getSettings();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("main_menu.fxml")));
        root.setEffect(DisplayService.getColorAdjust());
        primaryStage.setTitle("Game title");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setMinWidth(1280);
        primaryStage.setMinHeight(720);
        primaryStage.show();
    }

    @Override
    public void stop() {
        FileSystemService.saveSettings();
        PlayerService.closeDatabase();
    }

    public static void main(String[] args) { launch(args); }
}
