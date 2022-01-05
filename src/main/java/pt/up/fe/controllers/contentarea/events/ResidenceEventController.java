package pt.up.fe.controllers.contentarea.events;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.dates.IDate;
import pt.up.fe.dtos.events.FieldDTO;
import pt.up.fe.dtos.events.PersonEventDTO;
import pt.up.fe.events.Event;
import pt.up.fe.facades.EventFacade;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.events.*;
import pt.up.fe.person.Person;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.UUID;

public class ResidenceEventController implements Initializable, IContentPageController {

    private final boolean editMode = true;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField residenceDate;
    @FXML
    private TextArea description;
    @FXML
    private TextField fieldInput;
    @FXML
    private TextField residenceName;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField placeResidence;
    @FXML
    private ComboBox<String> typeOfPlace;
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
    private Button mainButton;
    private IDate date;
    private Boolean inCreateMode = true;
    private UUID editId = null;
    private Person selectedPerson;

    @FXML
    void createEvent(ActionEvent event) {
        HashMap<String, Person> persons = new HashMap<>();
        for (PersonEventDTO item : this.table_persons.getItems()) {
            persons.put(item.getRelationship(), item.getPerson());
        }

        HashMap<String, String> specialPurposeFields = new HashMap<>();
        for (FieldDTO item : this.table_fields.getItems()) {
            specialPurposeFields.put(item.getField(), item.getName());
        }

        Event residenceEvent = new EventFacade().createResidenceEvent(
                this.residenceName.getText(),
                this.placeResidence.getText(),
                this.date,
                this.typeOfPlace.getValue(),
                persons,
                specialPurposeFields,
                this.description.getText(),
                editId,
                this.selectedPerson
        );

        CustomSceneHelper.getNodeById("viewEditPersonPage").fireEvent(new EventCustomEvent(EventCustomEvent.EVENT, residenceEvent));
        CustomSceneHelper.bringNodeToFront("viewEditPerson", "Page");
        System.out.println(residenceEvent.toString());
    }

    @FXML
    void addCustomField(ActionEvent event) {
        String field = fieldInput.getText();
        String name = nameInput.getText();

        if (field.isEmpty() || name.isEmpty()) {
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

        CustomSceneHelper.getNodeById("listPersonsPage")
                .fireEvent(new SelectModeCustomEvent(SelectModeCustomEvent.SELECT_MODE, true));
        CustomSceneHelper.getNodeById("listPersonsPage")
                .fireEvent(new PageToSendCustomEvent(PageToSendCustomEvent.PAGE_TO_SEND, "residenceEventPage"));

        CustomSceneHelper.getNodeById("residenceEventPage").addEventFilter(PersonCustomEvent.PERSON,
                new PersonCustomEventHandler(this.handleRelation(btnName)) {
                    @Override
                    public void handle(PersonCustomEvent personCustomEvent) {
                        table_persons.getItems().add(new PersonEventDTO(getRelation(),
                                personCustomEvent.getPerson())); // Add person to table
                        CustomSceneHelper.getNodeById("residenceEventPage")
                                .removeEventFilter(PersonCustomEvent.PERSON, this); // Remove event handler
                    }
                });
    }

    @FXML
    public void openDateBuilder(ActionEvent event) {
        CustomSceneHelper.bringNodeToFront("CreateDate", "Page");

        CustomSceneHelper.getNodeById("createDatePage")
                .addEventFilter(DateCustomEvent.DATE, new EventHandler<DateCustomEvent>() {
                    @Override
                    public void handle(DateCustomEvent dateCustomEvent) {
                        date = dateCustomEvent.getDate();
                        residenceDate.setText(date.toString());

                        CustomSceneHelper.getNodeById("createDatePage")
                                .removeEventFilter(DateCustomEvent.DATE, this); // Remove event handler
                        CustomSceneHelper.bringNodeToFront("ResidenceEvent", "Page");
                    }
                });
    }

    @FXML
    public void initialize(URL url, ResourceBundle resources) {
        // Initialize Type of Marriage combo
        typeOfPlace.getItems().clear();
        typeOfPlace.getItems().addAll(
                "Single Family Home",
                "Duplex",
                "Apartments",
                "Town Home",
                "Villa",
                "Others",
                "Unknown"
        );

        this.initTables();

        if (this.editMode == false) {
            this.toggleViewMode();
        }
    }

    @Override
    public void setEventHandlers() {
        CustomSceneHelper.getNodeById("residenceEventPage").addEventFilter(
                EventCustomEvent.EVENT, new EventHandler<EventCustomEvent>() {
                    @Override
                    public void handle(EventCustomEvent eventCustomEvent) {
                        Event ev = eventCustomEvent.getEvent();

                        inCreateMode = false;
                        editId = ev.getId();

                        residenceDate.setText(ev.getDate().toString());
                        description.setText(ev.getDescription());

                        for (var entry : ev.getPeopleRelations().entrySet()) {
                            table_persons.getItems().add(new PersonEventDTO(entry.getKey(), entry.getValue()));
                        }

                        for (var entry : ev.getSpecialPurposeFields().entrySet()) {
                            if (entry.getKey() == "Residence Name") {
                                residenceName.setText(entry.getValue());
                            } else if (entry.getKey() == "Type Of Place") {
                                typeOfPlace.getSelectionModel().select(entry.getValue());
                            } else {
                                table_fields.getItems().add(new FieldDTO(entry.getKey(), entry.getValue()));
                            }
                        }

                        // placeBirth.setText(ev.getPlace().toString());
                        date = ev.getDate();

                        mainButton.setText("Edit");
                    }
                });

        CustomSceneHelper.getNodeById("residenceEventPage").addEventFilter(
                PersonCustomEvent.PERSON, new EventHandler<PersonCustomEvent>() {
                    @Override
                    public void handle(PersonCustomEvent personCustomEvent) {
                        selectedPerson = personCustomEvent.getPerson();
                    }
                });
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
        if (btnName.contains("Mother") || btnName.contains("Father")) {
            return btnName.replaceAll("Add ", "");
        }

        String relation = relationshipInput.getText();

        if (relation.isEmpty()) {
            return null;
        }

        return relation;
    }

    private void handleButtonChange() {
        if (inCreateMode == true) {
            mainButton.setText("Create");
        } else {
            mainButton.setText("Edit");
        }
    }

    private void toggleViewMode() {
        for (Node node : anchorPane.getChildren()) {
            if (node instanceof TextField) {
                ((TextField) node).setEditable(false);
            }
            if (node instanceof Button) {
                node.setDisable(true);
            }
            if (node instanceof TextArea) {
                ((TextArea) node).setEditable(false);
            }
            if (node instanceof ComboBox) {
                ((ComboBox) node).setOnShown(event -> ((ComboBox) node).hide());
            }
        }

        mainButton.setVisible(false);
    }

    @Override
    public void clearPage() {
        residenceDate.clear();
        description.clear();
        fieldInput.clear();
        residenceName.clear();
        nameInput.clear();
        placeResidence.clear();
        relationshipInput.clear();
        table_fields.getItems().clear();
        table_persons.getItems().clear();
        typeOfPlace.getItems().clear();
        date = null;
        inCreateMode = true;
        editId = null;
        handleButtonChange();
    }
}
