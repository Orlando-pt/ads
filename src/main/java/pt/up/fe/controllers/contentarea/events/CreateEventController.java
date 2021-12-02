package pt.up.fe.controllers.contentarea.events;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.helpers.CustomSceneHelper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateEventController implements Initializable, IContentPageController {
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
        String name = comboBox.getValue() + "Event";
        CustomSceneHelper.bringNodeToFront(name, "Page");
    }

    @Override
    public void setEventHandlers() {
    }

    @Override
    public void clearPage() {
    }
}
