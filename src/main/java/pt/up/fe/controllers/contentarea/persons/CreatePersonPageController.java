package pt.up.fe.controllers.contentarea.persons;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import pt.up.fe.dtos.persons.PersonDTO;
import pt.up.fe.facades.PersonFacade;
import pt.up.fe.person.Gender;
import pt.up.fe.person.Person;
import pt.up.fe.sources.Source;

public class CreatePersonPageController implements Initializable {

  @FXML
  private Button selectSourceButton;

  @FXML
  private Button newSourceButton;

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
  private Button birthButton;

  private Source selectedSource;

  @FXML
  public void initialize(URL url, ResourceBundle resources) {
    setButtonsInvisible();
  }

  private void setButtonsInvisible() {
    newSourceButton.setVisible(false);
    selectSourceButton.setVisible(false);
  }

  @FXML
  private void createPersonAndGoToBirth(MouseEvent event) {
    PersonDTO personDTO = new PersonDTO();
    personDTO.setFirstName(firstNameInput.getText());
    personDTO.setMiddleName(middleNameInput.getText());
    personDTO.setLastName(lastNameInput.getText());
    personDTO.setDescription(descriptionInput.getText());
    personDTO.setGender(Gender.valueOf(genderInput.getValue().toString().toUpperCase()));
    personDTO.setSource(selectedSource);

    Person person = PersonFacade.createPerson(personDTO);
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


}
