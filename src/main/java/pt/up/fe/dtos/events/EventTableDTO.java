package pt.up.fe.dtos.events;
import pt.up.fe.dates.IDate;
import pt.up.fe.events.Event;
import pt.up.fe.places.Place;

public class EventTableDTO {
    private String event;

    private Place place;

    private IDate date;

    private String description;

    private Event curEvent;

    public EventTableDTO(String event, Place place, IDate date, String description, Event selectedEvent) {
        this.event = event;
        this.place = place;
        this.date = date;
        this.description = description;
        this.curEvent = selectedEvent;
    }

    public Event getCurEvent() {
        return curEvent;
    }

    public void setCurEvent(Event curEvent) {
        this.curEvent = curEvent;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public IDate getDate() {
        return date;
    }

    public void setDate(IDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
