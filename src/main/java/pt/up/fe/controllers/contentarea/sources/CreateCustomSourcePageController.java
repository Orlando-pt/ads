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
import pt.up.fe.dtos.sources.CustomSourceDTO;
import pt.up.fe.facades.SourceFacade;
import pt.up.fe.sources.CustomSource;

public class CreateCustomSourcePageController implements Initializable {

  @FXML
  private Button createCustomSourceButton;

  @FXML
  private Button addAuthorButton;

  @FXML
  private TextField customSourceNameInput;

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
  private void createCustomSource(MouseEvent event) {
    CustomSourceDTO customSourceDTO = new CustomSourceDTO();

    customSourceDTO.setName(customSourceNameInput.getCharacters().toString());
    customSourceDTO.setAuthors(authorsList);
    customSourceDTO.setDateOfPublication(new SimpleDate());

    CustomSource customSource = SourceFacade.createCustomSource(customSourceDTO);
    System.out.println(customSource);
  }


}
