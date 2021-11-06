package pt.up.fe.events;

import pt.up.fe.date.IDate;
import pt.up.fe.places.Place;

public class Marriage extends Event {
    public Marriage(Place place, IDate date) {
        super(place, date);
        this.setName(this.getClass().getSimpleName());
    }
}
