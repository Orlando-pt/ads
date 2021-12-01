package pt.up.fe.helpers;

import pt.up.fe.events.Event;
import pt.up.fe.person.Person;

public final class SingletonHolder {

    private Person person;
    private Event event;

    private final static SingletonHolder INSTANCE = new SingletonHolder();

    private SingletonHolder() {}

    public static SingletonHolder getInstance() {
        return INSTANCE;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
