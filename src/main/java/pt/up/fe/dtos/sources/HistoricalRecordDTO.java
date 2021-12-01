package pt.up.fe.dtos.sources;

import pt.up.fe.places.Place;

public class HistoricalRecordDTO extends SourceDTO {

  private Place nationalArchiveCountry;

  public Place getNationalArchiveCountry() {
    return nationalArchiveCountry;
  }

  public void setNationalArchiveCountry(Place nationalArchiveCountry) {
    this.nationalArchiveCountry = nationalArchiveCountry;
  }
}
