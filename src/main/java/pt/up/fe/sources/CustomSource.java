package pt.up.fe.sources;

import java.util.Map;
import org.json.JSONObject;

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

  @Override
  public Map<String, Object> toYAMLObject() {
    Map<String, Object> obj = super.toYAMLObject();
    obj.put("id", this.getId().toString());
    obj.put("name", this.getName());
    if (this.getDateOfPublication() != null) {
      obj.put("dateOfPublication", this.getDateOfPublication().toYAMLObject());
    }
    obj.put("authors", this.getAuthors());

    return obj;
  }
}
