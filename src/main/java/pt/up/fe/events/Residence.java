package pt.up.fe.events;

import pt.up.fe.date.IDate;
import pt.up.fe.places.Place;

public class Residence extends Event {
    public Residence(Place place, IDate date) {
        super(place, date);
        this.setName(this.getClass().getSimpleName());
    }
}
