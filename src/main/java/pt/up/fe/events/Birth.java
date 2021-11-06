package pt.up.fe.events;

import pt.up.fe.dates.IDate;
import pt.up.fe.places.Place;

public class Birth extends Event {
    public Birth(Place place, IDate date) {
        super(place, date);
        this.setName(this.getClass().getSimpleName());
    }
}
