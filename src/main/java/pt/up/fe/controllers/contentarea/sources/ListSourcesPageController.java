package pt.up.fe.controllers.contentarea.sources;

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
import pt.up.fe.dates.IDate;
import pt.up.fe.dtos.persons.PersonTableDTO;
import pt.up.fe.dtos.sources.FilterSourcesDTO;
import pt.up.fe.dtos.sources.SourceTableDTO;
import pt.up.fe.facades.SourceFacade;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.events.SelectModeCustomEvent;
import pt.up.fe.helpers.events.SourceCustomEvent;
import pt.up.fe.places.Place;
import pt.up.fe.sources.Book;
import pt.up.fe.sources.HistoricalRecord;
import pt.up.fe.sources.OnlineResource;
import pt.up.fe.sources.OrallyTransmitted;
import pt.up.fe.sources.Source;

public class ListSourcesPageController implements Initializable, IContentPageController {

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
  private TableColumn<SourceTableDTO, String> type;

  @FXML
  private TableColumn<SourceTableDTO, String> name;

  @FXML
  private TableColumn<SourceTableDTO, IDate> dateOfPublication;

  @FXML
  private TableColumn<SourceTableDTO, String> authors;

  @FXML
  private TableColumn<SourceTableDTO, Integer> pages;

  @FXML
  private TableColumn<SourceTableDTO, String> publisher;

  @FXML
  private TableColumn<PersonTableDTO, Place> nationalArchiveCountry;

  @FXML
  private TableColumn<PersonTableDTO, String> link;

  @FXML
  private TableColumn<PersonTableDTO, Place> place;

  @FXML
  private TableView<SourceTableDTO> sourcesTable;

  ObservableList<SourceTableDTO> list = FXCollections.observableArrayList();

  @Override
  public void initialize(URL url, ResourceBundle resources) {
    type.setCellValueFactory(new PropertyValueFactory<>("type"));
    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    dateOfPublication.setCellValueFactory(new PropertyValueFactory<>("dateOfPublication"));
    authors.setCellValueFactory(new PropertyValueFactory<>("authors"));
    pages.setCellValueFactory(new PropertyValueFactory<>("pages"));
    publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
    nationalArchiveCountry.setCellValueFactory(
        new PropertyValueFactory<>("nationalArchiveCountry"));
    link.setCellValueFactory(new PropertyValueFactory<>("link"));
    place.setCellValueFactory(new PropertyValueFactory<>("place"));

    this.changeButtonLayout(true);
    this.clearPage();
    sourcesTable.setItems(list);
  }

  @Override
  public void setEventHandlers() {
    CustomSceneHelper.getNodeById("listSourcesPage").addEventFilter(
        SelectModeCustomEvent.SELECT_MODE, new EventHandler<SelectModeCustomEvent>() {
          @Override
          public void handle(SelectModeCustomEvent selectModeCustomEvent) {
            changeButtonLayout(selectModeCustomEvent.getSelectMode());
          }
        });
  }

  @FXML
  private void search(MouseEvent event) {
    filterSources();
  }

  @FXML
  private void selectSource(MouseEvent event) {
    Source source = sourcesTable.getSelectionModel().getSelectedItem().getSource();
    CustomSceneHelper.getNodeById("createPersonPage")
        .fireEvent(new SourceCustomEvent(SourceCustomEvent.SOURCE, source));
    CustomSceneHelper.bringNodeToFront("createPerson", "Page");
    this.clearPage();
    this.changeButtonLayout(false);
  }

  private void filterSources() {
    FilterSourcesDTO filters = new FilterSourcesDTO();

    filters.setName(nameInput.getCharacters().toString());

    list.clear();
    List<Source> sourcesFiltered = SourceFacade.filterSources(filters);

    sourcesFiltered.forEach(source -> {
      SourceTableDTO sourceTableDTO = new SourceTableDTO(source.getClass().getSimpleName(),
          source.getName(),
          source.getDateOfPublication(), source.getAuthors().toString(), source);

      switch (source.getClass().getSimpleName()) {
        case "Book":
          sourceTableDTO.setPages(((Book) source).getPages());
          sourceTableDTO.setPublisher(((Book) source).getPublisher());
          break;
        case "HistoricalRecord":
          sourceTableDTO.setNationalArchiveCountry(
              ((HistoricalRecord) source).getNationalArchiveCountry());
          break;
        case "OnlineResource":
          sourceTableDTO.setLink(((OnlineResource) source).getLink());
          break;
        case "OrallyTransmitted":
          sourceTableDTO.setPlace(((OrallyTransmitted) source).getPlace());
          break;
      }
      list.add(sourceTableDTO);
    });

  }

  @Override
  public void clearPage() {
    nameInput.clear();
    this.filterSources();
    this.changeButtonLayout(false);
  }

  private void changeButtonLayout(Boolean selectMode) {
    if (selectMode) {
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
