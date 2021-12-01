package pt.up.fe.controllers.events;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import pt.up.fe.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateEventController implements Initializable {

    @FXML
    private Button button;

    @FXML
    private ComboBox<String> comboBox;

    // Add a public no-args constructor
    public CreateEventController() {
    }

    @FXML
    public void initialize(URL url, ResourceBundle resources) {
        // Initialize combobox
        comboBox.getItems().clear();
        comboBox.getItems().addAll("Birth", "Death", "Emigration", "Marriage", "Residence", "Custom");
        comboBox.getSelectionModel().select("Birth");
    }

    public void createNewEvent(ActionEvent event) throws IOException {
        String name = comboBox.getValue().toLowerCase() + "Event";

        Scene scene = new Scene(App.loadFXML(name));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void goBack(ActionEvent event) throws IOException {
        Scene scene = new Scene(App.loadFXML("event"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
