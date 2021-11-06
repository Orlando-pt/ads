package pt.up.fe.events;

import pt.up.fe.date.IDate;
import pt.up.fe.places.Place;

public class Birth extends Event {
    public Birth(Place place, IDate date) {
        super(place, date);
        this.setName(this.getClass().getSimpleName());
    }
}
