package pt.up.fe.controllers.alwaysdisplayed;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import pt.up.fe.controllers.contentarea.HomePageController;
import pt.up.fe.controllers.contentarea.events.*;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.controllers.contentarea.dates.CreateDateController;
import pt.up.fe.controllers.contentarea.persons.CreatePersonPageController;
import pt.up.fe.controllers.contentarea.persons.ListPersonsPageController;
import pt.up.fe.controllers.contentarea.persons.PersonsPageController;
import pt.up.fe.controllers.contentarea.persons.ViewEditPersonPageController;
import pt.up.fe.controllers.contentarea.places.CreatePlacePageController;
import pt.up.fe.controllers.contentarea.places.ListPlacesPageController;
import pt.up.fe.controllers.contentarea.places.PlacesPageController;
import pt.up.fe.controllers.contentarea.places.ViewEditPlacePageController;
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

  @FXML
  private EventController eventsPageController;

  @FXML
  private CreateEventController createEventPageController;

  @FXML
  private BirthEventController birthEventPageController;

  @FXML
  private DeathEventController deathEventPageController;

  @FXML
  private EmigrationEventController emigrationEventPageController;

  @FXML
  private MarriageEventController marriageEventPageController;

  @FXML
  private ResidenceEventController residenceEventPageController;

  @FXML
  private CustomEventController customEventPageController;

  @FXML
  private CreateDateController createDatePageController;

  @FXML
  private CreatePlacePageController createPlacePageController;

  @FXML
  private PlacesPageController placesPageController;

  @FXML
  private ListPlacesPageController listPlacesPageController;

  @FXML
  private ViewEditPlacePageController viewEditPlacePageController;

  @FXML
  private HomePageController homePageController;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    CustomSceneHelper.contentAreaPaneController = this;
  }

  public void setEventHandlers() throws IllegalAccessException {
    Field[] fields = this.getClass().getDeclaredFields();
    for (Field field : fields) {
      IContentPageController controller = (IContentPageController) field.get(this);
      controller.setEventHandlers();
    }

  }

  public void cleanAll() throws IllegalAccessException {
    Field[] fields = this.getClass().getDeclaredFields();
    for (Field field : fields) {
      IContentPageController controller = (IContentPageController) field.get(this);
      controller.clearPage();
    }
  }

}
