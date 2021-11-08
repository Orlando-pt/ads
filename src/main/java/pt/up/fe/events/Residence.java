package pt.up.fe.events;

import org.apache.logging.log4j.LogManager;

import pt.up.fe.dates.IDate;
import pt.up.fe.places.Place;

public class Residence extends Event {
    public Residence(Place place, IDate date) {
        super(place, date);

        this.logger = LogManager.getLogger(Residence.class);
        this.setName(this.getClass().getSimpleName());
    }
}
