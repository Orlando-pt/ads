package pt.up.fe.events;

import pt.up.fe.dates.IDate;
import pt.up.fe.places.Place;

public class Emigration extends Event {
    public Emigration() {
        this.setName(this.getClass().getSimpleName());
    }
}
