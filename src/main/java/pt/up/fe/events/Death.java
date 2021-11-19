package pt.up.fe.events;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Death extends Event {

  public Death() {
    this.setName(this.getClass().getSimpleName());
  }

  public Logger initializeLogger() {
    return LogManager.getLogger(Death.class);
  }
}
