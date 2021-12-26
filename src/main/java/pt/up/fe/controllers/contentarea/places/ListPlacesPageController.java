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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.dtos.persons.PersonTableDTO;
import pt.up.fe.dtos.places.FilterPlacesDTO;
import pt.up.fe.dtos.places.PlaceTableDTO;
import pt.up.fe.dtos.places.PlaceType;
import pt.up.fe.facades.PlaceFacade;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.events.PageToSendCustomEvent;
import pt.up.fe.helpers.events.PlaceCustomEvent;
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
  private TextField nameInput;

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
      CustomSceneHelper.getNodeById("viewEditPLacePage")
          .fireEvent(new PlaceCustomEvent(PlaceCustomEvent.PLACE, place));
      CustomSceneHelper.bringNodeToFront("viewEditPlace", "Page");
    }


    System.out.println(place);
  }

  private void filterPlaces() {
    FilterPlacesDTO filters = new FilterPlacesDTO();

    filters.setName(nameInput.getCharacters().toString());


    list.clear();
    List<Place> placesFiltered = PlaceFacade.filterPlaces(filters);

    placesFiltered.forEach(place -> {
      list.add(new PlaceTableDTO(place.getName(), PlaceType.valueOf(place.getClass().getSimpleName().toUpperCase()), place.getAltitude(),
          place.getLongitude(), place.getAltitude(), place));
    });
  }

  @Override
  public void clearPage() {
    nameInput.clear();
    this.filterPlaces();
    this.selectMode = false;
    pageToSend = null;
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
