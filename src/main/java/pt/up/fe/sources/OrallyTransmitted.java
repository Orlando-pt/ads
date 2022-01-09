package pt.up.fe.sources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import pt.up.fe.places.Place;

public class OrallyTransmitted extends Source {
  private Place place;

  public OrallyTransmitted(String name) {
    super(name);
  }

  public OrallyTransmitted(String name, String id) {
    super(name, id);
  }

  public Place getPlace() {
    return place;
  }

  public void setPlace(Place place) {
    this.place = place;
  }

  @Override
  public JSONObject toJSONObject() {
    JSONObject obj = super.toJSONObject();
    obj.put("type", this.getClass().getSimpleName());
    if (this.getPlace() != null) {
      obj.put("place", this.getPlace().getId().toString());
    }
    return obj;
  }

  @Override
  public Map<String, Object> toYAMLObject() {
    Map<String, Object> obj = super.toYAMLObject();
    obj.put("type", this.getClass().getSimpleName());
    if (this.getPlace() != null) {
      obj.put("place", this.getPlace().getId());
    }

    return obj;
  }

  public static OrallyTransmitted importJSONObject(JSONObject obj) {
    OrallyTransmitted book =
        new OrallyTransmitted((String) obj.get("name"), (String) obj.get("id"));

    // TODO: Date of Publication

    JSONArray authors = obj.getJSONArray("authors");
    List<String> a = new ArrayList<>();
    for (Object auth : authors.toList()) {
      a.add((String) auth);
    }
    book.setAuthors(a);

    // TODO: Place

    return book;
  }
}
