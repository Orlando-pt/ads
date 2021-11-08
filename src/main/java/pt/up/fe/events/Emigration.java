package pt.up.fe.events;

import org.apache.logging.log4j.LogManager;

import pt.up.fe.dates.IDate;
import pt.up.fe.places.Place;

public class Emigration extends Event {
    public Emigration(Place place, IDate date) {
        super(place, date);
        this.logger = LogManager.getLogger(Emigration.class);
        this.setName(this.getClass().getSimpleName());
    }
}
