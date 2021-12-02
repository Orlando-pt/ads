package pt.up.fe.places;

import java.util.HashMap;
import java.util.Map;

public class Parish extends Place {

  private Double area = 0d;

  public Parish() {
    super("");
  }

  public Parish(String name) {
    super(name);
  }

  @Override
  public Boolean isComposite() {
    return false;
  }

  @Override
  public Double getArea() {
    return this.area;
  }

  public void setArea(Double area) {
    this.area = area;
  }

  @Override
  public Map<String, Object> export() {
    Map<String, Object> retMap = new HashMap<>();
    retMap.put("Name", getName());
    retMap.put("Area", this.area);
    return retMap;
  }

  @Override
  public Place load(Map<String, Object> place) {
    this.setName((String) place.get("Name"));
    this.setArea((Double) place.get("Area"));
    return this;
  }

  @Override
  public String toString() {
    return super.getName();
  }
}
