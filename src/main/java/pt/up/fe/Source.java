package pt.up.fe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;
import pt.up.fe.dates.IDate;

public abstract class Source implements IExportObject {
  private final UUID id = UUID.randomUUID();
  private IDate dateOfPublication;
  private String name;
  private List<String> authors;

  public Source(String name) {
    this.name = name;
    this.authors = new ArrayList<>();
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
}
