package pt.up.fe.helpers.events;

import javafx.event.Event;
import javafx.event.EventType;

public class TypeSelectionDateCustomEvent extends Event {

  public static final EventType<TypeSelectionDateCustomEvent> INTERVALSELECTION = new EventType<>(Event.ANY,
      "INTERVALSELECTION");

  private boolean intervalSelection;

  public TypeSelectionDateCustomEvent(EventType<? extends Event> eventType,
      boolean intervalSelection) {
    super(eventType);
    this.intervalSelection = intervalSelection;
  }

  public boolean getIsIntervalSelection() {
    return intervalSelection;
  }
}
