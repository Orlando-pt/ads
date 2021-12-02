package pt.up.fe.events;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class Birth extends Event {
  public Birth() {
    this.setName(this.getClass().getSimpleName());
  }

  public Logger initializeLogger() {
    return LogManager.getLogger(Birth.class);
  }

  @Override
  public JSONObject toJSONObject() {
    JSONObject obj = super.toJSONObject();
    obj.put("type", this.getClass().getSimpleName());
    return obj;
  }

  @Override
  public Map<String, Object> toYAMLObject() {
    Map<String, Object> obj = super.toYAMLObject();
    obj.put("type", this.getClass().getSimpleName());
    return obj;
  }
}
