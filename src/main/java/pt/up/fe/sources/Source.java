package pt.up.fe.sources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;
import pt.up.fe.dates.IDate;
import pt.up.fe.dates.IntervalDate;
import pt.up.fe.dates.SimpleDate;
import pt.up.fe.exports.IExportObject;

public abstract class Source implements IExportObject {
  private final UUID id;
  private IDate dateOfPublication;
  private String name;
  private List<String> authors = new ArrayList<>();

  public Source(String name) {
    this.id = UUID.randomUUID();
    this.name = name;
  }

  public Source(String name, String id) {
    this.id = UUID.fromString(id);
    this.name = name;
  }

  public Source(JSONObject obj) {
    this.id = UUID.fromString((String) obj.get("id"));
    this.name = (String) obj.get("name");

    JSONArray authors = obj.getJSONArray("authors");
    List<String> a = new ArrayList<>();
    for (Object auth : authors.toList()) {
      a.add((String) auth);
    }
    this.authors = a;

    if (obj.has("dateOfPublication")) {
      JSONObject date = obj.getJSONObject("dateOfPublication");
      if (date.has("startDate") || date.has("endDate")) {
        this.dateOfPublication = new IntervalDate(date);
      } else {
        this.dateOfPublication = new SimpleDate(obj);
      }
    }
  }

  public UUID getId() {
    return this.id;
  }

  public IDate getDateOfPublication() {
    return dateOfPublication;
  }

  public void setDateOfPublication(IDate dateOfPublication) {
    this.dateOfPublication = dateOfPublication;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getAuthors() {
    return authors;
  }

  public void addAuthor(String author) {
    this.authors.add(author);
  }

  public void setAuthors(List<String> authors) {
    this.authors = authors;
  }

  @Override
  public String toString() {
    StringBuilder sBuilder = new StringBuilder();
    sBuilder.append("\n Name: " + this.getName());
    sBuilder.append("\n Date Of Publication: " + this.getDateOfPublication());

    sBuilder.append("\n Authors: ");
    for (String author : this.getAuthors()) {
      sBuilder.append("\n" + author);
    }

    return sBuilder.toString();
  }

  @Override
  public JSONObject toJSONObject() {
    JSONObject obj = new JSONObject();
    obj.put("id", this.getId().toString());
    obj.put("name", this.getName());
    if (this.getDateOfPublication() != null) {
      obj.put("dateOfPublication", this.getDateOfPublication().toJSONObject());
    }
    obj.put("authors", new JSONArray(this.getAuthors()));

    return obj;
  }

  @Override
  public Map<String, Object> toYAMLObject() {
    Map<String, Object> obj = new HashMap<>();
    obj.put("id", this.getId().toString());
    obj.put("name", this.getName());
    if (this.getDateOfPublication() != null) {
      obj.put("dateOfPublication", this.getDateOfPublication().toYAMLObject());
    }
    obj.put("authors", this.getAuthors());

    return obj;
  }

  public static Source importJSONObject(JSONObject obj) throws ClassNotFoundException {
    switch ((String) obj.get("type")) {
      case "Book":
        return new Book(obj);
      case "CustomSource":
        return new CustomSource(obj);
      case "HistoricalRecord":
        return new HistoricalRecord(obj);
      case "OnlineResource":
        return new OnlineResource(obj);
      case "OrallyTransmitted":
        return new OrallyTransmitted(obj);
      default:
        throw new ClassNotFoundException();
    }
  }
}
