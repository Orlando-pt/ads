package pt.up.fe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pt.up.fe.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EventController {

    // Add a public no-args constructor
    public EventController() {
    }

    public void goToMain(ActionEvent event) throws IOException {
        Scene scene = new Scene(App.loadFXML("main"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void goToScreen(ActionEvent event) throws IOException {
        String name = ((Button) event.getSource()).getId();

        Scene scene = new Scene(App.loadFXML(name));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void initialize(URL url, ResourceBundle resources) {
    }
}
