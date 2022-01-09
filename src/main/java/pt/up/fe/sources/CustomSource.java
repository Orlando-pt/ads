package pt.up.fe.sources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class CustomSource extends Source {

  public CustomSource(String name) {
    super(name);
  }

  public CustomSource(String name, String id) {
    super(name, id);
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

  public static CustomSource importJSONObject(JSONObject obj) {
    CustomSource source = new CustomSource((String) obj.get("name"), (String) obj.get("id"));

    // TODO: Date of Publication

    JSONArray authors = obj.getJSONArray("authors");
    List<String> a = new ArrayList<>();
    for (Object auth : authors.toList()) {
      a.add((String) auth);
    }
    source.setAuthors(a);

    return source;
  }
}
