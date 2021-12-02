package pt.up.fe.controllers.contentarea.sources;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.helpers.CustomSceneHelper;

public class SourcesPageController implements Initializable, IContentPageController {

  @FXML
  public void initialize(URL url, ResourceBundle resources) {
  }

  @FXML
  private void goToAnotherSourcePage(MouseEvent event) {
    String buttonID = CustomSceneHelper.getSourceID(event.getSource());
    CustomSceneHelper.bringNodeToFront(buttonID, "Page");
  }


  @Override
  public void setEventHandlers() {

  }

  @Override
  public void clearPage() {

  }
}
