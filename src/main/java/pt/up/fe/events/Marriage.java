package pt.up.fe.events;

import pt.up.fe.dates.IDate;
import pt.up.fe.places.Place;

public class Marriage extends Event {
    public Marriage() {
        this.setName(this.getClass().getSimpleName());
    }
}
