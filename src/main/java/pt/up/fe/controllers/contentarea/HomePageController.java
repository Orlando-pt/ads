package pt.up.fe.controllers.contentarea;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import pt.up.fe.Main;

public class HomePageController implements Initializable, IContentPageController {

  @FXML
  ToggleGroup editType;

  @FXML
  RadioButton viewRadioButton;

  @FXML
  RadioButton editRadioButton;

  @FXML
  public void initialize(URL url, ResourceBundle resources) {
    Main.editMode = false;
    editType.selectToggle(viewRadioButton);
  }

  @FXML
  private void changeEditMode(ActionEvent event) {
    if (viewRadioButton.isSelected()) {
      Main.editMode = false;
    } else {
      Main.editMode = true;
    }
  }

  @Override
  public void setEventHandlers() {

  }

  @Override
  public void clearPage() {
  }


}
