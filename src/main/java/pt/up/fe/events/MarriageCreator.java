package pt.up.fe.events;

import pt.up.fe.dates.IDate;
import pt.up.fe.places.Place;

public class MarriageCreator extends EventCreator{
    @Override
    public Event createEvent(Place place, IDate date) {
        return new Marriage(place, date);
    }
}
