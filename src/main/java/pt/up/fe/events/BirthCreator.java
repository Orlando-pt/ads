package pt.up.fe.events;

import pt.up.fe.dates.IDate;
import pt.up.fe.places.Place;

public class BirthCreator extends EventCreator{
    @Override
    public Event createEvent(Place place, IDate date) {
        return new Birth(place, date);
    }
}
