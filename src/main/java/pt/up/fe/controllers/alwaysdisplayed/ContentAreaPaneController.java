package pt.up.fe.controllers.alwaysdisplayed;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import pt.up.fe.controllers.contentarea.persons.CreatePersonPageController;
import pt.up.fe.controllers.contentarea.persons.ListPersonsPageController;
import pt.up.fe.controllers.contentarea.persons.PersonsPageController;
import pt.up.fe.controllers.contentarea.persons.ViewEditPersonPageController;
import pt.up.fe.controllers.contentarea.sources.CreateBookPageController;
import pt.up.fe.controllers.contentarea.sources.CreateCustomSourcePageController;
import pt.up.fe.controllers.contentarea.sources.CreateHistoricalRecordPageController;
import pt.up.fe.controllers.contentarea.sources.CreateOnlineResourcePageController;
import pt.up.fe.controllers.contentarea.sources.CreateOrallyTransmittedPageController;
import pt.up.fe.controllers.contentarea.sources.CreateSourcePageController;
import pt.up.fe.controllers.contentarea.sources.ListSourcesPageController;
import pt.up.fe.controllers.contentarea.sources.SourcesPageController;
import pt.up.fe.helpers.CustomSceneHelper;

public class ContentAreaPaneController implements Initializable {

  @FXML
  private CreatePersonPageController createPersonPageController;

  @FXML
  private PersonsPageController personsPageController;

  @FXML
  private ViewEditPersonPageController viewEditPersonPageController;

  @FXML
  private ListPersonsPageController listPersonsPageController;

  @FXML
  private SourcesPageController sourcesPageController;

  @FXML
  private CreateSourcePageController createSourcePageController;

  @FXML
  private CreateBookPageController createBookPageController;

  @FXML
  private CreateCustomSourcePageController createCustomSourcePageController;

  @FXML
  private CreateHistoricalRecordPageController createHistoricalRecordPageController;

  @FXML
  private CreateOnlineResourcePageController createOnlineResourcePageController;

  @FXML
  private CreateOrallyTransmittedPageController createOrallyTransmittedPageController;

  @FXML
  private ListSourcesPageController listSourcesPageController;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    CustomSceneHelper.contentAreaPaneController = this;
  }

  public void setEventHandlers(){
    createPersonPageController.setEventHandlers();
    listPersonsPageController.setEventHandlers();
  }

  public void cleanAll(){
    listPersonsPageController.clearPage();
    listSourcesPageController.clearPage();
  }

}