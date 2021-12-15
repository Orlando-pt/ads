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
import pt.up.fe.dtos.sources.CustomSourceDTO;
import pt.up.fe.facades.SourceFacade;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.events.DateCustomEvent;
import pt.up.fe.helpers.events.PageToSendCustomEvent;
import pt.up.fe.helpers.events.SourceCustomEvent;
import pt.up.fe.sources.CustomSource;

public class CreateCustomSourcePageController implements Initializable, IContentPageController {

  @FXML
  private Button createCustomSourceButton;

  @FXML
  private Button addAuthorButton;

  @FXML
  private TextField customSourceNameInput;

  @FXML
  private TextField authorNameInput;

  @FXML
  private TableColumn<String, String> authorNameColumn;

  @FXML
  private TableView<String> authorsTable;

  private String pageToSend;

  @FXML
  private TextField customSourceDate;

  private IDate date;

  ObservableList<String> authorsList = FXCollections.observableArrayList();

  @FXML
  public void initialize(URL url, ResourceBundle resources) {
    authorNameColumn.setCellValueFactory(cellData ->
        new ReadOnlyStringWrapper(cellData.getValue()));
    authorsTable.setItems(authorsList);
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
  }

  @FXML
  public void openDateBuilder(ActionEvent event) {
    CustomSceneHelper.bringNodeToFront("CreateDate", "Page");

    CustomSceneHelper.getNodeById("createDatePage").addEventFilter(DateCustomEvent.DATE, new EventHandler<DateCustomEvent>() {
      @Override
      public void handle(DateCustomEvent dateCustomEvent) {
        date = dateCustomEvent.getDate();
        customSourceDate.setText(date.toString());

        CustomSceneHelper.getNodeById("createDatePage").removeEventFilter(DateCustomEvent.DATE, this); // Remove event handler
        CustomSceneHelper.bringNodeToFront("createCustomSource", "Page");
      }
    });
  }

  @Override
  public void clearPage() {
    customSourceNameInput.clear();
    authorNameInput.clear();
    authorsList.clear();
    pageToSend = null;
  }

  @FXML
  private void addAuthor(MouseEvent event) {
    String authorNameStr = authorNameInput.getCharacters().toString();
    if (!authorNameStr.isEmpty()) {
      authorsList.add(authorNameStr);
    }
  }

  @FXML
  private void createCustomSource(MouseEvent event) throws IllegalAccessException {
    CustomSourceDTO customSourceDTO = new CustomSourceDTO();

    customSourceDTO.setName(customSourceNameInput.getCharacters().toString());
    customSourceDTO.setAuthors(authorsList);
    customSourceDTO.setDateOfPublication(date);

    CustomSource customSource = SourceFacade.createCustomSource(customSourceDTO);

    if (pageToSend != null) {
      CustomSceneHelper.getNodeById(pageToSend)
          .fireEvent(new SourceCustomEvent(SourceCustomEvent.SOURCE, customSource));
      CustomSceneHelper.bringNodeToFront(pageToSend, "");
    } else {
      CustomSceneHelper.contentAreaPaneController.cleanAll();
      CustomSceneHelper.bringNodeToFront("listSources", "Page");
    }
    this.clearPage();
  }


}
