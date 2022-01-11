package pt.up.fe.controllers.contentarea.sources;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.dates.IDate;
import pt.up.fe.dates.SimpleDate;
import pt.up.fe.dtos.sources.OnlineResourceDTO;
import pt.up.fe.facades.SourceFacade;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.events.DateCustomEvent;
import pt.up.fe.helpers.events.PageToSendCustomEvent;
import pt.up.fe.helpers.events.SourceCustomEvent;
import pt.up.fe.sources.Book;
import pt.up.fe.sources.CustomSource;
import pt.up.fe.sources.OnlineResource;

public class CreateOnlineResourcePageController implements Initializable, IContentPageController {

  @FXML
  private Button createOnlineResourceButton;

  @FXML
  private Button addAuthorButton;

  @FXML
  private Button addDateButton;

  @FXML
  private TextField onlineResourceNameInput;

  @FXML
  private TextField linkInput;

  @FXML
  private TextField authorNameInput;

  @FXML
  private TableColumn<String, String> authorNameColumn;

  @FXML
  private TableView<String> authorsTable;

  private String pageToSend;

  @FXML
  private TextField onlineResourceDate;

  private IDate date;

  private OnlineResource selectedOnlineResource;

  private boolean editMode = true;

  ObservableList<String> authorsList = FXCollections.observableArrayList();

  @FXML
  public void initialize(URL url, ResourceBundle resources) {
    authorNameColumn.setCellValueFactory(cellData ->
        new ReadOnlyStringWrapper(cellData.getValue()));
    authorsTable.setItems(authorsList);
  }

  @Override
  public void setEventHandlers() {
    CustomSceneHelper.getNodeById("createOnlineResourcePage").addEventFilter(
        SourceCustomEvent.SOURCE, new EventHandler<SourceCustomEvent>() {
          @Override
          public void handle(SourceCustomEvent sourceCustomEvent) {
            selectedOnlineResource = (OnlineResource) sourceCustomEvent.getSource();
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
  }

  @Override
  public void clearPage() {
    onlineResourceNameInput.clear();
    authorNameInput.clear();
    authorsList.clear();
    linkInput.clear();
    pageToSend = null;
    selectedOnlineResource = null;
    date = null;
    createOnlineResourceButton.setText("Create Online Resource");
    editMode = true;
    changePageMode();
  }

  public void setInfo() {
    onlineResourceNameInput.setText(selectedOnlineResource.getName());
    if (selectedOnlineResource.getDateOfPublication() != null) {
      date = selectedOnlineResource.getDateOfPublication();
      onlineResourceDate.setText(selectedOnlineResource.getDateOfPublication().toString());
    }
    authorsList.addAll(selectedOnlineResource.getAuthors());
    linkInput.setText(selectedOnlineResource.getLink());
    createOnlineResourceButton.setText("Save Online Resource");
    changePageMode();
  }

  private void changePageMode() {
    if (editMode) {
      onlineResourceNameInput.setEditable(true);
      addAuthorButton.setVisible(true);
      authorNameInput.setVisible(true);
      addDateButton.setVisible(true);
      linkInput.setEditable(true);
    } else {
      onlineResourceNameInput.setEditable(false);
      addAuthorButton.setVisible(false);
      authorNameInput.setVisible(false);
      addDateButton.setVisible(false);
      linkInput.setEditable(false);
    }
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

    CustomSceneHelper.getNodeById("createDatePage").addEventFilter(DateCustomEvent.DATE, new EventHandler<DateCustomEvent>() {
      @Override
      public void handle(DateCustomEvent dateCustomEvent) {
        date = dateCustomEvent.getDate();
        onlineResourceDate.setText(date.toString());

        CustomSceneHelper.getNodeById("createDatePage").removeEventFilter(DateCustomEvent.DATE, this); // Remove event handler
        CustomSceneHelper.bringNodeToFront("createOnlineResource", "Page");
      }
    });
  }

  @FXML
  private void createOnlineResource(MouseEvent event) throws IllegalAccessException {
    OnlineResourceDTO onlineResourceDTO = new OnlineResourceDTO();

    onlineResourceDTO.setName(onlineResourceNameInput.getCharacters().toString());
    onlineResourceDTO.setAuthors(authorsList);
    onlineResourceDTO.setDateOfPublication(date);
    onlineResourceDTO.setLink(linkInput.getCharacters().toString());

    OnlineResource onlineResource;

    if (selectedOnlineResource == null) {
      onlineResource = SourceFacade.createOnlineResource(onlineResourceDTO);
    } else {
      onlineResource = SourceFacade.editOnlineResource(selectedOnlineResource, onlineResourceDTO);
    }

    if (pageToSend != null) {
      CustomSceneHelper.getNodeById(pageToSend)
          .fireEvent(new SourceCustomEvent(SourceCustomEvent.SOURCE, onlineResource));
      CustomSceneHelper.bringNodeToFront(pageToSend, "");
    } else {
      CustomSceneHelper.contentAreaPaneController.cleanAll();
      CustomSceneHelper.bringNodeToFront("listSources", "Page");
    }
    this.clearPage();
  }


}
