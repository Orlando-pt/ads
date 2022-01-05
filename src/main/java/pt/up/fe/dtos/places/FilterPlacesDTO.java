package pt.up.fe.dtos.places;

public class FilterPlacesDTO {

  private String name;

  private PlaceType type;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PlaceType getType() {
    return type;
  }

  public void setType(PlaceType type) {
    this.type = type;
  }
}
