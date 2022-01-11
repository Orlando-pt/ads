package pt.up.fe.helpers.events;

import javafx.event.Event;
import javafx.event.EventType;
import pt.up.fe.places.Place;

public class PlaceCustomEvent extends Event {

  public static final EventType<PlaceCustomEvent> PLACE = new EventType<>(Event.ANY, "PLACE");

  private Place place;

  public PlaceCustomEvent(EventType<? extends Event> eventType, Place place) {
    super(eventType);
    this.place = place;
  }

  public Place getPlace() {
    return place;
  }
}
