package pt.up.fe.helpers.events;

import javafx.event.Event;
import javafx.event.EventType;

public class SelectModeCustomEvent extends Event {

  public static final EventType<SelectModeCustomEvent> SELECT_MODE = new EventType<>(Event.ANY,
      "SELECT_MODE");

  private Boolean selectMode;

  public SelectModeCustomEvent(EventType<? extends Event> eventType, Boolean selectMode) {
    super(eventType);
    this.selectMode = selectMode;
  }

  public Boolean getSelectMode() {
    return selectMode;
  }
}