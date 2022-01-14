package pt.up.fe.imports;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import pt.up.fe.places.Place;

public class PlaceJsonStrategy extends JsonStrategy<Place> {
  @Override
  public List<Place> doImport(List<JSONObject> data) {
    List<Place> list = new ArrayList<>();
    for (JSONObject obj : data) {
      list.add(Place.importJSONObject(obj));
    }
    return list;
  }
}
