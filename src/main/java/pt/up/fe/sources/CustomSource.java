package pt.up.fe.sources;

import org.json.JSONObject;
import pt.up.fe.Source;

public class CustomSource extends Source {

  public CustomSource(String name) {
    super(name);
  }

  @Override
  public JSONObject toJSONObject() {
    JSONObject obj = super.toJSONObject();
    obj.put("type", this.getClass().getSimpleName());
    return obj;
  }
    }
}
