package pt.up.fe.controllers.contentarea.sources;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.dates.SimpleDate;
import pt.up.fe.dtos.sources.OrallyTransmittedDTO;
import pt.up.fe.facades.SourceFacade;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.events.PageToSendCustomEvent;
import pt.up.fe.helpers.events.SourceCustomEvent;
import pt.up.fe.sources.OrallyTransmitted;

public class CreateOrallyTransmittedPageController implements Initializable,
    IContentPageController {

  @FXML
  private Button createOrallyTransmittedRecordButton;

  @FXML
  private Button addAuthorButton;

  @FXML
  private TextField orallyTransmittedNameInput;

  @FXML
  private TextField authorNameInput;

  @FXML
  private TableColumn<String, String> authorNameColumn;

  @FXML
  private TableView<String> authorsTable;

  private String pageToSend;

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

  @Override
  public void clearPage() {
    orallyTransmittedNameInput.clear();
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
  private void createOrallyTransmitted(MouseEvent event) {
    OrallyTransmittedDTO orallyTransmittedDTO = new OrallyTransmittedDTO();

    orallyTransmittedDTO.setName(orallyTransmittedNameInput.getCharacters().toString());
    orallyTransmittedDTO.setAuthors(authorsList);
    orallyTransmittedDTO.setDateOfPublication(new SimpleDate());

    OrallyTransmitted orallyTransmitted = SourceFacade.createOrallyTransmitted(
        orallyTransmittedDTO);

    if (pageToSend != null) {
      CustomSceneHelper.getNodeById(pageToSend)
          .fireEvent(new SourceCustomEvent(SourceCustomEvent.SOURCE, orallyTransmitted));
      pageToSend = null;
    }
  }
}
