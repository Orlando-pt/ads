package pt.up.fe.helpers.events;

import javafx.event.Event;
import javafx.event.EventType;
import pt.up.fe.person.Person;

public class PersonCustomEvent extends Event {
    public static final EventType<PersonCustomEvent> PERSON = new EventType<>(Event.ANY, "PERSON");

    private Person person;

    public PersonCustomEvent(EventType<? extends Event> eventType, Person person) {
        super(eventType);
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
}
