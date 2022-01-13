package pt.up.fe.controllers.contentarea.importExport;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import pt.up.fe.controllers.contentarea.IContentPageController;

public class ImportExportPageController implements Initializable, IContentPageController {

  @FXML
  private Button importButton;

  @FXML
  private Button exportButton;

  @FXML
  private Text exportLabel;

  @FXML
  public void initialize(URL url, ResourceBundle resources) {
  }


  @FXML
  private void importContent(MouseEvent event) {

  }


  @FXML
  private void exportContent(MouseEvent event) {

  }


  @Override
  public void setEventHandlers() {

  }


  @Override
  public void clearPage() {

  }
}
