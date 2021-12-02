package pt.up.fe.places;

import java.util.Map;
import org.json.JSONObject;
import pt.up.fe.BaseClass;

public abstract class Place extends BaseClass {
  private Double latitude;
  private Double longitude;
  private Double altitude;

  public Place(String name) {
    this.setName(name);
  }

  public abstract Boolean isComposite();

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Double getAltitude() {
    return altitude;
  }

  public void setAltitude(Double altitude) {
    this.altitude = altitude;
  }

  public abstract Double getArea();

  @Override
  public abstract String toString();

  @Override
  public JSONObject toJSONObject() {
    JSONObject obj = super.toJSONObject();
    obj.put("latitude", this.getLatitude());
    obj.put("longitude", this.getLongitude());
    obj.put("altitude", this.getLatitude());
    obj.put("area", this.getArea());
    obj.put("isComposite", this.isComposite());
    return obj;
  }

  @Override
  public Map<String, Object> toYAMLObject() {
    Map<String, Object> obj = super.toYAMLObject();
    obj.put("latitude", this.getLatitude());
    obj.put("longitude", this.getLongitude());
    obj.put("altitude", this.getLatitude());
    obj.put("area", this.getArea());
    obj.put("isComposite", this.isComposite());
    return obj;
  }
}
