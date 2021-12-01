package pt.up.fe.controllers.contentarea.events;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import pt.up.fe.dtos.events.FieldDTO;
import pt.up.fe.dtos.events.PersonEventDTO;
import pt.up.fe.events.Event;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.events.PersonCustomEvent;
import pt.up.fe.helpers.events.PersonCustomEventHandler;
import pt.up.fe.person.Person;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BirthEventController implements Initializable {

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
    private TableView<PersonEventDTO> table_persons;

    @FXML
    private TableColumn<PersonEventDTO, String> col_relationship;

    @FXML
    private TableColumn<PersonEventDTO, String> col_person_name;

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
    void addPerson(ActionEvent event) {
        CustomSceneHelper.bringNodeToFront("ListPersons", "Page");

        String btnName = ((Button) event.getSource()).getText();

        CustomSceneHelper.getNodeById("listPersonsPage").addEventFilter(PersonCustomEvent.PERSON, new PersonCustomEventHandler(this.handleRelation(btnName)) {
            @Override
            public void handle(PersonCustomEvent personCustomEvent) {
                table_persons.getItems().add(new PersonEventDTO(getRelation(), personCustomEvent.getPerson())); // Add person to table
                CustomSceneHelper.getNodeById("listPersonsPage").removeEventFilter(PersonCustomEvent.PERSON, this); // Remove event handler
            }
        });
    }

    @FXML
    void createEvent(ActionEvent event) {

    }

    @FXML
    public void initialize(URL url, ResourceBundle resources) {
        this.initTables();
    }

    private void initTables() {
        this.initFieldsCols();
        this.initPersonsCols();
    }

    private void initFieldsCols() {
        col_field.setCellValueFactory(new PropertyValueFactory<>("field"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        this.editableCols();
    }

    private void initPersonsCols() {
        col_relationship.setCellValueFactory(new PropertyValueFactory<>("relationship"));
        col_person_name.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void editableCols() {
        col_field.setCellFactory(TextFieldTableCell.forTableColumn());

        col_field.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setField(e.getNewValue());
        });

        col_name.setCellFactory(TextFieldTableCell.forTableColumn());

        col_name.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue());
        });

        table_fields.setEditable(true);
    }

    private String handleRelation(String btnName) {
        if(btnName.contains("Mother") || btnName.contains("Father")) {
            return btnName.replaceAll("Add ", "");
        }

        String relation = relationshipInput.getText();

        if(relation.isEmpty()) {
            return null;
        }

        return relation;
    }
}
