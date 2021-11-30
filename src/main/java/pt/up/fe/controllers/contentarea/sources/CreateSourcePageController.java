package pt.up.fe.controllers.contentarea.sources;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import pt.up.fe.helpers.CustomSceneHelper;

public class CreateSourcePageController {

  @FXML
  ToggleGroup source_type;


  public CreateSourcePageController() {
  }

  @FXML
  private void initialize(URL url, ResourceBundle resources) {
  }

  @FXML
  private void goToSpecificSourceCreationPage(MouseEvent event) {
    RadioButton selectedRadioButton = (RadioButton) source_type.getSelectedToggle();
    String toogleGroupSelectedValueID = selectedRadioButton.getId();
    CustomSceneHelper.bringNodeToFront(toogleGroupSelectedValueID, "Page");
  }


}
