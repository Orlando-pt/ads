package pt.up.fe.controllers.contentarea.events;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.dates.IDate;
import pt.up.fe.dtos.events.EmigrationEventDTO;
import pt.up.fe.dtos.events.FieldDTO;
import pt.up.fe.dtos.events.PersonEventDTO;
import pt.up.fe.events.Event;
import pt.up.fe.facades.EventFacade;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.events.*;
import pt.up.fe.person.Person;
import pt.up.fe.places.Place;
import pt.up.fe.sources.Source;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.UUID;

public class EmigrationEventController implements Initializable, IContentPageController {

    private final boolean editMode = true;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField emigrationDate;
    @FXML
    private TextArea description;
    @FXML
    private TextField fieldInput;
    @FXML
    private TextField typeOfEmigration;
    @FXML
    private TextField nameInput;
    @FXML
    private ComboBox<String> pushFactorsCombo;
    @FXML
    private ComboBox<String> pullFactorsCombo;
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

    // Source

    @FXML
    private Button selectSourceButton;

    @FXML
    private RadioButton selectSource;

    @FXML
    private Button newSourceButton;

    @FXML
    private RadioButton noSource;

    @FXML
    private ToggleGroup source_radio;

    private Source selectedSource;

    // Place

    @FXML
    private Button selectPlaceButton;

    @FXML
    private Button newPlaceButton;

    @FXML
    private ToggleGroup place_radio;

    @FXML
    private RadioButton noPlace;

    @FXML
    private RadioButton selectPlace;

    private Place selectedPlace;

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

        EmigrationEventDTO eventDTO = new EmigrationEventDTO();
        eventDTO.setTypeOfEmigration(this.typeOfEmigration.getText());
        eventDTO.setPlace(this.selectedPlace);
        eventDTO.setDate(this.date);
        eventDTO.setPushFactor(this.pushFactorsCombo.getValue());
        eventDTO.setPullFactor(this.pullFactorsCombo.getValue());
        eventDTO.setPersons(persons);
        eventDTO.setSpecialFields(specialPurposeFields);
        eventDTO.setDescription(this.description.getText());
        eventDTO.setSource(selectedSource);
        eventDTO.setEditId(editId);
        eventDTO.setPerson(this.selectedPerson);

        Event emigrationEvent = EventFacade.createEmigrationEvent(eventDTO);

        CustomSceneHelper.getNodeById("viewEditPersonPage").fireEvent(new EventCustomEvent(EventCustomEvent.EVENT, emigrationEvent));
        CustomSceneHelper.bringNodeToFront("viewEditPerson", "Page");
        System.out.println(emigrationEvent);
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
                .fireEvent(new PageToSendCustomEvent(PageToSendCustomEvent.PAGE_TO_SEND, "emigrationEventPage"));

        CustomSceneHelper.getNodeById("emigrationEventPage").addEventFilter(PersonCustomEvent.PERSON,
                new PersonCustomEventHandler(this.handleRelation(btnName)) {
                    @Override
                    public void handle(PersonCustomEvent personCustomEvent) {
                        table_persons.getItems().add(new PersonEventDTO(getRelation(),
                                personCustomEvent.getPerson())); // Add person to table
                        CustomSceneHelper.getNodeById("emigrationEventPage")
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
                        emigrationDate.setText(date.toString());

                        CustomSceneHelper.getNodeById("createDatePage")
                                .removeEventFilter(DateCustomEvent.DATE, this); // Remove event handler
                        CustomSceneHelper.bringNodeToFront("EmigrationEvent", "Page");
                    }
                });
    }

    @FXML
    public void initialize(URL url, ResourceBundle resources) {
        // Initialize Push Factors combo
        pushFactorsCombo.getItems().clear();
        pushFactorsCombo.getItems().addAll(
                "Poor living conditions",
                "Lack of employment or entrepreneurial opportunities",
                "Lack of educational opportunities",
                "Lack of political or religious rights",
                "Threat of arrest or punishment",
                "Persecution or intolerance based on race, religion, gender or sexual orientation",
                "Inability to find a spouse for marriage",
                "Lack of freedom to choose religion, or to choose no religion",
                "Shortage of farmland; hard to start new farms (historically)",
                "Oppressive legal or political conditions",
                "Struggling or Failing economy",
                "Military draft, warfare or terrorism",
                "Famine or drought",
                "Cultural fights with other cultural groups",
                "Expulsion by armed force or coercion",
                "Overcrowding",
                "Unknown"
        );

        // Initialize Push Factors combo
        pullFactorsCombo.getItems().clear();
        pullFactorsCombo.getItems().addAll(
                "Better living conditions",
                "Favourable letters relatives or informants who have already moved; chain migration",
                "Better opportunities for acquiring farms for self and children",
                "Cheap purchase of farmland",
                "Quick wealth (as in a gold rush)",
                "More job opportunities",
                "Promise of higher pay",
                "Prepaid travel (as from relatives)",
                "Better welfare programmes",
                "Better schools",
                "Join relatives who have already moved; chain migration",
                "Building a new nation (historically)",
                "Building specific cultural or religious communities",
                "Political freedom",
                "Cultural opportunities",
                "Greater opportunity to find a spouse",
                "Favorable climate",
                "Easygoing to across the boundaries",
                "Reduced tariff",
                "Unknown"
        );

        this.initTables();

        if (this.editMode == false) {
            this.toggleViewMode();
        }

        setButtonsInvisible();
        setPlaceButtonsInvisible();
    }

    @Override
    public void setEventHandlers() {
        CustomSceneHelper.getNodeById("emigrationEventPage").addEventFilter(
                EventCustomEvent.EVENT, new EventHandler<EventCustomEvent>() {
                    @Override
                    public void handle(EventCustomEvent eventCustomEvent) {
                        Event ev = eventCustomEvent.getEvent();

                        inCreateMode = false;
                        editId = ev.getId();

                        if (ev.getDate() != null) {
                            emigrationDate.setText(ev.getDate().toString());
                            date = ev.getDate();
                        }

                        if (ev.getDescription() != null) {
                            description.setText(ev.getDescription());
                        }

                        selectedSource = ev.getSource();
                        setSourceInfo();

                        selectedPlace = ev.getPlace();
                        setPlaceInfo();

                        for (var entry : ev.getPeopleRelations().entrySet()) {
                            table_persons.getItems().add(new PersonEventDTO(entry.getKey(), entry.getValue()));
                        }

                        for (var entry : ev.getSpecialPurposeFields().entrySet()) {
                            if (entry.getKey() == "Type of Emigration") {
                                typeOfEmigration.setText(entry.getValue());
                            } else if (entry.getKey() == "Push factor") {
                                pushFactorsCombo.getSelectionModel().select(entry.getValue());
                            } else if (entry.getKey() == "Pull factor") {
                                pullFactorsCombo.getSelectionModel().select(entry.getValue());
                            } else {
                                table_fields.getItems().add(new FieldDTO(entry.getKey(), entry.getValue()));
                            }
                        }

                        mainButton.setText("Edit");
                    }
                });

        CustomSceneHelper.getNodeById("emigrationEventPage").addEventFilter(
                PersonCustomEvent.PERSON, new EventHandler<PersonCustomEvent>() {
                    @Override
                    public void handle(PersonCustomEvent personCustomEvent) {
                        selectedPerson = personCustomEvent.getPerson();
                    }
                });

        CustomSceneHelper.getNodeById("emigrationEventPage").addEventFilter(SourceCustomEvent.SOURCE, new EventHandler<SourceCustomEvent>() {
            @Override
            public void handle(SourceCustomEvent sourceCustomEvent) {
                selectedSource = sourceCustomEvent.getSource();
                setButtonsInvisible();
                source_radio.selectToggle(selectSource);
                selectSourceButton.setVisible(true);
                selectSourceButton.setText(selectedSource.getName());
            }
        });

        CustomSceneHelper.getNodeById("emigrationEventPage")
                .addEventFilter(PlaceCustomEvent.PLACE, new EventHandler<PlaceCustomEvent>() {
                    @Override
                    public void handle(PlaceCustomEvent placeCustomEvent) {
                        selectedPlace = placeCustomEvent.getPlace();
                        place_radio.selectToggle(selectPlace);
                        selectPlaceButton.setVisible(true);
                        selectPlaceButton.setText(selectedPlace.getName());
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

        source_radio.getToggles().forEach(toggle -> {
            Node node = (Node) toggle;
            node.setDisable(false);
        });

        mainButton.setVisible(false);
    }

    // Source

    @FXML
    private void selectSourceType(MouseEvent event) throws NoSuchFieldException {

        RadioButton selectedRadioButton = (RadioButton) source_radio.getSelectedToggle();
        String toogleGroupSelectedValueID = selectedRadioButton.getId();
        setButtonsInvisible();
        try {
            Field field = this.getClass().getDeclaredField(toogleGroupSelectedValueID + "Button");
            Button b = (Button) field.get(this);
            b.setVisible(true);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // No need to do anything, button invalid.
        }
    }

    @FXML
    private void selectSource(MouseEvent event) {
        CustomSceneHelper.getNodeById("listSourcesPage").fireEvent(new SelectModeCustomEvent(SelectModeCustomEvent.SELECT_MODE, true));
        CustomSceneHelper.getNodeById("listSourcesPage").fireEvent(new PageToSendCustomEvent(PageToSendCustomEvent.PAGE_TO_SEND, "emigrationEventPage"));
        CustomSceneHelper.bringNodeToFront("listSources", "Page");
    }

    @FXML
    private void addSource(MouseEvent event) {
        CustomSceneHelper.getNodeById("createSourcePage").fireEvent(new PageToSendCustomEvent(
                PageToSendCustomEvent.PAGE_TO_SEND, "emigrationEventPage"));
        CustomSceneHelper.bringNodeToFront("createSource", "Page");
    }

    private void setSourceInfo() {
        setButtonsInvisible();
        if (selectedSource != null) {
            source_radio.selectToggle(selectSource);
            selectSourceButton.setVisible(true);
            selectSourceButton.setText(selectedSource.getName());
        }
    }

    private void setButtonsInvisible() {
        newSourceButton.setVisible(false);
        selectSourceButton.setVisible(false);
    }

    // Place

    private void setPlaceButtonsInvisible() {
        newPlaceButton.setVisible(false);
        selectPlaceButton.setVisible(false);
    }

    @FXML
    private void selectPlaceType(MouseEvent event) throws NoSuchFieldException {

        RadioButton selectedRadioButton = (RadioButton) place_radio.getSelectedToggle();
        String toogleGroupSelectedValueID = selectedRadioButton.getId();
        setPlaceButtonsInvisible();
        try {
            Field field = this.getClass().getDeclaredField(toogleGroupSelectedValueID + "Button");
            Button b = (Button) field.get(this);
            b.setVisible(true);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // No need to do anything, button invalid.
        }
    }

    @FXML
    private void selectPlace(MouseEvent event) {
        CustomSceneHelper.getNodeById("listPlacesPage")
                .fireEvent(new SelectModeCustomEvent(SelectModeCustomEvent.SELECT_MODE, true));
        CustomSceneHelper.getNodeById("listPlacesPage").fireEvent(
                new PageToSendCustomEvent(PageToSendCustomEvent.PAGE_TO_SEND,
                        "emigrationEventPage"));
        CustomSceneHelper.bringNodeToFront("listPlaces", "Page");
    }

    @FXML
    private void addPlace(MouseEvent event) {
        CustomSceneHelper.getNodeById("createPlacePage").fireEvent(new PageToSendCustomEvent(
                PageToSendCustomEvent.PAGE_TO_SEND, "emigrationEventPage"));
        CustomSceneHelper.bringNodeToFront("createPlace", "Page");
    }

    private void setPlaceInfo() {
        setPlaceButtonsInvisible();

        if (selectedPlace != null) {
            place_radio.selectToggle(selectPlace);
            selectPlaceButton.setVisible(true);
            selectPlaceButton.setText(selectedPlace.getName());
        }
    }

    @Override
    public void clearPage() {
        emigrationDate.clear();
        description.clear();
        fieldInput.clear();
        typeOfEmigration.clear();
        nameInput.clear();
        relationshipInput.clear();
        table_fields.getItems().clear();
        table_persons.getItems().clear();
        date = null;
        inCreateMode = true;
        editId = null;
        handleButtonChange();
        // Source
        source_radio.selectToggle(noSource);
        setButtonsInvisible();
        selectedSource = null;
        // Place
        place_radio.selectToggle(noPlace);
        selectedPlace = null;
    }
}
