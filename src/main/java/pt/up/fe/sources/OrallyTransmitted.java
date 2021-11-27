package pt.up.fe.sources;

import pt.up.fe.places.Place;

public class OrallyTransmitted extends Source {

  private Place place;

  public OrallyTransmitted(String name) {
    super(name);
  }

  public Place getPlace() {
    return place;
  }

  public void setPlace(Place place) {
    this.place = place;
  }
}
