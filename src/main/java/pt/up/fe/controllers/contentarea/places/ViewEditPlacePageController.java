package pt.up.fe.controllers.contentarea.places;

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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pt.up.fe.Main;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.dtos.persons.PersonTableDTO;
import pt.up.fe.dtos.places.PlaceDTO;
import pt.up.fe.dtos.places.PlaceTableDTO;
import pt.up.fe.dtos.places.PlaceType;
import pt.up.fe.facades.PlaceFacade;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.DecimalNumberTextField;
import pt.up.fe.helpers.events.PageToSendCustomEvent;
import pt.up.fe.helpers.events.PlaceCustomEvent;
import pt.up.fe.helpers.events.SelectModeCustomEvent;
import pt.up.fe.helpers.events.SourceCustomEvent;
import pt.up.fe.places.CompoundPlace;
import pt.up.fe.places.Parish;
import pt.up.fe.places.Place;
import pt.up.fe.sources.Source;

public class ViewEditPlacePageController implements Initializable, IContentPageController {

  @FXML
  private Button selectSourceButton;

  @FXML
  private Button newSourceButton;

  @FXML
  private Button addChild;

  @FXML
  private Button toCompound;

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
  private Label childrenLabel;

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

  @FXML
  private TableColumn<PersonTableDTO, String> tableName;

  @FXML
  private TableColumn<PersonTableDTO, PlaceType> tableType;

  @FXML
  private TableColumn<PersonTableDTO, Double> tableLatitude;

  @FXML
  private TableColumn<PersonTableDTO, Double> tableLongitude;

  @FXML
  private TableColumn<PersonTableDTO, Double> tableAltitude;

  @FXML
  private TableView<PlaceTableDTO> childrenTable;

  private Source selectedSource;

  ObservableList<PlaceTableDTO> childrenTableList = FXCollections.observableArrayList();

  private Place selectedPlace;

  @Override
  public void initialize(URL url, ResourceBundle resources) {
    typeInput.setDisable(true);
    tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
    tableLatitude.setCellValueFactory(new PropertyValueFactory<>("latitude"));
    tableLongitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));
    tableAltitude.setCellValueFactory(new PropertyValueFactory<>("altitude"));
    tableType.setCellValueFactory(new PropertyValueFactory<>("type"));

    childrenTable.setItems(childrenTableList);
    changePageMode();
    setButtonsInvisible();
  }

  @Override
  public void setEventHandlers() {
    CustomSceneHelper.getNodeById("viewEditPlacePage").addEventFilter(
        PlaceCustomEvent.PLACE, new EventHandler<PlaceCustomEvent>() {
          @Override
          public void handle(PlaceCustomEvent placeCustomEvent) {
            if (selectedPlace != null) {
              Place place = placeCustomEvent.getPlace();
              if (place != selectedPlace || !childrenTableList.stream().anyMatch(child -> child.getPlace() == place)) {
                String type = place.getClass().getSimpleName();
                if (type.length() > 6) {
                  type = type.substring(0, 8);
                }
                childrenTableList.add(new PlaceTableDTO(place.getName(),
                    PlaceType.valueOf(type.toUpperCase()), place.getLatitude(),
                    place.getLongitude(), place.getAltitude(), place));
              }
            } else {
              selectedPlace = placeCustomEvent.getPlace();
              setInfo();
            }
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
    changePageMode();
    childrenTableList.clear();
    selectedSource = null;
    selectedPlace = null;
  }


  private void setButtonsInvisible() {
    newSourceButton.setVisible(false);
    selectSourceButton.setVisible(false);
  }

  @FXML
  private void addChild(MouseEvent event) {
    CustomSceneHelper.getNodeById("listPlacesPage")
        .fireEvent(new SelectModeCustomEvent(SelectModeCustomEvent.SELECT_MODE, true));
    CustomSceneHelper.getNodeById("listPlacesPage").fireEvent(
        new PageToSendCustomEvent(PageToSendCustomEvent.PAGE_TO_SEND,
            "viewEditPlacePage"));
    CustomSceneHelper.bringNodeToFront("listPlaces", "Page");
  }

  public void savePlace() throws IllegalAccessException {
    if (Main.editMode) {
      if (selectedPlace.isComposite()) {
        childrenTableList.forEach(placeTableDTO -> {
          Place place = placeTableDTO.getPlace();
          if (!((CompoundPlace) selectedPlace).getChildren().contains(place)) {
            PlaceFacade.addChildToCompound((CompoundPlace) selectedPlace, place);
          }
        });
      }

      PlaceDTO placeDTO = new PlaceDTO();
      placeDTO.setName(nameInput.getText());
      placeDTO.setDescription(descriptionInput.getText());
      if (!altitudeInput.getText().isEmpty()) {
        placeDTO.setAltitude(Double.parseDouble(altitudeInput.getText()));
      }
      if (!longitudeInput.getText().isEmpty()) {
        placeDTO.setLongitude(Double.parseDouble(longitudeInput.getText()));
      }
      if (!latitudeInput.getText().isEmpty()) {
        placeDTO.setLatitude(Double.parseDouble(latitudeInput.getText()));
      }

      placeDTO.setType(
          PlaceType.valueOf(typeInput.getValue().toString().split(" ")[0].toUpperCase()));

      if (!areaInput.getText().isEmpty() && placeDTO.getType() != PlaceType.COMPOUND) {
        placeDTO.setArea(Double.parseDouble(areaInput.getText()));
      }

      if (selectedSource != null) {
        placeDTO.setSource(selectedSource);
      }

      PlaceFacade.editPlace(selectedPlace, placeDTO);

    }
    this.clearPage();
    CustomSceneHelper.contentAreaPaneController.cleanAll();
    CustomSceneHelper.bringNodeToFront("listPlaces", "Page");

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
  private void toCompound(MouseEvent event) {
    if (!selectedPlace.isComposite()) {
      Place newSelectedPlace = PlaceFacade.transformParishToCompound((Parish) selectedPlace);
      this.clearPage();
      selectedPlace = newSelectedPlace;
      this.setInfo();
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
    if (Main.editMode) {
      descriptionInput.setEditable(true);
      nameInput.setEditable(true);
      latitudeInput.setEditable(true);
      longitudeInput.setEditable(true);
      altitudeInput.setEditable(true);
      areaInput.setEditable(true);
      toCompound.setDisable(false);
      addChild.setDisable(false);
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
      toCompound.setDisable(true);
      addChild.setDisable(true);
      source_radio.getToggles().forEach(toggle -> {
        Node node = (Node) toggle;
        node.setDisable(true);
      });
      saveButton.setText("OK");
    }
  }

  private void setInfo() {
    descriptionInput.setText(
        ((selectedPlace.getDescription() == null) ? "" : selectedPlace.getDescription()));
    nameInput.setText(((selectedPlace.getName() == null) ? "" : selectedPlace.getName()));
    altitudeInput.setText(
        ((selectedPlace.getAltitude() == null) ? "" : selectedPlace.getAltitude().toString()));
    latitudeInput.setText(
        ((selectedPlace.getLatitude() == null) ? "" : selectedPlace.getLatitude().toString()));
    longitudeInput.setText(
        ((selectedPlace.getLongitude() == null) ? "" : selectedPlace.getLongitude().toString()));
    if (!selectedPlace.isComposite()) {
      areaInput.setText(((((Parish) selectedPlace).getArea() == null) ? "" : ((Parish) selectedPlace).getArea().toString()));
      typeInput.setValue("Parish");
      toCompound.setVisible(true);
      childrenTable.setVisible(false);
      addChild.setVisible(false);
      childrenLabel.setVisible(false);
      saveButton.setLayoutX(281);
      saveButton.setLayoutY(560);
    } else {
      typeInput.setValue("Compound Place");
      toCompound.setVisible(false);
      childrenTable.setVisible(true);
      addChild.setVisible(true);
      saveButton.setLayoutX(281);
      saveButton.setLayoutY(771);
      ((CompoundPlace) selectedPlace).getChildren().forEach(place -> {
        String type = place.getClass().getSimpleName();
        if (type.length() > 6) {
          type = type.substring(0, 8);
        }
        childrenTableList.add(new PlaceTableDTO(place.getName(),
            PlaceType.valueOf(type.toUpperCase()), place.getLatitude(),
            place.getLongitude(), place.getAltitude(), place));
      });
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
