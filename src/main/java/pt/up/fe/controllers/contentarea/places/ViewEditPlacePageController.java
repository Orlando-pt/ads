package pt.up.fe.controllers.contentarea.places;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.dtos.places.PlaceType;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.DecimalNumberTextField;
import pt.up.fe.helpers.events.PageToSendCustomEvent;
import pt.up.fe.helpers.events.PlaceCustomEvent;
import pt.up.fe.helpers.events.PlaceFilterModeCustomEvent;
import pt.up.fe.helpers.events.SelectModeCustomEvent;
import pt.up.fe.helpers.events.SourceCustomEvent;
import pt.up.fe.places.Parish;
import pt.up.fe.places.Place;
import pt.up.fe.sources.Source;

public class ViewEditPlacePageController implements Initializable, IContentPageController {

  @FXML
  private Button selectSourceButton;

  @FXML
  private Button newSourceButton;

  @FXML
  private Button selectParent;

  @FXML
  private TextField nameInput;

  @FXML
  private ComboBox typeInput;

  @FXML
  private TextArea descriptionInput;

  @FXML
  private ToggleGroup source_radio;

  @FXML
  private Button saveButton;

  @FXML
  private DecimalNumberTextField latitudeInput;

  @FXML
  private DecimalNumberTextField longitudeInput;

  @FXML
  private DecimalNumberTextField altitudeInput;

  @FXML
  private Label areaLabel;

  @FXML
  private RadioButton selectSource;

  @FXML
  private DecimalNumberTextField areaInput;

  @FXML
  private RadioButton noSource;

  private Source selectedSource;

  private Place parentPlace;

  private Place selectedPlace;

  private boolean editMode = false;

  @Override
  public void initialize(URL url, ResourceBundle resources) {
    changePageMode();
    setButtonsInvisible();
  }

  @Override
  public void setEventHandlers() {
    CustomSceneHelper.getNodeById("viewEditPlacePage").addEventFilter(
        PlaceCustomEvent.PLACE, new EventHandler<PlaceCustomEvent>() {
          @Override
          public void handle(PlaceCustomEvent placeCustomEvent) {
            selectedPlace = placeCustomEvent.getPlace();
            setInfo();
          }
        });

    CustomSceneHelper.getNodeById("viewEditPlacePage")
        .addEventFilter(SourceCustomEvent.SOURCE, new EventHandler<SourceCustomEvent>() {
          @Override
          public void handle(SourceCustomEvent sourceCustomEvent) {
            selectedSource = sourceCustomEvent.getSource();
          }
        });
  }

  @Override
  public void clearPage() {
    nameInput.clear();
    latitudeInput.clear();
    longitudeInput.clear();
    altitudeInput.clear();
    areaInput.clear();
    typeInput.getSelectionModel().select(0);
    descriptionInput.clear();
    source_radio.selectToggle(noSource);
    setButtonsInvisible();
    selectedSource = null;
    parentPlace = null;
    selectParent.setText("Select Parent");
  }


  private void setButtonsInvisible() {
    newSourceButton.setVisible(false);
    selectSourceButton.setVisible(false);
  }

  @FXML
  private void selectParentPlace(MouseEvent event) {
    CustomSceneHelper.getNodeById("listPlacesPage")
        .fireEvent(new SelectModeCustomEvent(SelectModeCustomEvent.SELECT_MODE, true));
    CustomSceneHelper.getNodeById("listPlacesPage")
        .fireEvent(new PlaceFilterModeCustomEvent(PlaceFilterModeCustomEvent.FILTER_MODE,
            PlaceType.COMPOUND));
    CustomSceneHelper.getNodeById("listPlacesPage").fireEvent(
        new PageToSendCustomEvent(PageToSendCustomEvent.PAGE_TO_SEND,
            "createPlacePage"));
    CustomSceneHelper.bringNodeToFront("listPlaces", "Page");
  }

  public void savePlace() throws IllegalAccessException {
    if (editMode) {

    } else {
      this.clearPage();
      CustomSceneHelper.contentAreaPaneController.cleanAll();
      CustomSceneHelper.bringNodeToFront("listPlaces", "Page");
    }
  }

  @FXML
  private void onChangeType() {
    if (typeInput.getSelectionModel().getSelectedItem().toString().equals("Parish")) {
      areaInput.clear();
      areaInput.setVisible(true);
      areaLabel.setVisible(true);
    } else {
      areaInput.setVisible(false);
      areaInput.clear();
      areaLabel.setVisible(false);
    }
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
        new PageToSendCustomEvent(PageToSendCustomEvent.PAGE_TO_SEND, "createPlacePage"));
    CustomSceneHelper.bringNodeToFront("listSources", "Page");
  }

  @FXML
  private void addSource(MouseEvent event) {
    CustomSceneHelper.getNodeById("createSourcePage").fireEvent(new PageToSendCustomEvent(
        PageToSendCustomEvent.PAGE_TO_SEND, "createPlacePage"));
    CustomSceneHelper.bringNodeToFront("createSource", "Page");
  }

  private void changePageMode() {
    if (editMode) {
      descriptionInput.setEditable(true);
      nameInput.setEditable(true);
      latitudeInput.setEditable(true);
      longitudeInput.setEditable(true);
      altitudeInput.setEditable(true);
      areaInput.setEditable(true);
      typeInput.setDisable(false);
      source_radio.getToggles().forEach(toggle -> {
        Node node = (Node) toggle;
        node.setDisable(false);
      });
      saveButton.setText("Save");
    } else {
      descriptionInput.setEditable(false);
      nameInput.setEditable(false);
      latitudeInput.setEditable(false);
      longitudeInput.setEditable(false);
      altitudeInput.setEditable(false);
      areaInput.setEditable(false);
      typeInput.setDisable(true);
      source_radio.getToggles().forEach(toggle -> {
        Node node = (Node) toggle;
        node.setDisable(true);
      });
      saveButton.setText("OK");
    }
  }

  private void setInfo() {
    descriptionInput.setText(selectedPlace.getDescription());
    nameInput.setText(selectedPlace.getName());
    altitudeInput.setText(selectedPlace.getAltitude().toString());
    latitudeInput.setText(selectedPlace.getLatitude().toString());
    longitudeInput.setText(selectedPlace.getLongitude().toString());
    if (selectedPlace.getClass().getSimpleName().equals("Parish")) {
      areaInput.setText(((Parish) selectedPlace).getArea().toString());
      typeInput.setValue("Parish");
    } else {
      typeInput.setValue("Compound");
    }

    setButtonsInvisible();
    if (selectedSource != null) {
      source_radio.selectToggle(selectSource);
      selectSourceButton.setVisible(true);
      selectSourceButton.setText(selectedSource.getName());
    } else {
      if (selectedPlace.getSource() != null) {
        source_radio.selectToggle(selectSource);
        selectSourceButton.setVisible(true);
        selectSourceButton.setText(selectedPlace.getSource().getName());
      }
    }


    changePageMode();
  }


}
