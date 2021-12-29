package pt.up.fe.controllers.contentarea.events;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.dates.IDate;
import pt.up.fe.dtos.events.EventTableDTO;
import pt.up.fe.dtos.events.FilterEventsDTO;
import pt.up.fe.events.Event;
import pt.up.fe.facades.EventFacade;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.events.PageToSendCustomEvent;
import pt.up.fe.helpers.events.SelectModeCustomEvent;
import pt.up.fe.places.Place;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListEventsPageController implements Initializable, IContentPageController {

    @FXML
    private Button selectButton;

    @FXML
    private Label selectButtonLabel;

    @FXML
    private Button selectViewButton;

    @FXML
    private Label selectViewButtonLabel;

    @FXML
    private TextField eventInput;

    @FXML
    private TextField dateInput;

    @FXML
    private TextField descriptionInput;

    @FXML
    private TableColumn<EventTableDTO, String> event;

    @FXML
    private TableColumn<EventTableDTO, Place> place;

    @FXML
    private TableColumn<EventTableDTO, IDate> date;

    @FXML
    private TableColumn<EventTableDTO, String> description;

    @FXML
    private TableView<EventTableDTO> eventsTable;

    private Boolean selectMode = false;

    private String pageToSend;

    ObservableList<EventTableDTO> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        event.setCellValueFactory(new PropertyValueFactory<>("event"));
        place.setCellValueFactory(new PropertyValueFactory<>("place"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        this.clearPage();
        eventsTable.setItems(list);
    }

    @Override
    public void setEventHandlers() {
        CustomSceneHelper.getNodeById("listEventsPage").addEventFilter(
                SelectModeCustomEvent.SELECT_MODE, new EventHandler<SelectModeCustomEvent>() {
                    @Override
                    public void handle(SelectModeCustomEvent selectModeCustomEvent) {
                        selectMode = selectModeCustomEvent.getSelectMode();
                        changeButtonLayout();
                    }
                });

        CustomSceneHelper.getNodeById("listEventsPage").addEventFilter(
                PageToSendCustomEvent.PAGE_TO_SEND, new EventHandler<PageToSendCustomEvent>() {
                    @Override
                    public void handle(PageToSendCustomEvent pageToSendCustomEvent) {
                        pageToSend = pageToSendCustomEvent.getPageToSend();
                    }
                });
    }

    @FXML
    private void search(MouseEvent event) {
        filterEvents();
    }

    @FXML
    private void selectEvent(MouseEvent event) {
        Event curEvent = eventsTable.getSelectionModel().getSelectedItem().getCurEvent();
        /* if (selectMode && pageToSend != null) {
            CustomSceneHelper.getNodeById(pageToSend)
                    .fireEvent(new PersonCustomEvent(PersonCustomEvent.PERSON, person));
            CustomSceneHelper.bringNodeToFront(pageToSend, "");
        } else {
            CustomSceneHelper.getNodeById("viewEditPersonPage")
                    .fireEvent(new PersonCustomEvent(PersonCustomEvent.PERSON, person));
            CustomSceneHelper.bringNodeToFront("viewEditPerson", "Page");
        } */

        System.out.println(curEvent);
    }

    private void filterEvents() {
        FilterEventsDTO filters = new FilterEventsDTO();

        filters.setEvent(eventInput.getCharacters().toString());
        filters.setDate(dateInput.getCharacters().toString());
        filters.setDescription(descriptionInput.getCharacters().toString());

        list.clear();
        List<Event> eventsFiltered = new EventFacade().filterEvents(filters);

        eventsFiltered.forEach(event -> {
            list.add(new EventTableDTO(event.getName(), event.getPlace(), event.getDate(), event.getDescription(), event));
        });
    }

    @Override
    public void clearPage() {
        eventInput.clear();
        dateInput.clear();
        descriptionInput.clear();
        this.filterEvents();
        this.selectMode = false;
        pageToSend = null;
        this.changeButtonLayout();
    }

    private void changeButtonLayout() {
        if (this.selectMode) {
            selectViewButton.setVisible(false);
            selectViewButtonLabel.setVisible(false);
            selectButton.setVisible(true);
            selectButtonLabel.setVisible(true);
        } else {
            selectButton.setVisible(false);
            selectButtonLabel.setVisible(false);
            selectViewButton.setVisible(true);
            selectViewButtonLabel.setVisible(true);
        }
    }
}
