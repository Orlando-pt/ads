package pt.up.fe.events;

import org.apache.logging.log4j.LogManager;

import pt.up.fe.dates.IDate;
import pt.up.fe.places.Place;

public class Marriage extends Event {
    public Marriage(Place place, IDate date) {
        super(place, date);

        this.logger = LogManager.getLogger(Marriage.class);
        this.setName(this.getClass().getSimpleName());
    }
}
