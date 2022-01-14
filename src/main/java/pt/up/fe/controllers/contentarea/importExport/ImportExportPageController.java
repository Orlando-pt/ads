package pt.up.fe.controllers.contentarea.importExport;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.facades.ImportExportFacade;
import pt.up.fe.facades.PersonFacade;

public class ImportExportPageController implements Initializable, IContentPageController {

  @FXML
  private Button importButton;

  @FXML
  private Button exportButton;

  @FXML
  private Text exportLabel;

  @FXML
  private Text exportLabelQueries;

  @FXML
  public void initialize(URL url, ResourceBundle resources) {
  }


  @FXML
  private void importContent(MouseEvent event) {
    try {
      ImportExportFacade.importContent();
      exportLabel.setText("Import was successful");
    } catch (Exception e) {
      exportLabel.setText("There was an error while importing");
      e.printStackTrace();
    }
    exportLabel.setVisible(true);
  }


  @FXML
  private void exportContent(MouseEvent event) {
    try {
      ImportExportFacade.exportContent();
      exportLabel.setText("Export was successful");
    } catch (Exception e) {
      exportLabel.setText("There was an error while exporting");
      e.printStackTrace();
    }
    exportLabel.setVisible(true);

  }

  @FXML
  private void importQueries(MouseEvent event) {
    try {
      PersonFacade.importQueries();
      exportLabelQueries.setText("Import was successful");
    } catch (Exception e) {
      exportLabelQueries.setText("There was an error while importing");
      e.printStackTrace();
    }
    exportLabelQueries.setVisible(true);
  }


  @FXML
  private void exportQueries(MouseEvent event) {
    try {
      PersonFacade.exportQueries();
      exportLabelQueries.setText("Export was successful");
    } catch (Exception e) {
      exportLabelQueries.setText("There was an error while exporting");
      e.printStackTrace();
    }
    exportLabelQueries.setVisible(true);

  }


  @Override
  public void setEventHandlers() {

  }


  @Override
  public void clearPage() {
    exportLabelQueries.setVisible(false);
    exportLabel.setVisible(false);
  }
}
