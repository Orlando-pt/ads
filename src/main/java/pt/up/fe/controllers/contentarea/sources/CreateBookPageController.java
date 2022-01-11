package pt.up.fe.controllers.contentarea.sources;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.dates.IDate;
import pt.up.fe.dtos.sources.BookDTO;
import pt.up.fe.facades.SourceFacade;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.NumberTextField;
import pt.up.fe.helpers.events.DateCustomEvent;
import pt.up.fe.helpers.events.PageToSendCustomEvent;
import pt.up.fe.helpers.events.SourceCustomEvent;
import pt.up.fe.sources.Book;


public class CreateBookPageController implements Initializable, IContentPageController {

  @FXML
  private Button createBookButton;

  @FXML
  private Button addAuthorButton;

  @FXML
  private Button addDateButton;

  @FXML
  private TextField publisherNameInput;

  @FXML
  private TextField bookNameInput;

  @FXML
  private TextField authorNameInput;

  @FXML
  private NumberTextField pagesInput;

  @FXML
  private TableColumn<String, String> authorNameColumn;

  @FXML
  private TableView<String> authorsTable;

  private String pageToSend;

  @FXML
  private TextField bookDate;

  private IDate date;

  private Book selectedBook;

  private boolean editMode = true;

  ObservableList<String> authorsList = FXCollections.observableArrayList();

  @FXML
  public void initialize(URL url, ResourceBundle resources) {
    authorNameColumn.setCellValueFactory(cellData ->
        new ReadOnlyStringWrapper(cellData.getValue()));
    authorsTable.setItems(authorsList);
  }

  @Override
  public void setEventHandlers() {
    CustomSceneHelper.getNodeById("createBookPage").addEventFilter(
        SourceCustomEvent.SOURCE, new EventHandler<SourceCustomEvent>() {
          @Override
          public void handle(SourceCustomEvent sourceCustomEvent) {
            selectedBook = (Book) sourceCustomEvent.getSource();
            setInfo();
          }
        });

    CustomSceneHelper.getNodeById("createSourcePage").addEventFilter(
        PageToSendCustomEvent.PAGE_TO_SEND, new EventHandler<PageToSendCustomEvent>() {
          @Override
          public void handle(PageToSendCustomEvent pageToSendCustomEvent) {
            pageToSend = pageToSendCustomEvent.getPageToSend();
          }
        });
  }

  @FXML
  public void openDateBuilder(ActionEvent event) {
    CustomSceneHelper.bringNodeToFront("CreateDate", "Page");

    CustomSceneHelper.getNodeById("createDatePage")
        .addEventFilter(DateCustomEvent.DATE, new EventHandler<DateCustomEvent>() {
          @Override
          public void handle(DateCustomEvent dateCustomEvent) {
            date = dateCustomEvent.getDate();
            bookDate.setText(date.toString());

            CustomSceneHelper.getNodeById("createDatePage")
                .removeEventFilter(DateCustomEvent.DATE, this); // Remove event handler
            CustomSceneHelper.bringNodeToFront("createBook", "Page");
          }
        });
  }

  @Override
  public void clearPage() {
    publisherNameInput.clear();
    bookNameInput.clear();
    authorNameInput.clear();
    pagesInput.clear();
    authorsList.clear();
    selectedBook = null;
    date = null;
    pageToSend = null;
    createBookButton.setText("Create Book");
    editMode = true;
    changePageMode();
  }

  public void setInfo() {
    publisherNameInput.setText(selectedBook.getPublisher());
    bookNameInput.setText(selectedBook.getName());
    if (selectedBook.getPages() != null) {
      pagesInput.setText(selectedBook.getPages().toString());
    }
    if (selectedBook.getDateOfPublication() != null) {
      date = selectedBook.getDateOfPublication();
      bookDate.setText(selectedBook.getDateOfPublication().toString());
    }
    authorsList.addAll(selectedBook.getAuthors());
    createBookButton.setText("Save Book");
    changePageMode();
  }

  private void changePageMode() {
    if (editMode) {
      publisherNameInput.setEditable(true);
      bookNameInput.setEditable(true);
      pagesInput.setEditable(true);
      addAuthorButton.setVisible(true);
      authorNameInput.setVisible(true);
      addDateButton.setVisible(true);
    } else {
      publisherNameInput.setEditable(false);
      bookNameInput.setEditable(false);
      pagesInput.setEditable(false);
      addAuthorButton.setVisible(false);
      authorNameInput.setVisible(false);
      addDateButton.setVisible(false);
    }
  }

  @FXML
  private void addAuthor(MouseEvent event) {
    String authorNameStr = authorNameInput.getCharacters().toString();
    if (!authorNameStr.isEmpty()) {
      authorsList.add(authorNameStr);
    }
  }

  @FXML
  private void createBook(MouseEvent event) throws IllegalAccessException {
    BookDTO bookDTO = new BookDTO();

    bookDTO.setName(bookNameInput.getCharacters().toString());
    String pagesNumber = pagesInput.getCharacters().toString();
    if (!pagesNumber.isEmpty()) {
      bookDTO.setPages(Integer.valueOf(pagesNumber));
    }
    bookDTO.setAuthors(authorsList);
    bookDTO.setPublisher(publisherNameInput.getCharacters().toString());
    bookDTO.setDateOfPublication(date);

    Book book;

    if (selectedBook == null) {
      book = SourceFacade.createBook(bookDTO);
    } else {
      book = SourceFacade.editBook(selectedBook, bookDTO);
    }

    if (pageToSend != null) {
      CustomSceneHelper.getNodeById(pageToSend)
          .fireEvent(new SourceCustomEvent(SourceCustomEvent.SOURCE, book));
      CustomSceneHelper.bringNodeToFront(pageToSend, "");
    } else {
      CustomSceneHelper.contentAreaPaneController.cleanAll();
      CustomSceneHelper.bringNodeToFront("listSources", "Page");
    }

    this.clearPage();
  }


}
