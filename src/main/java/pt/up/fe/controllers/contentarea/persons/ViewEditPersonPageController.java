package pt.up.fe.controllers.contentarea.persons;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.dates.IDate;
import pt.up.fe.dates.SimpleDate;
import pt.up.fe.dtos.events.EventTableDTO;
import pt.up.fe.dtos.persons.PersonDTO;
import pt.up.fe.dtos.persons.PersonTableDTO;
import pt.up.fe.events.Event;
import pt.up.fe.facades.PersonFacade;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.events.EventCustomEvent;
import pt.up.fe.helpers.events.PageToSendCustomEvent;
import pt.up.fe.helpers.events.PersonCustomEvent;
import pt.up.fe.helpers.events.SelectModeCustomEvent;
import pt.up.fe.helpers.events.SourceCustomEvent;
import pt.up.fe.person.Gender;
import pt.up.fe.person.Person;
import pt.up.fe.places.Place;
import pt.up.fe.sources.Source;

public class ViewEditPersonPageController implements Initializable, IContentPageController {

  @FXML
  private Button selectSourceButton;

  @FXML
  private Button addEvent;

  @FXML
  private Button newSourceButton;

  @FXML
  private RadioButton selectSource;

  @FXML
  private Button saveButton;

  @FXML
  private TextField firstNameInput;

  @FXML
  private TextField middleNameInput;

  @FXML
  private TextField lastNameInput;

  @FXML
  private ComboBox genderInput;

  @FXML
  private TextArea descriptionInput;

  @FXML
  private ToggleGroup source_radio;

  @FXML
  private RadioButton noSource;

  @FXML
  private TableColumn<PersonTableDTO, String> childrenColumnBirthDate;

  @FXML
  private TableColumn<PersonTableDTO, Integer> childrenColumnChildren;

  @FXML
  private TableColumn<PersonTableDTO, String> childrenColumnFirstName;

  @FXML
  private TableColumn<PersonTableDTO, Gender> childrenColumnGender;

  @FXML
  private TableColumn<PersonTableDTO, String> childrenColumnLastName;

  @FXML
  private TableColumn<PersonTableDTO, String> childrenColumnMiddleName;

  @FXML
  private TableView<PersonTableDTO> childrenTable;

  @FXML
  private TableColumn<EventTableDTO, String> eventColumnEvent;

  @FXML
  private TableColumn<EventTableDTO, Place> eventColumnPlace;

  @FXML
  private TableColumn<EventTableDTO, IDate> eventColumnDate;

  @FXML
  private TableColumn<EventTableDTO, String> eventColumnDescription;

  @FXML
  private TableView<EventTableDTO> eventsTable;

  private Source selectedSource;

  private Person selectedPerson;

  private boolean editMode = false;

  ObservableList<PersonTableDTO> childrenTableList = FXCollections.observableArrayList();

  ObservableList<EventTableDTO> eventsTableList = FXCollections.observableArrayList();

  @FXML
  public void initialize(URL url, ResourceBundle resources) {
    changePageMode();
    setButtonsInvisible();
    childrenColumnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    childrenColumnMiddleName.setCellValueFactory(new PropertyValueFactory<>("middleName"));
    childrenColumnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    childrenColumnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    childrenColumnChildren.setCellValueFactory(new PropertyValueFactory<>("children"));
    childrenColumnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    childrenTable.setItems(childrenTableList);
    eventColumnEvent.setCellValueFactory(new PropertyValueFactory<>("event"));
    eventColumnPlace.setCellValueFactory(new PropertyValueFactory<>("place"));
    eventColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    eventColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    eventsTable.setItems(eventsTableList);
  }


  @Override
  public void setEventHandlers() {
    CustomSceneHelper.getNodeById("viewEditPersonPage").addEventFilter(
        PersonCustomEvent.PERSON, new EventHandler<PersonCustomEvent>() {
          @Override
          public void handle(PersonCustomEvent personCustomEvent) {
            selectedPerson = personCustomEvent.getPerson();
            setInfo();
          }
        });

    CustomSceneHelper.getNodeById("viewEditPersonPage").addEventFilter(
        EventCustomEvent.EVENT, new EventHandler<EventCustomEvent>() {
          @Override
          public void handle(EventCustomEvent eventCustomEvent) {
            Event event = eventCustomEvent.getEvent();
            PersonFacade.addEventToPerson(selectedPerson, event);

            eventsTableList.add(
                new EventTableDTO(event.getName(), event.getPlace(), event.getDate(),
                    event.getDescription(), event));

          }
        });

    CustomSceneHelper.getNodeById("viewEditPersonPage")
        .addEventFilter(SourceCustomEvent.SOURCE, new EventHandler<SourceCustomEvent>() {
          @Override
          public void handle(SourceCustomEvent sourceCustomEvent) {
            selectedSource = sourceCustomEvent.getSource();
            setInfo();
          }
        });
  }

  private void setInfo() {
    descriptionInput.setText(selectedPerson.getDescription());
    firstNameInput.setText(selectedPerson.getName());
    middleNameInput.setText(selectedPerson.getMiddleName());
    lastNameInput.setText(selectedPerson.getLastName());
    genderInput.setValue(
        selectedPerson.getGender().toString().substring(0, 1) + selectedPerson.getGender()
            .toString().substring(1).toLowerCase());

    setButtonsInvisible();
    if (selectedSource != null) {
      source_radio.selectToggle(selectSource);
      selectSourceButton.setVisible(true);
      selectSourceButton.setText(selectedSource.getName());
    } else {
      if (selectedPerson.getSource() != null) {
        source_radio.selectToggle(selectSource);
        selectSourceButton.setVisible(true);
        selectSourceButton.setText(selectedPerson.getSource().getName());
      }
    }

    childrenTableList.clear();
    selectedPerson.getChildren().forEach(person -> {
      childrenTableList.add(
          new PersonTableDTO(person.getName(), person.getMiddleName(), person.getLastName(),
              person.getGender(), new SimpleDate(), person.getChildren().size(), person));
    });

    eventsTableList.clear();
    selectedPerson.getEvents().forEach(event -> {
      eventsTableList.add(new EventTableDTO(event.getName(), event.getPlace(), event.getDate(),
          event.getDescription(), event));
    });

    changePageMode();
  }

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
    CustomSceneHelper.getNodeById("listSourcesPage")
        .fireEvent(new SelectModeCustomEvent(SelectModeCustomEvent.SELECT_MODE, true));
    CustomSceneHelper.getNodeById("listSourcesPage").fireEvent(
        new PageToSendCustomEvent(PageToSendCustomEvent.PAGE_TO_SEND, "viewEditPersonPage"));
    CustomSceneHelper.bringNodeToFront("listSources", "Page");
  }

  @FXML
  private void addSource(MouseEvent event) {
    CustomSceneHelper.getNodeById("createSourcePage").fireEvent(new PageToSendCustomEvent(
        PageToSendCustomEvent.PAGE_TO_SEND, "viewEditPersonPage"));
    CustomSceneHelper.bringNodeToFront("createSource", "Page");
  }

  @FXML
  private void selectChild(MouseEvent event) {
    if (event.getClickCount() == 2) {
      Person selection = childrenTable.getSelectionModel().getSelectedItem().getPerson();
      clearPage();
      this.selectedPerson = selection;
      setInfo();
    }
  }

  @FXML
  private void selectEvent(MouseEvent event) {
    if (event.getClickCount() == 2) {
      Event curEvent = eventsTable.getSelectionModel().getSelectedItem().getCurEvent();
      String eventName = curEvent.getClass().getSimpleName();
      eventName = eventName.substring(0, 1).toLowerCase() + eventName.substring(1);
      if (!curEvent.getClass().getSimpleName().contains("Event")) {
        eventName += "Event";
      }
      CustomSceneHelper.getNodeById(eventName + "Page")
          .fireEvent(new EventCustomEvent(EventCustomEvent.EVENT, curEvent));
      CustomSceneHelper.bringNodeToFront(eventName + "Page", "Page");
      clearPage();

    }
  }

  @FXML
  private void addEvent(MouseEvent event) {
    if (editMode) {
      CustomSceneHelper.getNodeById("createEventPage")
          .fireEvent(new PersonCustomEvent(PersonCustomEvent.PERSON, selectedPerson));
    }
  }


  private void changePageMode() {
    if (editMode) {
      descriptionInput.setEditable(true);
      firstNameInput.setEditable(true);
      middleNameInput.setEditable(true);
      lastNameInput.setEditable(true);
      genderInput.setDisable(false);
      source_radio.getToggles().forEach(toggle -> {
        Node node = (Node) toggle;
        node.setDisable(false);
      });
      saveButton.setText("Save");
      addEvent.setVisible(true);
    } else {
      descriptionInput.setEditable(false);
      firstNameInput.setEditable(false);
      middleNameInput.setEditable(false);
      lastNameInput.setEditable(false);
      genderInput.setDisable(true);
      addEvent.setVisible(false);
      source_radio.getToggles().forEach(toggle -> {
        Node node = (Node) toggle;
        node.setDisable(true);
      });
      saveButton.setText("OK");
    }
  }

  private void setButtonsInvisible() {
    newSourceButton.setVisible(false);
    selectSourceButton.setVisible(false);
  }

  @Override
  public void clearPage() {
    this.selectedPerson = null;
    this.selectedSource = null;
    descriptionInput.clear();
    firstNameInput.clear();
    middleNameInput.clear();
    lastNameInput.clear();
    genderInput.getSelectionModel().select(0);
    source_radio.selectToggle(noSource);
    setButtonsInvisible();
    descriptionInput.clear();
    childrenTableList.clear();
    eventsTableList.clear();
  }

  public void save() throws IllegalAccessException {
    if (editMode) {
      PersonDTO personDTO = new PersonDTO();
      personDTO.setFirstName(firstNameInput.getText());
      personDTO.setMiddleName(middleNameInput.getText());
      personDTO.setLastName(lastNameInput.getText());
      personDTO.setDescription(descriptionInput.getText());

      personDTO.setGender(Gender.valueOf(genderInput.getSelectionModel().getSelectedIndex() + 1));

      if (selectedSource != null) {
        personDTO.setSource(selectedSource);
      }

      PersonFacade.editPerson(selectedPerson, personDTO);

    }
    this.clearPage();
    CustomSceneHelper.contentAreaPaneController.cleanAll();
    CustomSceneHelper.bringNodeToFront("listPersons", "Page");

  }
}
