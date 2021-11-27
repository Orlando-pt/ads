package pt.up.fe.sources;

import pt.up.fe.places.Place;

public class HistoricalRecord extends Source {

  private Place nationalArchiveCountry;

  public HistoricalRecord(String name) {
    super(name);
  }

  public Place getNationalArchiveCountry() {
    return nationalArchiveCountry;
  }

  public void setNationalArchiveCountry(Place nationalArchiveCountry) {
    this.nationalArchiveCountry = nationalArchiveCountry;
  }
}
