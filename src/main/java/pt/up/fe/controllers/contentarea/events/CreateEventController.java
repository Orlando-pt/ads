package pt.up.fe.controllers.contentarea.events;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.events.PersonCustomEvent;
import pt.up.fe.person.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateEventController implements Initializable, IContentPageController {
    @FXML
    private ComboBox<String> comboBox;

    private Person selectedPerson;

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

        CustomSceneHelper.getNodeById(name + "Page").fireEvent(new PersonCustomEvent(PersonCustomEvent.PERSON, this.selectedPerson));
        CustomSceneHelper.bringNodeToFront(name, "Page");
    }

    @Override
    public void setEventHandlers() {
        CustomSceneHelper.getNodeById("createEventPage").addEventFilter(
                PersonCustomEvent.PERSON, new EventHandler<PersonCustomEvent>() {
                    @Override
                    public void handle(PersonCustomEvent personCustomEvent) {
                        selectedPerson = personCustomEvent.getPerson();
                    }
                });
    }

    @Override
    public void clearPage() {
    }
}
