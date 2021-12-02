package pt.up.fe.helpers.events;

import javafx.event.Event;
import javafx.event.EventType;
import pt.up.fe.dates.IDate;

public class DateCustomEvent extends Event {
    public static final EventType<DateCustomEvent> DATE = new EventType<>(Event.ANY, "DATE");

    private IDate date;

    public DateCustomEvent(EventType<? extends Event> eventType, IDate date) {
        super(eventType);
        this.date = date;
    }

    public IDate getDate() {
        return date;
    }
}
