package pt.up.fe.events;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomEvent extends Event {

  public CustomEvent(String name) {
    this.setName(name);
  }

  public Logger initializeLogger() {
    return LogManager.getLogger(CustomEvent.class);
  }
}
