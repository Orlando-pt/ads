package pt.up.fe.helpers.events;

import javafx.event.Event;
import javafx.event.EventType;

public class PageToSendCustomEvent extends Event {

  public static final EventType<PageToSendCustomEvent> PAGE_TO_SEND = new EventType<>(Event.ANY,
      "PAGE_TO_SEND");

  private String pageToSend;

  public PageToSendCustomEvent(EventType<? extends Event> eventType, String pageToSend) {
    super(eventType);
    this.pageToSend = pageToSend;
  }

  public String getPageToSend() {
    return pageToSend;
  }


}