package pt.up.fe.dtos.places;

import pt.up.fe.places.Place;

public class PlaceTableDTO {

  private String name;

  private PlaceType type;

  private Double latitude;

  private Double longitude;

  private Double altitude;

  private Place place;

  public PlaceTableDTO(String name, PlaceType type, Double latitude, Double longitude,
      Double altitude, Place place) {
    this.name = name;
    this.type = type;
    this.latitude = latitude;
    this.longitude = longitude;
    this.altitude = altitude;
    this.place = place;
  }

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

  public Place getPlace() {
    return place;
  }

  public void setPlace(Place place) {
    this.place = place;
  }
}
