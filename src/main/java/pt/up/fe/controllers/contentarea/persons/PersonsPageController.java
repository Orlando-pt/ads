package pt.up.fe.controllers.contentarea.persons;

import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import pt.up.fe.Main;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.helpers.CustomSceneHelper;

public class PersonsPageController implements Initializable, IContentPageController {

  @FXML
  private Button createPerson;

  @FXML
  public void initialize(URL url, ResourceBundle resources) {
  }

  @FXML
  private void goToAnotherPersonPage(MouseEvent event) {
    String buttonID = CustomSceneHelper.getSourceID(event.getSource());
    CustomSceneHelper.bringNodeToFront(buttonID, "Page");
  }


  @Override
  public void setEventHandlers() {

  }

  @Override
  public void clearPage() {
    if (Main.editMode) {
      createPerson.setVisible(true);
    } else {
      createPerson.setVisible(false);
    }

  }
}
