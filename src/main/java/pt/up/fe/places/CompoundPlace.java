package pt.up.fe.places;

import java.util.ArrayList;
import java.util.List;

public class CompoundPlace extends Place {

  private final List<Place> children = new ArrayList<>();

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
