package pt.up.fe.helpers.events;

import javafx.event.Event;
import javafx.event.EventType;

public class EventCustomEvent extends Event {

    public static final EventType<EventCustomEvent> EVENT = new EventType<>(Event.ANY, "EVENT");

    private pt.up.fe.events.Event event;

    public EventCustomEvent(EventType<? extends Event> eventType, pt.up.fe.events.Event event) {
        super(eventType);
        this.event = event;
    }

    public pt.up.fe.events.Event getEvent() {
        return event;
    }
}
