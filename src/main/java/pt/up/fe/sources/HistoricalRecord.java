package pt.up.fe.sources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import pt.up.fe.places.Place;

public class HistoricalRecord extends Source {
  private Place nationalArchiveCountry;

  public HistoricalRecord(String name) {
    super(name);
  }

  public HistoricalRecord(String name, String id) {
    super(name, id);
  }

  public Place getNationalArchiveCountry() {
    return nationalArchiveCountry;
  }

  public void setNationalArchiveCountry(Place nationalArchiveCountry) {
    this.nationalArchiveCountry = nationalArchiveCountry;
  }

  @Override
  public JSONObject toJSONObject() {
    JSONObject obj = super.toJSONObject();
    obj.put("type", this.getClass().getSimpleName());
    if (this.getNationalArchiveCountry() != null) {
      obj.put("nationalArchiveCountry", this.getNationalArchiveCountry().getId().toString());
    }
    return obj;
  }

  @Override
  public Map<String, Object> toYAMLObject() {
    Map<String, Object> obj = super.toYAMLObject();
    obj.put("type", this.getClass().getSimpleName());
    if (this.getNationalArchiveCountry() != null) {
      obj.put("nationalArchiveCountry", this.getNationalArchiveCountry().getId().toString());
    }

    return obj;
  }

  public static HistoricalRecord importJSONObject(JSONObject obj) {
    HistoricalRecord book = new HistoricalRecord((String) obj.get("name"), (String) obj.get("id"));

    // TODO: Date of Publication

    JSONArray authors = obj.getJSONArray("authors");
    List<String> a = new ArrayList<>();
    for (Object auth : authors.toList()) {
      a.add((String) auth);
    }
    book.setAuthors(a);

    // TODO: Place


    return book;
  }
}
