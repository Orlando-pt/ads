package pt.up.fe.places;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

public class CompoundPlace extends Place {

  private final List<Place> children = new ArrayList<>();
  private final List<UUID> auxChildren = new ArrayList<>();

  public CompoundPlace(String name) {
    super(name);
  }

  public CompoundPlace(JSONObject obj) {
    super(obj);

    if (obj.has("children")) {
      for (Object o : obj.getJSONArray("children")) {
        String id = (String) o;
        this.auxChildren.add(UUID.fromString(id));
      }
    }
  }

  @Override
  public Boolean isComposite() {
    return true;
  }

  public List<Place> getChildren() {
    return children;
  }

  public List<UUID> getAuxChildren() {
    return this.auxChildren;
  }

  public void addChild(Place child) {
    this.children.add(child);
  }

  public void removeChild(Place child) {
    this.children.remove(child);
  }

  @Override
  public Double getArea() {
    Double area = 0d;
    for (Place place : this.children) {
      area += place.getArea();
    }
    return area;
  }

  @Override
  public String toString() {
    ArrayList<String> arr = new ArrayList<>();
    StringBuilder sBuilder = new StringBuilder();
    sBuilder.append(super.getName() + "(");

    for (Place place : this.children) {
      arr.add(place.toString());
    }
    String joined = String.join("+", arr);

    sBuilder.append(joined);
    sBuilder.append(")");

    return sBuilder.toString();
  }

  @Override
  public JSONObject toJSONObject() {
    JSONObject obj = super.toJSONObject();
    JSONArray children = new JSONArray();
    for (Place child : this.children) {
      children.put(child.getId().toString());
    }

    obj.put("children", children);
    return obj;
  }

  @Override
  public Map<String, Object> toYAMLObject() {
    Map<String, Object> obj = super.toYAMLObject();
    List<String> children = new ArrayList<>();
    for (Place child : this.children) {
      children.add(child.getId().toString());
    }

    obj.put("children", children);
    return obj;
  }
}
