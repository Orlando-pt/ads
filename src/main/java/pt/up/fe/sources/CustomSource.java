package pt.up.fe.sources;

import java.util.Map;
import org.json.JSONObject;

public class CustomSource extends Source {

  public CustomSource(String name) {
    super(name);
  }

  public CustomSource(String name, String id) {
    super(name, id);
  }

  public CustomSource(JSONObject obj) {
    super(obj);
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
