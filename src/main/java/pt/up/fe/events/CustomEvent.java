package pt.up.fe.events;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pt.up.fe.dates.IDate;
import pt.up.fe.places.Place;

public class CustomEvent extends Event {

    public CustomEvent(Place place, IDate date, String name) {
        super(place, date);

        // this.logger = LogManager.getLogger(CustomEvent.class);
        this.setName(name);
    }

    public Logger initializeLogger() {
        return LogManager.getLogger(CustomEvent.class);
    }
}
