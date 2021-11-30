package pt.up.fe.controllers.contentarea.persons;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import pt.up.fe.helpers.CustomSceneHelper;

public class PersonsPageController {

  public PersonsPageController() {
  }

  @FXML
  private void initialize(URL url, ResourceBundle resources) {
  }

  @FXML
  private void goToAnotherPersonPage(MouseEvent event) {
    String buttonID = CustomSceneHelper.getSourceID(event.getSource());
    CustomSceneHelper.bringNodeToFront(buttonID, "Page");
  }


}
