package pt.up.fe.controllers.contentarea.sources;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.dates.IDate;
import pt.up.fe.dtos.sources.HistoricalRecordDTO;
import pt.up.fe.facades.SourceFacade;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.events.DateCustomEvent;
import pt.up.fe.helpers.events.PageToSendCustomEvent;
import pt.up.fe.helpers.events.PlaceCustomEvent;
import pt.up.fe.helpers.events.SelectModeCustomEvent;
import pt.up.fe.helpers.events.SourceCustomEvent;
import pt.up.fe.places.Place;
import pt.up.fe.sources.HistoricalRecord;

public class CreateHistoricalRecordPageController implements Initializable, IContentPageController {

  @FXML
  private Button createHistoricalRecordButton;

  @FXML
  private Button addAuthorButton;

  @FXML
  private TextField historicalRecordNameInput;

  @FXML
  private TextField authorNameInput;

  @FXML
  private TableColumn<String, String> authorNameColumn;

  @FXML
  private TableView<String> authorsTable;

  @FXML
  private Button selectPlaceButton;

  @FXML
  private Button newPlaceButton;

  @FXML
  private ToggleGroup place_radio;

  @FXML
  private RadioButton noPlace;

  private String pageToSend;

  @FXML
  private TextField historicalRecordDate;

  private IDate date;

  private Place selectedPlace;

  ObservableList<String> authorsList = FXCollections.observableArrayList();

  @FXML
  public void initialize(URL url, ResourceBundle resources) {
    authorNameColumn.setCellValueFactory(cellData ->
        new ReadOnlyStringWrapper(cellData.getValue()));
    authorsTable.setItems(authorsList);
    setPlaceButtonsInvisible();
  }

  @Override
  public void setEventHandlers() {
    CustomSceneHelper.getNodeById("createSourcePage").addEventFilter(
        PageToSendCustomEvent.PAGE_TO_SEND, new EventHandler<PageToSendCustomEvent>() {
          @Override
          public void handle(PageToSendCustomEvent pageToSendCustomEvent) {
            pageToSend = pageToSendCustomEvent.getPageToSend();
          }
        });

    CustomSceneHelper.getNodeById("createHistoricalRecordPage")
        .addEventFilter(PlaceCustomEvent.PLACE, new EventHandler<PlaceCustomEvent>() {
          @Override
          public void handle(PlaceCustomEvent placeCustomEvent) {
            selectedPlace = placeCustomEvent.getPlace();
          }
        });
  }

  @FXML
  public void openDateBuilder(ActionEvent event) {
    CustomSceneHelper.bringNodeToFront("CreateDate", "Page");

    CustomSceneHelper.getNodeById("createDatePage")
        .addEventFilter(DateCustomEvent.DATE, new EventHandler<DateCustomEvent>() {
          @Override
          public void handle(DateCustomEvent dateCustomEvent) {
            date = dateCustomEvent.getDate();
            historicalRecordDate.setText(date.toString());

            CustomSceneHelper.getNodeById("createDatePage")
                .removeEventFilter(DateCustomEvent.DATE, this); // Remove event handler
            CustomSceneHelper.bringNodeToFront("createHistoricalRecord", "Page");
          }
        });
  }

  @Override
  public void clearPage() {
    historicalRecordNameInput.clear();
    authorNameInput.clear();
    authorsList.clear();
    pageToSend = null;
    place_radio.selectToggle(noPlace);
    selectedPlace = null;
  }

  // Place

  private void setPlaceButtonsInvisible() {
    newPlaceButton.setVisible(false);
    selectPlaceButton.setVisible(false);
  }

  @FXML
  private void selectPlaceType(MouseEvent event) throws NoSuchFieldException {

    RadioButton selectedRadioButton = (RadioButton) place_radio.getSelectedToggle();
    String toogleGroupSelectedValueID = selectedRadioButton.getId();
    setPlaceButtonsInvisible();
    try {
      Field field = this.getClass().getDeclaredField(toogleGroupSelectedValueID + "Button");
      Button b = (Button) field.get(this);
      b.setVisible(true);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      // No need to do anything, button invalid.
    }
  }

  @FXML
  private void selectPlace(MouseEvent event) {
    CustomSceneHelper.getNodeById("listPlacesPage")
        .fireEvent(new SelectModeCustomEvent(SelectModeCustomEvent.SELECT_MODE, true));
    CustomSceneHelper.getNodeById("listPlacesPage").fireEvent(
        new PageToSendCustomEvent(PageToSendCustomEvent.PAGE_TO_SEND,
            "createHistoricalRecordPagePage"));
    CustomSceneHelper.bringNodeToFront("listPlaces", "Page");
  }

  @FXML
  private void addPlace(MouseEvent event) {
    CustomSceneHelper.getNodeById("createPlacePage").fireEvent(new PageToSendCustomEvent(
        PageToSendCustomEvent.PAGE_TO_SEND, "createHistoricalRecordPage"));
    CustomSceneHelper.bringNodeToFront("createPlace", "Page");
  }

  @FXML
  private void addAuthor(MouseEvent event) {
    String authorNameStr = authorNameInput.getCharacters().toString();
    if (!authorNameStr.isEmpty()) {
      authorsList.add(authorNameStr);
    }
  }

  @FXML
  private void createHistoricalRecord(MouseEvent event) throws IllegalAccessException {
    HistoricalRecordDTO historicalRecordDTO = new HistoricalRecordDTO();

    historicalRecordDTO.setName(historicalRecordNameInput.getCharacters().toString());
    historicalRecordDTO.setAuthors(authorsList);
    historicalRecordDTO.setDateOfPublication(date);
    historicalRecordDTO.setNationalArchiveCountry(selectedPlace);

    HistoricalRecord historicalRecord = SourceFacade.createHistoricalRecord(historicalRecordDTO);

    if (pageToSend != null) {
      CustomSceneHelper.getNodeById(pageToSend)
          .fireEvent(new SourceCustomEvent(SourceCustomEvent.SOURCE, historicalRecord));
      CustomSceneHelper.bringNodeToFront(pageToSend, "");
    } else {
      CustomSceneHelper.contentAreaPaneController.cleanAll();
      CustomSceneHelper.bringNodeToFront("listSources", "Page");
    }
    this.clearPage();
  }


}
