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
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.events.PageToSendCustomEvent;
import pt.up.fe.helpers.events.PersonCustomEvent;
import pt.up.fe.helpers.events.SelectModeCustomEvent;
import pt.up.fe.helpers.events.SourceCustomEvent;
import pt.up.fe.person.Person;

public class ViewEditPersonPageController implements Initializable, IContentPageController {

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
  private RadioButton noSource;

  private Person selectedPerson;

  private boolean editMode = false;


  @FXML
  public void initialize(URL url, ResourceBundle resources) {
    changePageMode();
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

    CustomSceneHelper.getNodeById("viewEditPersonPage")
        .addEventFilter(SourceCustomEvent.SOURCE, new EventHandler<SourceCustomEvent>() {
          @Override
          public void handle(SourceCustomEvent sourceCustomEvent) {
            selectedPerson.setSource(sourceCustomEvent.getSource());
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

    if (selectedPerson.getSource() != null) {
      newSourceButton.setVisible(false);
      newSourceButton.setText(selectedPerson.getSource().getName());
    } else {
      setButtonsInvisible();
    }
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
        new PageToSendCustomEvent(PageToSendCustomEvent.PAGE_TO_SEND, "ViewEditPersonPage"));
    CustomSceneHelper.bringNodeToFront("listSources", "Page");
  }

  @FXML
  private void addSource(MouseEvent event) {
    CustomSceneHelper.getNodeById("createSourcePage").fireEvent(new PageToSendCustomEvent(
        PageToSendCustomEvent.PAGE_TO_SEND, "ViewEditPersonPage"));
    CustomSceneHelper.bringNodeToFront("createSource", "Page");
  }

  private void changePageMode() {
    if (editMode) {
      descriptionInput.setEditable(true);
      firstNameInput.setEditable(true);
      middleNameInput.setEditable(true);
      lastNameInput.setEditable(true);
      genderInput.setEditable(true);
    } else {
      descriptionInput.setEditable(false);
      firstNameInput.setEditable(false);
      middleNameInput.setEditable(false);
      lastNameInput.setEditable(false);
      genderInput.setEditable(false);

    }
  }

  private void setButtonsInvisible() {
    newSourceButton.setVisible(false);
    selectSourceButton.setVisible(false);
  }

  @Override
  public void clearPage() {
    this.selectedPerson = null;
    descriptionInput.clear();
    firstNameInput.clear();
    middleNameInput.clear();
    lastNameInput.clear();
    genderInput.getSelectionModel().select(0);
    source_radio.selectToggle(noSource);
    setButtonsInvisible();
    descriptionInput.clear();
  }
}
