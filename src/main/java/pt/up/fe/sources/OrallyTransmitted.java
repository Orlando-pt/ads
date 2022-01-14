package pt.up.fe.sources;

import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;
import pt.up.fe.places.Place;

public class OrallyTransmitted extends Source {

  private Place place;
  private UUID auxPlace;

  public OrallyTransmitted(String name) {
    super(name);
  }

  public OrallyTransmitted(String name, String id) {
    super(name, id);
  }

  public OrallyTransmitted(JSONObject obj) {
    super(obj);
    if (obj.has("place")) {
      this.auxPlace = UUID.fromString(obj.getString("place"));
    }
  }

  public Place getPlace() {
    return place;
  }

  public void setPlace(Place place) {
    this.place = place;
  }

  public UUID getAuxPlace() {
    return this.auxPlace;
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
}
