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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import pt.up.fe.Main;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.dates.IDate;
import pt.up.fe.dtos.sources.OrallyTransmittedDTO;
import pt.up.fe.facades.SourceFacade;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.events.DateCustomEvent;
import pt.up.fe.helpers.events.PageToSendCustomEvent;
import pt.up.fe.helpers.events.PlaceCustomEvent;
import pt.up.fe.helpers.events.SelectModeCustomEvent;
import pt.up.fe.helpers.events.SourceCustomEvent;
import pt.up.fe.places.Place;
import pt.up.fe.sources.HistoricalRecord;
import pt.up.fe.sources.OrallyTransmitted;

public class CreateOrallyTransmittedPageController implements Initializable,
    IContentPageController {

  @FXML
  private Button createOrallyTransmittedButton;

  @FXML
  private Button addAuthorButton;

  @FXML
  private Button addDateButton;

  @FXML
  private TextField orallyTransmittedNameInput;

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

  @FXML
  private RadioButton selectPlace;

  private Place selectedPlace;

  private String pageToSend;

  @FXML
  private TextField orallyTransmittedDate;

  private IDate date;

  private OrallyTransmitted selectedOrallyTransmitted;

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
    CustomSceneHelper.getNodeById("createOrallyTransmittedPage").addEventFilter(
        SourceCustomEvent.SOURCE, new EventHandler<SourceCustomEvent>() {
          @Override
          public void handle(SourceCustomEvent sourceCustomEvent) {
            selectedOrallyTransmitted = (OrallyTransmitted) sourceCustomEvent.getSource();
            setInfo();
          }
        });

    CustomSceneHelper.getNodeById("createSourcePage").addEventFilter(
        PageToSendCustomEvent.PAGE_TO_SEND, new EventHandler<PageToSendCustomEvent>() {
          @Override
          public void handle(PageToSendCustomEvent pageToSendCustomEvent) {
            pageToSend = pageToSendCustomEvent.getPageToSend();
          }
        });

    CustomSceneHelper.getNodeById("createOrallyTransmittedPage")
        .addEventFilter(PlaceCustomEvent.PLACE, new EventHandler<PlaceCustomEvent>() {
          @Override
          public void handle(PlaceCustomEvent placeCustomEvent) {
            selectedPlace = placeCustomEvent.getPlace();
            place_radio.selectToggle(selectPlace);
            selectPlaceButton.setVisible(true);
            selectPlaceButton.setText(selectedPlace.getName());
          }
        });


  }

  @Override
  public void clearPage() {
    orallyTransmittedNameInput.clear();
    authorNameInput.clear();
    authorsList.clear();
    pageToSend = null;
    place_radio.selectToggle(noPlace);
    selectedPlace = null;
    selectedOrallyTransmitted = null;
    date = null;
    createOrallyTransmittedButton.setText("Create Historical Record");
    changePageMode();
  }

  public void setInfo() {
    changePageMode();
    orallyTransmittedNameInput.setText(selectedOrallyTransmitted.getName());
    if (selectedOrallyTransmitted.getDateOfPublication() != null) {
      date = selectedOrallyTransmitted.getDateOfPublication();
      orallyTransmittedDate.setText(selectedOrallyTransmitted.getDateOfPublication().toString());
    }
    authorsList.addAll(selectedOrallyTransmitted.getAuthors());
    createOrallyTransmittedButton.setText("Save Historical Record");
    if (selectedOrallyTransmitted.getPlace() != null) {
      place_radio.selectToggle(selectPlace);
      selectedPlace = selectedOrallyTransmitted.getPlace();
      selectPlaceButton.setVisible(true);
      selectPlaceButton.setText(selectedOrallyTransmitted.getPlace().getName());
    }


  }

  private void changePageMode() {
    if (Main.editMode) {
      orallyTransmittedNameInput.setEditable(true);
      addAuthorButton.setVisible(true);
      authorNameInput.setVisible(true);
      addDateButton.setVisible(true);
      place_radio.getToggles().forEach(toggle -> {
        Node node = (Node) toggle;
        node.setDisable(false);
      });
    } else {
      orallyTransmittedNameInput.setEditable(false);
      addAuthorButton.setVisible(false);
      authorNameInput.setVisible(false);
      addDateButton.setVisible(false);
      place_radio.getToggles().forEach(toggle -> {
        Node node = (Node) toggle;
        node.setDisable(true);
      });
    }
    setPlaceButtonsInvisible();
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
            "createOrallyTransmittedPage"));
    CustomSceneHelper.bringNodeToFront("listPlaces", "Page");
  }

  @FXML
  private void addPlace(MouseEvent event) {
    CustomSceneHelper.getNodeById("createPlacePage").fireEvent(new PageToSendCustomEvent(
        PageToSendCustomEvent.PAGE_TO_SEND, "createOrallyTransmittedPage"));
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
  public void openDateBuilder(ActionEvent event) {
    CustomSceneHelper.bringNodeToFront("CreateDate", "Page");

    CustomSceneHelper.getNodeById("createDatePage")
        .addEventFilter(DateCustomEvent.DATE, new EventHandler<DateCustomEvent>() {
          @Override
          public void handle(DateCustomEvent dateCustomEvent) {
            date = dateCustomEvent.getDate();
            orallyTransmittedDate.setText(date.toString());

            CustomSceneHelper.getNodeById("createDatePage")
                .removeEventFilter(DateCustomEvent.DATE, this); // Remove event handler
            CustomSceneHelper.bringNodeToFront("createOrallyTransmitted", "Page");
          }
        });
  }

  @FXML
  private void createOrallyTransmitted(MouseEvent event) throws IllegalAccessException {
    OrallyTransmittedDTO orallyTransmittedDTO = new OrallyTransmittedDTO();

    orallyTransmittedDTO.setName(orallyTransmittedNameInput.getCharacters().toString());
    orallyTransmittedDTO.setAuthors(authorsList);
    orallyTransmittedDTO.setDateOfPublication(date);
    orallyTransmittedDTO.setPlace(selectedPlace);

    OrallyTransmitted orallyTransmitted;

    if (selectedOrallyTransmitted == null) {
      orallyTransmitted = SourceFacade.createOrallyTransmitted(orallyTransmittedDTO);
    } else {
      orallyTransmitted = SourceFacade.editOrallyTransmitted(selectedOrallyTransmitted,
          orallyTransmittedDTO);
    }

    if (pageToSend != null) {
      CustomSceneHelper.getNodeById(pageToSend)
          .fireEvent(new SourceCustomEvent(SourceCustomEvent.SOURCE, orallyTransmitted));
      CustomSceneHelper.bringNodeToFront(pageToSend, "");
    } else {
      CustomSceneHelper.contentAreaPaneController.cleanAll();
      CustomSceneHelper.bringNodeToFront("listSources", "Page");
    }
    this.clearPage();
  }
}
