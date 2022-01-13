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
import pt.up.fe.Main;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.dates.IDate;
import pt.up.fe.dtos.events.CustomEventDTO;
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

public class CustomEventController implements Initializable, IContentPageController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField customDate;
    @FXML
    private TextArea description;
    @FXML
    private TextField fieldInput;
    @FXML
    private TextField customName;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField typeOfCustom;
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

        CustomEventDTO eventDTO = new CustomEventDTO();
        eventDTO.setCustomName(this.customName.getText());
        eventDTO.setTypeOfCustom(this.typeOfCustom.getText());
        eventDTO.setPlace(this.selectedPlace);
        eventDTO.setDate(this.date);
        eventDTO.setPersons(persons);
        eventDTO.setSpecialFields(specialPurposeFields);
        eventDTO.setDescription(this.description.getText());
        eventDTO.setSource(selectedSource);
        eventDTO.setEditId(editId);
        eventDTO.setPerson(this.selectedPerson);

        Event customEvent = EventFacade.createCustomEvent(eventDTO);

        if(this.selectedPerson == null) {
            CustomSceneHelper.getNodeById("listEventsPage").fireEvent(new EventCustomEvent(EventCustomEvent.EVENT, customEvent));
            CustomSceneHelper.bringNodeToFront("listEvents", "Page");
        }
        else {
            CustomSceneHelper.getNodeById("viewEditPersonPage").fireEvent(new EventCustomEvent(EventCustomEvent.EVENT, customEvent));
            CustomSceneHelper.bringNodeToFront("viewEditPerson", "Page");
        }

        System.out.println(customEvent);
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
                .fireEvent(new PageToSendCustomEvent(PageToSendCustomEvent.PAGE_TO_SEND, "customEventPage"));

        CustomSceneHelper.getNodeById("customEventPage").addEventFilter(PersonCustomEvent.PERSON,
                new PersonCustomEventHandler(this.handleRelation(btnName)) {
                    @Override
                    public void handle(PersonCustomEvent personCustomEvent) {
                        table_persons.getItems().add(new PersonEventDTO(getRelation(),
                                personCustomEvent.getPerson())); // Add person to table
                        CustomSceneHelper.getNodeById("customEventPage")
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
                        customDate.setText(date.toString());

                        CustomSceneHelper.getNodeById("createDatePage")
                                .removeEventFilter(DateCustomEvent.DATE, this); // Remove event handler
                        CustomSceneHelper.bringNodeToFront("CustomEvent", "Page");
                    }
                });
    }

    @FXML
    public void initialize(URL url, ResourceBundle resources) {
        this.initTables();

        setButtonsInvisible();
        setPlaceButtonsInvisible();
    }

    @Override
    public void setEventHandlers() {
        CustomSceneHelper.getNodeById("customEventPage").addEventFilter(
                EventCustomEvent.EVENT, new EventHandler<EventCustomEvent>() {
                    @Override
                    public void handle(EventCustomEvent eventCustomEvent) {
                        Event ev = eventCustomEvent.getEvent();

                        inCreateMode = false;
                        editId = ev.getId();

                        if (ev.getName() != null) {
                            customName.setText(ev.getName());
                        }

                        if (ev.getDate() != null) {
                            customDate.setText(ev.getDate().toString());
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
                            if (entry.getKey() == "Type of Custom Event") {
                                typeOfCustom.setText(entry.getValue());
                            } else {
                                table_fields.getItems().add(new FieldDTO(entry.getKey(), entry.getValue()));
                            }
                        }

                        mainButton.setText("Edit");
                    }
                });

        CustomSceneHelper.getNodeById("customEventPage").addEventFilter(
                PersonCustomEvent.PERSON, new EventHandler<PersonCustomEvent>() {
                    @Override
                    public void handle(PersonCustomEvent personCustomEvent) {
                        selectedPerson = personCustomEvent.getPerson();
                    }
                });

        CustomSceneHelper.getNodeById("customEventPage").addEventFilter(SourceCustomEvent.SOURCE, new EventHandler<SourceCustomEvent>() {
            @Override
            public void handle(SourceCustomEvent sourceCustomEvent) {
                selectedSource = sourceCustomEvent.getSource();
                setButtonsInvisible();
                source_radio.selectToggle(selectSource);
                selectSourceButton.setVisible(true);
                selectSourceButton.setText(selectedSource.getName());
            }
        });

        CustomSceneHelper.getNodeById("customEventPage")
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
        this.toggleApplicationMode(Main.editMode);
    }

    private void toggleApplicationMode(Boolean isEditMode) {
        for (Node node : anchorPane.getChildren()) {
            if (node instanceof TextField) {
                ((TextField) node).setEditable(isEditMode);
            }
            if (node instanceof Button) {
                node.setDisable(!isEditMode);
            }
            if (node instanceof TextArea) {
                ((TextArea) node).setEditable(isEditMode);
            }
        }

        source_radio.getToggles().forEach(toggle -> {
            Node node = (Node) toggle;
            node.setDisable(!isEditMode);
        });

        place_radio.getToggles().forEach(toggle -> {
            Node node = (Node) toggle;
            node.setDisable(!isEditMode);
        });

        mainButton.setVisible(isEditMode);
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
        CustomSceneHelper.getNodeById("listSourcesPage").fireEvent(new PageToSendCustomEvent(PageToSendCustomEvent.PAGE_TO_SEND, "customEventPage"));
        CustomSceneHelper.bringNodeToFront("listSources", "Page");
    }

    @FXML
    private void addSource(MouseEvent event) {
        CustomSceneHelper.getNodeById("createSourcePage").fireEvent(new PageToSendCustomEvent(
                PageToSendCustomEvent.PAGE_TO_SEND, "customEventPage"));
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
                        "customEventPage"));
        CustomSceneHelper.bringNodeToFront("listPlaces", "Page");
    }

    @FXML
    private void addPlace(MouseEvent event) {
        CustomSceneHelper.getNodeById("createPlacePage").fireEvent(new PageToSendCustomEvent(
                PageToSendCustomEvent.PAGE_TO_SEND, "customEventPage"));
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
        customDate.clear();
        description.clear();
        fieldInput.clear();
        customName.clear();
        nameInput.clear();
        typeOfCustom.clear();
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
