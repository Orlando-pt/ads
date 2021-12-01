package pt.up.fe.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import pt.up.fe.helpers.CustomSceneHelper;

import java.net.URL;
import java.util.ResourceBundle;

public class EventController {

    // Add a public no-args constructor
    public EventController() {
    }

    @FXML
    private void goToAnotherPage(MouseEvent event) {
        String buttonID = CustomSceneHelper.getSourceID(event.getSource());
        CustomSceneHelper.bringNodeToFront(buttonID, "Page");
    }

    @FXML
    private void initialize(URL url, ResourceBundle resources) {
    }
}
