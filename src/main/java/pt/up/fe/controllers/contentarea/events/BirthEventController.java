package pt.up.fe.controllers.contentarea.events;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.up.fe.dtos.events.FieldDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class BirthEventController implements Initializable  {

    @FXML
    private DatePicker birthDate;

    @FXML
    private ScrollPane birthEventPage;

    @FXML
    private TextArea description;

    @FXML
    private TextField fieldInput;

    @FXML
    private TextField maternity;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField placeBirth;

    @FXML
    private TextField relationshipInput;

    @FXML
    private TableView<FieldDTO> table_fields;

    @FXML
    private TableColumn<FieldDTO, String> col_field;

    @FXML
    private TableColumn<FieldDTO, String> col_name;

    @FXML
    private TableView<?> table_persons;

    @FXML
    private TableColumn<?, ?> col_person_name;

    @FXML
    private TableColumn<?, ?> col_relationship;

    @FXML
    void addCustomField(ActionEvent event) {
        String field = fieldInput.getText();
        String name = nameInput.getText();

        if(field.isEmpty() || name.isEmpty()) {
            return;
        }

        table_fields.getItems().add(new FieldDTO(field, name));

        fieldInput.clear();
        nameInput.clear();
    }

    @FXML
    void addCustomPerson(ActionEvent event) {

    }

    @FXML
    void addMother(ActionEvent event) {

    }

    @FXML
    void createEvent(ActionEvent event) {

    }

    @FXML
    public void initialize(URL url, ResourceBundle resources) {
        this.initFieldsTable();
    }

    private void initFieldsTable() {
        this.initFieldsCols();
    }

    private void initFieldsCols() {
        col_field.setCellValueFactory(new PropertyValueFactory<>("field"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
}
