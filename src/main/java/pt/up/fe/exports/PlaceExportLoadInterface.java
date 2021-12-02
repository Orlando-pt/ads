package pt.up.fe.exports;

import java.util.Map;

import pt.up.fe.places.Place;

public interface PlaceExportLoadInterface {

    Map<String, Object> export();
    Place load(Map<String, Object> place);
    
}
