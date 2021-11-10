package pt.up.fe.events;

import pt.up.fe.dates.IDate;
import pt.up.fe.places.Place;

public class Death extends Event {
    public Death() {
        this.setName(this.getClass().getSimpleName());
    }
}
