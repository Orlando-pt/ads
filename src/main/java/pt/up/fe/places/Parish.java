package pt.up.fe.places;

public class Parish extends Place {

  private Double area;

  public Parish(String name) {
    super(name);
  }

  public CompoundPlace toCompound(){
    CompoundPlace copy = new CompoundPlace(this.getName());
    copy.setSource(this.getSource());
    copy.setAltitude(this.getAltitude());
    copy.setLatitude(this.getLatitude());
    copy.setLongitude(this.getLongitude());
    copy.setDescription(this.getDescription());

    return copy;
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
