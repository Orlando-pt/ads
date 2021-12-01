package pt.up.fe.controllers.contentarea.persons;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pt.up.fe.controllers.contentarea.events.BirthEventController;
import pt.up.fe.dates.SimpleDate;
import pt.up.fe.dtos.persons.FilterPersonsDTO;
import pt.up.fe.dtos.persons.PersonTableDTO;
import pt.up.fe.facades.PersonFacade;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.SingletonHolder;
import pt.up.fe.helpers.events.PersonCustomEvent;
import pt.up.fe.person.Gender;
import pt.up.fe.person.Person;

public class ListPersonsPageController implements Initializable {

  @FXML
  private Button selectButton;

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

  ObservableList<PersonTableDTO> list = FXCollections.observableArrayList();

  @Override
  public void initialize(URL url, ResourceBundle resources) {
    firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    middleName.setCellValueFactory(new PropertyValueFactory<>("middleName"));
    lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    children.setCellValueFactory(new PropertyValueFactory<>("children"));
    birthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

    if (false) {
      selectButton.setVisible(false);
      selectButtonLabel.setVisible(false);
      selectViewButton.setVisible(true);
      selectViewButtonLabel.setVisible(true);

    } else {
      selectViewButton.setVisible(false);
      selectViewButtonLabel.setVisible(false);
      selectButton.setVisible(true);
      selectButtonLabel.setVisible(true);
    }

    filterPersons();
    personsTable.setItems(list);
  }

  @FXML
  private void search(MouseEvent event) {
    filterPersons();
  }

  @FXML
  private void selectPerson(MouseEvent event) {
    Person person = personsTable.getSelectionModel().getSelectedItem().getPerson();

    CustomSceneHelper.getNodeById("listPersonsPage").fireEvent(new PersonCustomEvent(PersonCustomEvent.PERSON, person));

    CustomSceneHelper.bringNodeToFront("BirthEvent", "Page");

    this.clearPage();
  }

  private void filterPersons() {
    FilterPersonsDTO filters = new FilterPersonsDTO();

    filters.setFirstName(firstNameInput.getCharacters().toString());

    filters.setMiddleName(middleNameInput.getCharacters().toString());

    filters.setLastName(lastNameInput.getCharacters().toString());

    list.clear();
    List<Person> personsFiltered = PersonFacade.filterPersons(filters);

    personsFiltered.forEach(person -> {
      list.add(new PersonTableDTO(person.getName(), person.getMiddleName(), person.getLastName(),
          person.getGender(), new SimpleDate(), person.getChildren().size(), person));
    });
  }

  private void clearPage() {
    firstNameInput.clear();
    middleNameInput.clear();
    lastNameInput.clear();
    this.filterPersons();
  }


}
