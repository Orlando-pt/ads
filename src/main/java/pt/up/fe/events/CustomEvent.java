package pt.up.fe.events;

import pt.up.fe.date.IDate;
import pt.up.fe.places.Place;

import java.util.Map;

public class CustomEvent extends Event {

    public CustomEvent(Place place, IDate date, String name) {
        super(place, date);
        this.setName(name);
    }
}
