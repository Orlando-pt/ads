package pt.up.fe.events;

import pt.up.fe.date.IDate;
import pt.up.fe.places.Place;

public class Emigration extends Event {
    public Emigration(Place place, IDate date) {
        super(place, date);
        this.setName(this.getClass().getSimpleName());
    }
}
