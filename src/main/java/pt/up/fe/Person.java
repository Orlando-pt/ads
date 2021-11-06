package pt.up.fe;

import pt.up.fe.events.Event;

import java.util.List;

public class Person extends BaseClass {
    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public void removeEvent(Event event) {
        this.events.remove(event);
    }
}
