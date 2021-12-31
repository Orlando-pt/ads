package pt.up.fe.helpers.events;

import javafx.event.Event;
import javafx.event.EventType;
import pt.up.fe.dtos.places.PlaceType;

public class PlaceFilterModeCustomEvent extends Event {

  public static final EventType<PlaceFilterModeCustomEvent> FILTER_MODE = new EventType<>(Event.ANY,
      "FILTER_MODE");

  private PlaceType filterMode;

  public PlaceFilterModeCustomEvent(EventType<? extends Event> eventType, PlaceType filterMode) {
    super(eventType);
    this.filterMode = filterMode;
  }

  public PlaceType getFilterMode() {
    return filterMode;
  }
}