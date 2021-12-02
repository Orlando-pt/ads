package pt.up.fe.places;

import pt.up.fe.BaseClass;
import pt.up.fe.exports.PlaceExportLoadInterface;

public abstract class Place extends BaseClass implements PlaceExportLoadInterface{

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
}
