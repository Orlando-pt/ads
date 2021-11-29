package pt.up.fe.controllers.contentarea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.up.fe.App;

public class PersonsPageController {

  // Add a public no-args constructor
  public PersonsPageController() {
  }

  @FXML
  private void initialize(URL url, ResourceBundle resources) {
  }

  public void goToCreatePersonScreen(ActionEvent event) throws IOException {
    Scene peopleScene = new Scene(App.loadFXML("createPerson"));

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(peopleScene);
    stage.show();
  }


}
