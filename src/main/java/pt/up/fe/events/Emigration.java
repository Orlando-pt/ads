package pt.up.fe.events;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class Emigration extends Event {
  public Emigration() {
    this.setName(this.getClass().getSimpleName());
  }
  public Emigration(String id) {
    super(id);
    this.setName(this.getClass().getSimpleName());
  }

  public Emigration(JSONObject obj) {
    super(obj);
    this.setName(this.getClass().getSimpleName());
  }

  public Logger initializeLogger() {
    return LogManager.getLogger(Emigration.class);
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
