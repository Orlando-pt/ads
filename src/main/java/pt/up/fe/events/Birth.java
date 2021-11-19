package pt.up.fe.events;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Birth extends Event {

  public Birth() {
    this.setName(this.getClass().getSimpleName());
  }

  public Logger initializeLogger() {
    return LogManager.getLogger(Birth.class);
  }
}
