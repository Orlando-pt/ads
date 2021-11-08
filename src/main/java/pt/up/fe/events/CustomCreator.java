package pt.up.fe.events;

import pt.up.fe.dates.IDate;
import pt.up.fe.places.Place;

public class CustomCreator extends EventCreator {
    private String event;

    public CustomCreator(String event) {
        this.event = event;
    }

    @Override
    public Event createEvent(Place place, IDate date) {
        return new CustomEvent(place, date, this.event);
    }
}
