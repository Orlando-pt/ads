package pt.up.fe.events;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class CustomEvent extends Event {

  public CustomEvent(String name) {
    this.setName(name);
  }

  public Logger initializeLogger() {
    return LogManager.getLogger(CustomEvent.class);
  }

  @Override
  public JSONObject toJSONObject() {
    JSONObject obj = super.toJSONObject();
    obj.put("type", this.getClass().getSimpleName());
    return obj;
  }
}
