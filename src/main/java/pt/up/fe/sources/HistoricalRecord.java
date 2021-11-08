package pt.up.fe.sources;

import pt.up.fe.dates.IDate;
import pt.up.fe.places.Place;

import java.util.List;

public class HistoricalRecord extends Source{
    private Place nationalArchiveCountry;

    public HistoricalRecord(String name, Place nationalArchiveCountry) {
        super(name);
        this.nationalArchiveCountry = nationalArchiveCountry;
    }

    public Place getNationalArchiveCountry() {
        return nationalArchiveCountry;
    }

    public void setNationalArchiveCountry(Place nationalArchiveCountry) {
        this.nationalArchiveCountry = nationalArchiveCountry;
    }
}
