package pt.up.fe.events;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pt.up.fe.dates.IDate;
import pt.up.fe.places.Place;

public class Birth extends Event {
    public Birth(Place place, IDate date) {
        super(place, date);

        // this.logger = LogManager.getLogger(Birth.class);
        this.setName(this.getClass().getSimpleName());
    }

    public Logger initializeLogger() {
        return LogManager.getLogger(Birth.class);
    }
}
