package pt.up.fe.controllers.contentarea.persons;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.dtos.persons.PersonDTO;
import pt.up.fe.facades.PersonFacade;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.events.PageToSendCustomEvent;
import pt.up.fe.helpers.events.SelectModeCustomEvent;
import pt.up.fe.helpers.events.SourceCustomEvent;
import pt.up.fe.person.Gender;
import pt.up.fe.person.Person;
import pt.up.fe.Source;

public class CreatePersonPageController implements Initializable, IContentPageController {

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

  @FXML
  private RadioButton noSource;

  private Source selectedSource;

  @Override
  public void initialize(URL url, ResourceBundle resources) {
    setButtonsInvisible();
  }

  @Override
  public void setEventHandlers() {
    CustomSceneHelper.getNodeById("createPersonPage").addEventFilter(SourceCustomEvent.SOURCE, new EventHandler<SourceCustomEvent>() {
      @Override
      public void handle(SourceCustomEvent sourceCustomEvent) {
        selectedSource = sourceCustomEvent.getSource();
      }
    });
  }

  @Override
  public void clearPage() {
    firstNameInput.clear();
    middleNameInput.clear();
    lastNameInput.clear();
    genderInput.getSelectionModel().select(0);
    descriptionInput.clear();
    source_radio.selectToggle(noSource);
    selectedSource = null;
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

  @FXML
  private void selectSource(MouseEvent event) {
    CustomSceneHelper.getNodeById("listSourcesPage").fireEvent(new SelectModeCustomEvent(SelectModeCustomEvent.SELECT_MODE, true));
    CustomSceneHelper.getNodeById("listSourcesPage").fireEvent(new PageToSendCustomEvent(PageToSendCustomEvent.PAGE_TO_SEND, "createPersonPage"));
    CustomSceneHelper.bringNodeToFront("listSources", "Page");
  }

  @FXML
  private void addSource(MouseEvent event) {
    CustomSceneHelper.getNodeById("createSourcePage").fireEvent(new PageToSendCustomEvent(
        PageToSendCustomEvent.PAGE_TO_SEND, "createPersonPage"));
    CustomSceneHelper.bringNodeToFront("createSource", "Page");
  }


}
