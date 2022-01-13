package pt.up.fe.controllers.contentarea.graphviz;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.events.PersonCustomEvent;
import pt.up.fe.person.Person;

public class GraphVizPageController implements Initializable, IContentPageController {

  @FXML
  private Text selectPersonLabel;

  @FXML
  private Button selectPersonButton;

  @FXML
  private TextField inputNamePNG;

  @FXML
  private Button generateButton;

  @FXML
  private Text fileSavedLabel;

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
            selectPersonLabel.setText(selectedPerson.getName() + " is selected");
            selectPersonLabel.setVisible(true);
          }
        });

  }

  @Override
  public void clearPage() {
    this.selectedPerson = null;
    selectPersonLabel.setVisible(false);

  }
}
