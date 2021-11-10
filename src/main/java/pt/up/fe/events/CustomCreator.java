package pt.up.fe.events;

import pt.up.fe.dates.IDate;
import pt.up.fe.places.Place;

public class CustomCreator extends EventCreator {
    private String event;

    public CustomCreator(String event) {
        this.event = event;
    }

    @Override
    public Event createEvent() {
        return new CustomEvent(this.event);
    }
}
