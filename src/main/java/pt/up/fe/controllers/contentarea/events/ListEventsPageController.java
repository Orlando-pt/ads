package pt.up.fe.controllers.contentarea.events;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pt.up.fe.Main;
import pt.up.fe.controllers.contentarea.IContentPageController;
import pt.up.fe.dates.IDate;
import pt.up.fe.dtos.events.EventTableDTO;
import pt.up.fe.dtos.events.FilterEventsDTO;
import pt.up.fe.events.Event;
import pt.up.fe.facades.EventFacade;
import pt.up.fe.helpers.AutoCompleteTextField;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.events.EventCustomEvent;
import pt.up.fe.places.Place;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ListEventsPageController implements Initializable, IContentPageController {

    ObservableList<EventTableDTO> list = FXCollections.observableArrayList();
    @FXML
    private Button selectButton;
    @FXML
    private Label selectButtonLabel;
    @FXML
    private Button selectViewButton;
    @FXML
    private Label selectViewButtonLabel;
    @FXML
    private AutoCompleteTextField eventInput;
    @FXML
    private AutoCompleteTextField dateInput;
    @FXML
    private AutoCompleteTextField descriptionInput;
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

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        Main.eventsList.forEach(event -> {
            if (event.getName() != null) {
                eventInput.getEntries().add(event.getName());
            }
            if (event.getDate() != null) {
                dateInput.getEntries().add(event.getDate().toString());
            }
            if (event.getDescription() != null) {
                descriptionInput.getEntries().add(event.getDescription());
            }
        });

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
                EventCustomEvent.EVENT, new EventHandler<EventCustomEvent>() {
                    @Override
                    public void handle(EventCustomEvent eventCustomEvent) {
                        filterEvents();
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

        String pageToSend = "customEvent";

        if (Arrays.asList("Birth", "Death", "Emigration", "Marriage", "Residence").contains(curEvent.getName())) {
            pageToSend = curEvent.getName().toLowerCase() + "Event";
        }

        CustomSceneHelper.getNodeById(pageToSend + "Page").fireEvent(new EventCustomEvent(EventCustomEvent.EVENT, curEvent));
        CustomSceneHelper.bringNodeToFront(pageToSend, "Page");
    }

    private void filterEvents() {
        FilterEventsDTO filters = new FilterEventsDTO();

        filters.setEvent(eventInput.getCharacters().toString());
        filters.setDate(dateInput.getCharacters().toString());
        filters.setDescription(descriptionInput.getCharacters().toString());

        list.clear();
        List<Event> eventsFiltered = EventFacade.filterEvents(filters);

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
