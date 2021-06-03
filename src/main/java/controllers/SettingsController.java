package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.AudioService;
import services.DisplayService;

import java.io.IOException;

public class SettingsController extends AbstractBackButton {

    @FXML
    private javafx.scene.control.Slider volumeSlider;
    @FXML
    private javafx.scene.control.Slider brightnessSlider;

    private int volume = AudioService.getVolume();
    private int brightness = DisplayService.getBrightness();

    @FXML
    private void initialize() {
        volumeSlider.adjustValue(AudioService.getVolume());
        brightnessSlider.adjustValue(DisplayService.getBrightness());
    }

    private boolean checkModificationsAreSaved() {
        return  (volume     == (int) volumeSlider.getValue()) &&
                (brightness == (int) brightnessSlider.getValue());
    }

    @FXML
    public void backButtonAction(ActionEvent event) throws IOException {
        if (checkModificationsAreSaved()) {
            super.backButtonAction(event);
        } else {
            super.backButtonAction(event);
            /**
             * warning window
             */
        }
    }

    @FXML
    public void saveButtonAction() {
        AudioService.setVolume(volumeSlider.getValue());
        DisplayService.setBrightness(brightnessSlider.getValue());
        volume = AudioService.getVolume();
        brightness = DisplayService.getBrightness();
    }

}
