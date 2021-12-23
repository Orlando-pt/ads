package pt.up.fe.places;

public class Parish extends Place {

  private Double area;

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
  public String toString() {
    return super.getName();
  }
}
