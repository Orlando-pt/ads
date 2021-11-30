package pt.up.fe.controllers.contentarea.sources;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import pt.up.fe.helpers.CustomSceneHelper;

public class SourcesPageController {

  public SourcesPageController() {
  }

  @FXML
  private void initialize(URL url, ResourceBundle resources) {
  }

  @FXML
  private void goToAnotherSourcePage(MouseEvent event) {
    String buttonID = CustomSceneHelper.getSourceID(event.getSource());
    CustomSceneHelper.bringNodeToFront(buttonID, "Page");
  }


}
