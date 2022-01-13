package pt.up.fe.controllers.contentarea.events;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.helpers.CustomSceneHelper;

public class EventController implements Initializable, IContentPageController {

  @FXML
  private void goToAnotherPage(MouseEvent event) {
    String buttonID = CustomSceneHelper.getSourceID(event.getSource());
    CustomSceneHelper.bringNodeToFront(buttonID, "Page");
  }

  @FXML
  public void initialize(URL url, ResourceBundle resources) {
  }

  @Override
  public void setEventHandlers() {
  }

  @Override
  public void clearPage() {
  }
}
