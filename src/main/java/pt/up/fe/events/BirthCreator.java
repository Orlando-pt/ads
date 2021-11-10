package pt.up.fe.events;

import pt.up.fe.dates.IDate;
import pt.up.fe.places.Place;

public class BirthCreator extends EventCreator{
    @Override
    public Event createEvent() {
        return new Birth();
    }
}
