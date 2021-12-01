package pt.up.fe.controllers.contentarea.sources;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pt.up.fe.dates.SimpleDate;
import pt.up.fe.dtos.sources.HistoricalRecordDTO;
import pt.up.fe.facades.SourceFacade;
import pt.up.fe.sources.HistoricalRecord;

public class CreateHistoricalRecordPageController implements Initializable {

  @FXML
  private Button createHistoricalRecordButton;

  @FXML
  private Button addAuthorButton;

  @FXML
  private TextField historicalRecordNameInput;

  @FXML
  private TextField authorNameInput;

  @FXML
  private TableColumn<String, String> authorNameColumn;

  @FXML
  private TableView<String> authorsTable;

  ObservableList<String> authorsList = FXCollections.observableArrayList();

  @FXML
  public void initialize(URL url, ResourceBundle resources) {
    authorNameColumn.setCellValueFactory(cellData ->
        new ReadOnlyStringWrapper(cellData.getValue()));
    authorsTable.setItems(authorsList);
  }

  @FXML
  private void addAuthor(MouseEvent event) {
    String authorNameStr = authorNameInput.getCharacters().toString();
    if (!authorNameStr.isEmpty()) {
      authorsList.add(authorNameStr);
    }
  }

  @FXML
  private void createHistoricalRecord(MouseEvent event) {
    HistoricalRecordDTO historicalRecordDTO = new HistoricalRecordDTO();

    historicalRecordDTO.setName(historicalRecordNameInput.getCharacters().toString());
    historicalRecordDTO.setAuthors(authorsList);
    historicalRecordDTO.setDateOfPublication(new SimpleDate());

    HistoricalRecord historicalRecord = SourceFacade.createHistoricalRecord(historicalRecordDTO);
    System.out.println(historicalRecord);
  }


}
