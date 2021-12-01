package pt.up.fe.dtos.sources;

import pt.up.fe.places.Place;

public class OrallyTransmittedDTO extends SourceDTO{

  private Place place;

  public Place getPlace() {
    return place;
  }

  public void setPlace(Place place) {
    this.place = place;
  }
}
