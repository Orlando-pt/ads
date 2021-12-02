package pt.up.fe.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pt.up.fe.Main;

public class MainController implements Initializable {

  // Add a public no-args constructor
  public MainController() {
  }

  public void goToPeopleScreen(ActionEvent event) throws IOException {
    Scene peopleScene = new Scene(Main.loadFXML("people"));

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(peopleScene);
    stage.show();
  }

  public void goToScreen(ActionEvent event) throws IOException {
    String name = ((Button) event.getSource()).getId();

    Scene scene = new Scene(Main.loadFXML(name));

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void initialize(URL url, ResourceBundle resources) {
  }
}

