package pt.up.fe.controllers.contentarea.persons;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.dates.IDate;
import pt.up.fe.dates.IntervalDate;
import pt.up.fe.dates.SimpleDate;
import pt.up.fe.dtos.persons.FilterPersonType;
import pt.up.fe.dtos.persons.FilterPersonsDTO;
import pt.up.fe.dtos.persons.PersonTableDTO;
import pt.up.fe.facades.PersonFacade;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.events.DateCustomEvent;
import pt.up.fe.helpers.events.PageToSendCustomEvent;
import pt.up.fe.helpers.events.PersonCustomEvent;
import pt.up.fe.helpers.events.SelectModeCustomEvent;
import pt.up.fe.helpers.events.TypeSelectionDateCustomEvent;
import pt.up.fe.person.Gender;
import pt.up.fe.person.Person;

public class ListPersonsPageController implements Initializable, IContentPageController {

  @FXML
  private Button selectButton;

  @FXML
  private Button searchButton;

  @FXML
  private Button addDate1Button;

  @FXML
  private ComboBox typeSearchComboBox;

  @FXML
  private Label selectButtonLabel;

  @FXML
  private Button selectViewButton;

  @FXML
  private Label selectViewButtonLabel;

  @FXML
  private TextField firstNameInput;

  @FXML
  private TextField middleNameInput;

  @FXML
  private TextField lastNameInput;

  @FXML
  private Label firstNameLabel;

  @FXML
  private Label middleNameLabel;

  @FXML
  private Label lastNameLabel;

  @FXML
  private TableColumn<PersonTableDTO, String> birthDate;

  @FXML
  private TableColumn<PersonTableDTO, Integer> children;

  @FXML
  private TableColumn<PersonTableDTO, String> firstName;

  @FXML
  private TableColumn<PersonTableDTO, Gender> gender;

  @FXML
  private TableColumn<PersonTableDTO, String> lastName;

  @FXML
  private TableColumn<PersonTableDTO, String> middleName;

  @FXML
  private TableView<PersonTableDTO> personsTable;

  private Boolean selectMode = false;

  private String pageToSend;

  private IntervalDate date;

  private FilterPersonType typeDTO;

  ObservableList<PersonTableDTO> list = FXCollections.observableArrayList();

  @Override
  public void initialize(URL url, ResourceBundle resources) {
    firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    middleName.setCellValueFactory(new PropertyValueFactory<>("middleName"));
    lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    children.setCellValueFactory(new PropertyValueFactory<>("children"));
    birthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

    typeSearchComboBox.getItems().clear();
    typeSearchComboBox.getItems()
        .addAll("Names", "Children", "GrandChildren", "GrandGrandChildren", "Interval of Dates");
    typeSearchComboBox.getSelectionModel().select("Names");

    this.clearPage();
    personsTable.setItems(list);
  }

  @FXML
  private void changeFilterType(ActionEvent event) throws NoSuchFieldException {

    String selectedType = (String) typeSearchComboBox.getSelectionModel().getSelectedItem();
    hideFilters();
    switch (selectedType) {
      case "Names":
        typeDTO = FilterPersonType.NAMES;
        firstNameLabel.setVisible(true);
        middleNameLabel.setVisible(true);
        lastNameLabel.setVisible(true);
        firstNameInput.setVisible(true);
        middleNameInput.setVisible(true);
        lastNameInput.setVisible(true);
        break;
      case "Children":
        typeDTO = FilterPersonType.CHILDREN;
        searchButton.setText("Select a person to search");
        break;
      case "GrandChildren":
        typeDTO = FilterPersonType.GRANDCHILDREN;
        searchButton.setText("Select a person to search");
        break;
      case "GrandGrandChildren":
        typeDTO = FilterPersonType.GRANDGRANDCHILDREN;
        searchButton.setText("Select a person to search");
        break;
      case "Interval of Dates":
        typeDTO = FilterPersonType.DATE;
        firstNameLabel.setText("Start Date");
        middleNameLabel.setText("End Date");
        firstNameLabel.setVisible(true);
        middleNameLabel.setVisible(true);
        firstNameInput.setVisible(true);
        middleNameInput.setVisible(true);
        firstNameInput.setDisable(true);
        middleNameInput.setDisable(true);
        addDate1Button.setVisible(true);
        addDate1Button.setText("Add Dates");
        break;
    }
  }

  private void hideFilters() {
    firstNameLabel.setText("First Name");
    middleNameLabel.setText("Middle Name");
    searchButton.setText("Search");
    firstNameInput.clear();
    middleNameInput.clear();
    lastNameInput.clear();
    date = null;
    firstNameLabel.setVisible(false);
    middleNameLabel.setVisible(false);
    firstNameInput.setDisable(false);
    middleNameInput.setDisable(false);
    lastNameLabel.setVisible(false);
    firstNameInput.setVisible(false);
    middleNameInput.setVisible(false);
    lastNameInput.setVisible(false);
    addDate1Button.setVisible(false);
  }

  @Override
  public void setEventHandlers() {
    CustomSceneHelper.getNodeById("listPersonsPage").addEventFilter(
        SelectModeCustomEvent.SELECT_MODE, new EventHandler<SelectModeCustomEvent>() {
          @Override
          public void handle(SelectModeCustomEvent selectModeCustomEvent) {
            selectMode = selectModeCustomEvent.getSelectMode();
            changeButtonLayout();
          }
        });

    CustomSceneHelper.getNodeById("listPersonsPage").addEventFilter(
        PageToSendCustomEvent.PAGE_TO_SEND, new EventHandler<PageToSendCustomEvent>() {
          @Override
          public void handle(PageToSendCustomEvent pageToSendCustomEvent) {
            pageToSend = pageToSendCustomEvent.getPageToSend();
          }
        });
  }

  @FXML
  private void search(MouseEvent event) {
    filterPersons();
  }

  @FXML
  private void selectPerson(MouseEvent event) {
    Person person = personsTable.getSelectionModel().getSelectedItem().getPerson();
    if (selectMode && pageToSend != null) {
      CustomSceneHelper.getNodeById(pageToSend)
          .fireEvent(new PersonCustomEvent(PersonCustomEvent.PERSON, person));
      CustomSceneHelper.bringNodeToFront(pageToSend, "");
    } else {
      CustomSceneHelper.getNodeById("viewEditPersonPage")
          .fireEvent(new PersonCustomEvent(PersonCustomEvent.PERSON, person));
      CustomSceneHelper.bringNodeToFront("viewEditPerson", "Page");
    }

  }

  private void filterPersons() {
    FilterPersonsDTO filters = new FilterPersonsDTO();

    filters.setFilterPersonType(typeDTO);

    filters.setFirstName(firstNameInput.getCharacters().toString());

    filters.setMiddleName(middleNameInput.getCharacters().toString());

    filters.setLastName(lastNameInput.getCharacters().toString());

    PersonTableDTO personTable = personsTable.getSelectionModel().getSelectedItem();

    if (personTable != null && typeDTO != FilterPersonType.NAMES && typeDTO != FilterPersonType.DATE) {
      firstNameLabel.setText(
          "Showing information of " + personTable.getFirstName() + " " + personTable.getLastName());
      firstNameLabel.setVisible(true);
      filters.setPerson(personTable.getPerson());
    }

    if (date != null) {
        filters.setStartDate(date.getStartDate());
        filters.setEndDate(date.getEndDate());
    }

    list.clear();
    List<Person> personsFiltered = PersonFacade.filterPersons(filters);

    personsFiltered.forEach(person -> {
      list.add(new PersonTableDTO(person.getName(), person.getMiddleName(), person.getLastName(),
          person.getGender(), new SimpleDate(), person.getChildren().size(), person));
    });
  }

  @Override
  public void clearPage() {
    hideFilters();
    typeDTO = FilterPersonType.NAMES;
    firstNameLabel.setVisible(true);
    middleNameLabel.setVisible(true);
    lastNameLabel.setVisible(true);
    firstNameInput.setVisible(true);
    middleNameInput.setVisible(true);
    lastNameInput.setVisible(true);
    typeSearchComboBox.getSelectionModel().select("Names");
    firstNameInput.clear();
    middleNameInput.clear();
    lastNameInput.clear();
    this.filterPersons();
    this.selectMode = false;
    pageToSend = null;
    this.changeButtonLayout();
    date = null;
  }

  @FXML
  public void openDate1Builder(MouseEvent event) {
    CustomSceneHelper.getNodeById("createDatePage")
        .fireEvent(new TypeSelectionDateCustomEvent(TypeSelectionDateCustomEvent.INTERVALSELECTION,
            (typeSearchComboBox.getSelectionModel().getSelectedItem()).equals(
                "Interval of Dates")));

    CustomSceneHelper.bringNodeToFront("createDate", "Page");

    CustomSceneHelper.getNodeById("createDatePage")
        .addEventFilter(DateCustomEvent.DATE, new EventHandler<DateCustomEvent>() {
          @Override
          public void handle(DateCustomEvent dateCustomEvent) {
            date = (IntervalDate)  dateCustomEvent.getDate();

            if (date.getClass().getSimpleName().equals("SimpleDate")) {
              firstNameInput.setText(date.toString());
            } else {
              firstNameInput.setText(((IntervalDate) date).getStartDate().toString());
              middleNameInput.setText(((IntervalDate) date).getEndDate().toString());
            }

            CustomSceneHelper.getNodeById("createDatePage")
                .removeEventFilter(DateCustomEvent.DATE, this); // Remove event handler
            CustomSceneHelper.bringNodeToFront("ListPersons", "Page");
          }
        });
  }

  private void changeButtonLayout() {
    if (this.selectMode) {
      selectViewButton.setVisible(false);
      selectViewButtonLabel.setVisible(false);
      selectButton.setVisible(true);
      selectButtonLabel.setVisible(true);
    } else {
      selectButton.setVisible(false);
      selectButtonLabel.setVisible(false);
      selectViewButton.setVisible(true);
      selectViewButtonLabel.setVisible(true);
    }
  }


}
