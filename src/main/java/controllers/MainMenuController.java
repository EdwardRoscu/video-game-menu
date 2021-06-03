package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import models.Player;

import services.PlayerService;
import services.DisplayService;

import java.util.Objects;

public class MainMenuController {

    @FXML
    private javafx.scene.control.Button choosePlayerButton;

    @FXML
    private javafx.scene.control.Button startButton;

    @FXML
    private void initialize() {
        Player player = PlayerService.getCurrentPlayer();
        if (player == null) {
            startButton.setDisable(true);
        } else {
            startButton.setDisable(false);
            choosePlayerButton.setText(player.getName());
        }
    }

    @FXML
    public void choosePlayerButtonAction(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("choose_player.fxml")));
        root.setEffect(DisplayService.getColorAdjust());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Choose player");
        stage.setScene(new Scene(root, ((Node) event.getSource()).getScene().getWidth(), ((Node) event.getSource()).getScene().getHeight()));
        stage.show();
    }

    @FXML
    public void settingsButtonAction(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("settings.fxml")));
        root.setEffect(DisplayService.getColorAdjust());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Settings");
        stage.setScene(new Scene(root, ((Node) event.getSource()).getScene().getWidth(), ((Node) event.getSource()).getScene().getHeight()));
        stage.show();
    }

    @FXML
    public void exitButtonAction() { Platform.exit(); }

}
