package pt.up.fe.controllers.contentarea.graphviz;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.facades.PersonFacade;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.events.PageToSendCustomEvent;
import pt.up.fe.helpers.events.PersonCustomEvent;
import pt.up.fe.helpers.events.SelectModeCustomEvent;
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
    CustomSceneHelper.getNodeById("graphVizPage").addEventFilter(
        PersonCustomEvent.PERSON, new EventHandler<PersonCustomEvent>() {
          @Override
          public void handle(PersonCustomEvent personCustomEvent) {
            selectedPerson = personCustomEvent.getPerson();
            selectPersonLabel.setText(selectedPerson.getName() + " is selected");
            selectPersonLabel.setVisible(true);
          }
        });
  }

  @FXML
  private void selectPerson(MouseEvent event) {
    CustomSceneHelper.getNodeById("listPersonsPage")
        .fireEvent(new SelectModeCustomEvent(SelectModeCustomEvent.SELECT_MODE, true));
    CustomSceneHelper.getNodeById("listPersonsPage")
        .fireEvent(new PageToSendCustomEvent(PageToSendCustomEvent.PAGE_TO_SEND, "graphVizPage"));
    CustomSceneHelper.bringNodeToFront("listPersons", "Page");
  }


  @FXML
  private void generate(MouseEvent event) {
    if (selectedPerson != null && !inputNamePNG.getText().isEmpty()) {
      try {
        PersonFacade.createFamilyTreeVisualization(selectedPerson, inputNamePNG.getText());
        fileSavedLabel.setText("File has been saved to graphs/" + inputNamePNG.getText()
            + ".png on the current directory");

      } catch (Exception e) {
        fileSavedLabel.setText("There was an error while exporting");
        System.err.println("Error while exporting family tree.");
        e.printStackTrace();
      }
      fileSavedLabel.setVisible(true);
    }

  }

  @Override
  public void clearPage() {
    this.selectedPerson = null;
    selectPersonLabel.setVisible(false);
    fileSavedLabel.setVisible(false);
    inputNamePNG.clear();
    fileSavedLabel.setVisible(false);


  }
}
