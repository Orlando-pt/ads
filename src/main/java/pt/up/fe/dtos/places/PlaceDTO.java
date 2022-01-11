package pt.up.fe.dtos.places;

import pt.up.fe.sources.Source;

public class PlaceDTO {

  private String name;
  private PlaceType type;
  private String description;
  private Double latitude;
  private Double longitude;
  private Double altitude;
  private Double area;
  private Source source;

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

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

  public Double getArea() {
    return area;
  }

  public void setArea(Double area) {
    this.area = area;
  }

  public Source getSource() {
    return source;
  }

  public void setSource(Source source) {
    this.source = source;
  }
}
