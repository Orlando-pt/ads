package pt.up.fe.person;

import pt.up.fe.BaseClass;
import pt.up.fe.events.Event;

import java.util.List;

public class Person extends BaseClass {
    private Gender gender;
    private List<Event> events;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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
