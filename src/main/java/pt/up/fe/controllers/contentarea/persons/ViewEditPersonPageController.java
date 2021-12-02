package pt.up.fe.controllers.contentarea.persons;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.events.PersonCustomEvent;
import pt.up.fe.person.Person;

public class ViewEditPersonPageController implements Initializable, IContentPageController {

  private Person selectedPerson;


  @FXML
  public void initialize(URL url, ResourceBundle resources) {
  }


  @Override
  public void setEventHandlers() {
    CustomSceneHelper.getNodeById("viewEditPersonPage").addEventFilter(
        PersonCustomEvent.PERSON, new EventHandler<PersonCustomEvent>() {
          @Override
          public void handle(PersonCustomEvent personCustomEvent) {
            selectedPerson = personCustomEvent.getPerson();
            System.out.println(selectedPerson);
          }
        });
  }

  @Override
  public void clearPage() {
    this.selectedPerson = null;

  }
}
