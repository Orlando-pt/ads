package pt.up.fe.controllers.contentarea.places;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import pt.up.fe.Main;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.dtos.persons.PersonTableDTO;
import pt.up.fe.dtos.places.FilterPlacesDTO;
import pt.up.fe.dtos.places.PlaceTableDTO;
import pt.up.fe.dtos.places.PlaceType;
import pt.up.fe.facades.PlaceFacade;
import pt.up.fe.helpers.AutoCompleteTextField;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.events.PageToSendCustomEvent;
import pt.up.fe.helpers.events.PlaceCustomEvent;
import pt.up.fe.helpers.events.PlaceFilterModeCustomEvent;
import pt.up.fe.helpers.events.SelectModeCustomEvent;
import pt.up.fe.places.Place;

public class ListPlacesPageController implements Initializable, IContentPageController {

  @FXML
  private Button selectButton;

  @FXML
  private Label selectButtonLabel;

  @FXML
  private Button selectViewButton;

  @FXML
  private Label selectViewButtonLabel;

  @FXML
  private AutoCompleteTextField nameInput;

  @FXML
  private ComboBox placeType;

  @FXML
  private TableColumn<PersonTableDTO, String> name;

  @FXML
  private TableColumn<PersonTableDTO, PlaceType> type;

  @FXML
  private TableColumn<PersonTableDTO, Double> latitude;

  @FXML
  private TableColumn<PersonTableDTO, Double> longitude;

  @FXML
  private TableColumn<PersonTableDTO, Double> altitude;

  @FXML
  private TableView<PlaceTableDTO> placesTable;

  private Boolean selectMode = false;

  private String pageToSend;

  ObservableList<PlaceTableDTO> list = FXCollections.observableArrayList();

  @Override
  public void initialize(URL url, ResourceBundle resources) {
    Main.placesList.forEach(place -> {
      if(place.getName() != null) {
        nameInput.getEntries().add(place.getName());
      }
    });

    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    latitude.setCellValueFactory(new PropertyValueFactory<>("latitude"));
    longitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));
    altitude.setCellValueFactory(new PropertyValueFactory<>("altitude"));
    type.setCellValueFactory(new PropertyValueFactory<>("type"));

    this.clearPage();
    placesTable.setItems(list);
  }

  @Override
  public void setEventHandlers() {
    CustomSceneHelper.getNodeById("listPlacesPage").addEventFilter(
        SelectModeCustomEvent.SELECT_MODE, new EventHandler<SelectModeCustomEvent>() {
          @Override
          public void handle(SelectModeCustomEvent selectModeCustomEvent) {
            selectMode = selectModeCustomEvent.getSelectMode();
            changeButtonLayout();
          }
        });

    CustomSceneHelper.getNodeById("listPlacesPage").addEventFilter(
        PageToSendCustomEvent.PAGE_TO_SEND, new EventHandler<PageToSendCustomEvent>() {
          @Override
          public void handle(PageToSendCustomEvent pageToSendCustomEvent) {
            pageToSend = pageToSendCustomEvent.getPageToSend();
          }
        });

    CustomSceneHelper.getNodeById("listPlacesPage").addEventFilter(
        PlaceFilterModeCustomEvent.FILTER_MODE, new EventHandler<PlaceFilterModeCustomEvent>() {
          @Override
          public void handle(PlaceFilterModeCustomEvent filterModeCustomEvent) {
            if (filterModeCustomEvent.getFilterMode() == PlaceType.COMPOUND) {
              placeType.setDisable(true);
              placeType.getSelectionModel().select(2);
              filterPlaces();
            }
          }
        });
  }

  @FXML
  private void search(MouseEvent event) {
    filterPlaces();
  }

  @FXML
  private void selectPlace(MouseEvent event) {
    Place place = placesTable.getSelectionModel().getSelectedItem().getPlace();
    if (selectMode && pageToSend != null) {
      CustomSceneHelper.getNodeById(pageToSend)
          .fireEvent(new PlaceCustomEvent(PlaceCustomEvent.PLACE, place));
      CustomSceneHelper.bringNodeToFront(pageToSend, "");
    } else {
      CustomSceneHelper.getNodeById("viewEditPlacePage")
          .fireEvent(new PlaceCustomEvent(PlaceCustomEvent.PLACE, place));
      CustomSceneHelper.bringNodeToFront("viewEditPlace", "Page");
    }
  }

  private void filterPlaces() {
    FilterPlacesDTO filters = new FilterPlacesDTO();

    filters.setName(nameInput.getCharacters().toString());

    if (placeType.getSelectionModel().isSelected(1)) {
      filters.setType(PlaceType.PARISH);
    } else if (placeType.getSelectionModel().isSelected(2)) {
      filters.setType(PlaceType.COMPOUND);
    }

    list.clear();
    List<Place> placesFiltered = PlaceFacade.filterPlaces(filters);

    placesFiltered.forEach(place -> {
      String type = place.getClass().getSimpleName();
      if (type.length() > 6) {
        type = type.substring(0, 8);
      }
      list.add(new PlaceTableDTO(place.getName(),
          PlaceType.valueOf(type.toUpperCase()), place.getLatitude(),
          place.getLongitude(), place.getAltitude(), place));
    });
  }

  @Override
  public void clearPage() {
    nameInput.clear();
    placeType.getSelectionModel().select(0);
    this.filterPlaces();
    this.selectMode = false;
    pageToSend = null;
    placeType.setDisable(false);
    this.changeButtonLayout();
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
