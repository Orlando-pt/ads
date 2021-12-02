package pt.up.fe.helpers.events;

import javafx.event.Event;
import javafx.event.EventType;
import pt.up.fe.sources.Source;

public class SourceCustomEvent extends Event {

  public static final EventType<SourceCustomEvent> SOURCE = new EventType<>(Event.ANY, "SOURCE");

  private Source source;

  public SourceCustomEvent(EventType<? extends Event> eventType, Source source) {
    super(eventType);
    this.source = source;
  }

  public Source getSource() {
    return source;
  }
}