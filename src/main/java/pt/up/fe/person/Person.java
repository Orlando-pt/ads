package pt.up.fe.person;

import pt.up.fe.BaseClass;
import pt.up.fe.events.Event;

import java.util.List;

public class Person extends BaseClass {
    private Genre genre;
    private List<Event> events;

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

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
