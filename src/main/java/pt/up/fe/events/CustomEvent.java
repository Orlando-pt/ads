package pt.up.fe.events;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class CustomEvent extends Event {

  public CustomEvent(String name) {
    this.setName(name);
  }

  public CustomEvent(String name, String id) {
    super(id);
    this.setName(name);
  }

  public CustomEvent(JSONObject obj) {
    super(obj);
    this.setName((String) (obj.has("name") ? obj.get("name") : null));
  }

  public Logger initializeLogger() {
    return LogManager.getLogger(CustomEvent.class);
  }

  @Override
  public JSONObject toJSONObject() {
    JSONObject obj = super.toJSONObject();
    obj.put("type", this.getClass().getSimpleName());
    obj.put("name", this.getName());
    return obj;
  }

  @Override
  public Map<String, Object> toYAMLObject() {
    Map<String, Object> obj = super.toYAMLObject();
    obj.put("type", this.getClass().getSimpleName());
    obj.put("name", this.getName());
    return obj;
  }
}
