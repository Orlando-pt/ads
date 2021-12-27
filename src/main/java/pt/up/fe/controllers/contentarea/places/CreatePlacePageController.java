package pt.up.fe.controllers.contentarea.places;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.dtos.places.PlaceDTO;
import pt.up.fe.dtos.places.PlaceType;
import pt.up.fe.facades.PlaceFacade;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.NumberTextField;
import pt.up.fe.helpers.events.PageToSendCustomEvent;
import pt.up.fe.helpers.events.PlaceCustomEvent;
import pt.up.fe.helpers.events.SelectModeCustomEvent;
import pt.up.fe.helpers.events.SourceCustomEvent;
import pt.up.fe.places.Place;
import pt.up.fe.sources.Source;

public class CreatePlacePageController implements Initializable, IContentPageController {

  @FXML
  private Button selectSourceButton;

  @FXML
  private Button newSourceButton;

  @FXML
  private TextField nameInput;

  @FXML
  private ComboBox typeInput;

  @FXML
  private TextArea descriptionInput;

  @FXML
  private ToggleGroup source_radio;

  @FXML
  private Button createButton;

  @FXML
  private NumberTextField latitudeInput;

  @FXML
  private NumberTextField longitudeInput;

  @FXML
  private NumberTextField altitudeInput;

  @FXML
  private Label areaLabel;

  @FXML
  private NumberTextField areaInput;

  @FXML
  private RadioButton noSource;

  private Source selectedSource;

  private String pageToSend;

  @Override
  public void initialize(URL url, ResourceBundle resources) {
    setButtonsInvisible();
  }

  @Override
  public void setEventHandlers() {
    CustomSceneHelper.getNodeById("createPlacePage")
        .addEventFilter(SourceCustomEvent.SOURCE, new EventHandler<SourceCustomEvent>() {
          @Override
          public void handle(SourceCustomEvent sourceCustomEvent) {
            selectedSource = sourceCustomEvent.getSource();
          }
        });

    CustomSceneHelper.getNodeById("createPlacePage").addEventFilter(
        PageToSendCustomEvent.PAGE_TO_SEND, new EventHandler<PageToSendCustomEvent>() {
          @Override
          public void handle(PageToSendCustomEvent pageToSendCustomEvent) {
            pageToSend = pageToSendCustomEvent.getPageToSend();
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
    selectedSource = null;
    pageToSend = null;
  }


  private void setButtonsInvisible() {
    newSourceButton.setVisible(false);
    selectSourceButton.setVisible(false);
  }

  @FXML
  private void createPlace(MouseEvent event) throws IllegalAccessException {
    PlaceDTO placeDTO = new PlaceDTO();
    placeDTO.setName(nameInput.getText());
    if (!altitudeInput.getText().isEmpty()) {
      placeDTO.setAltitude(Double.parseDouble(altitudeInput.getText()));
    }
    if (!longitudeInput.getText().isEmpty()) {
      placeDTO.setLongitude(Double.parseDouble(longitudeInput.getText()));
    }
    if (!latitudeInput.getText().isEmpty()) {
      placeDTO.setLatitude(Double.parseDouble(latitudeInput.getText()));
    }
    if (!areaInput.getText().isEmpty()) {
      placeDTO.setArea(Double.parseDouble(areaInput.getText()));
    }
    placeDTO.setType(PlaceType.valueOf(typeInput.getValue().toString().toUpperCase()));
    placeDTO.setSource(selectedSource);

    Place place = PlaceFacade.createPlace(placeDTO);

    if (pageToSend != null) {
      CustomSceneHelper.getNodeById(pageToSend)
          .fireEvent(new PlaceCustomEvent(PlaceCustomEvent.PLACE, place));
      CustomSceneHelper.bringNodeToFront(pageToSend, "");
    } else {
      CustomSceneHelper.contentAreaPaneController.cleanAll();
      CustomSceneHelper.bringNodeToFront("listPlaces", "Page");
    }
    this.clearPage();
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


}
