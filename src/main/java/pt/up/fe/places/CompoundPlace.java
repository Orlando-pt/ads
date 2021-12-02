package pt.up.fe.places;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CompoundPlace extends Place {

  private final List<Place> children = new ArrayList<>();

  public CompoundPlace() {
    super("");
  }

  public CompoundPlace(String name) {
    super(name);
  }

  @Override
  public Boolean isComposite() {
    return true;
  }

  public List<Place> getChildren() {
    return children;
  }

  public void addChild(Place child) {
    this.children.add(child);
  }

  public void removeChild(Place child) {
    this.children.remove(child);
  }
  
  @Override
  public Map<String, Object> export() {
    Map<String, Object> retMap = new HashMap<>();

    retMap.put("Name", getName());
    retMap.put(
      "Children",
      this.children.stream().map(
        Place::export
      ).collect(Collectors.toList())
    );
    return retMap;
  }

  @Override
  public Place load(Map<String, Object> place) {
    
    // if we have childrens, it means we are a compound
    // we could also work with isComposite
    if (place.containsKey("Children")) {
      List<Map<String, Object>> children = (List<Map<String, Object>>) place.get("Children");

      for (Map<String, Object> child : children)
        this.addChild(
          new CompoundPlace().load(child)
        );

    } else {
      // else it means we are a parish
      return new Parish().load(place);
    }

    this.setName((String) place.get("Name"));
    return this;
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
}
