package pt.up.fe.sources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class Book extends Source {
  private Integer pages;
  private String publisher;

  public Book(String name) {
    super(name);
  }

  public Book(String name, String id) {
    super(name, id);
  }

  public Integer getPages() {
    return pages;
  }

  public void setPages(Integer pages) {
    this.pages = pages;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  @Override
  public JSONObject toJSONObject() {
    JSONObject obj = super.toJSONObject();
    obj.put("type", this.getClass().getSimpleName());
    obj.put("pages", this.getPages());
    obj.put("publisher", this.getPublisher());
    return obj;
  }

  @Override
  public Map<String, Object> toYAMLObject() {
    Map<String, Object> obj = super.toYAMLObject();
    obj.put("type", this.getClass().getSimpleName());
    obj.put("pages", this.getPages());
    obj.put("publisher", this.getPublisher());

    return obj;
  }

  public static Book importJSONObject(JSONObject obj) {
    Book book = new Book((String) obj.get("name"), (String) obj.get("id"));

    // TODO: Date of Publication

    JSONArray authors = obj.getJSONArray("authors");
    List<String> a = new ArrayList<>();
    for (Object auth : authors.toList()) {
      a.add((String) auth);
    }
    book.setAuthors(a);

    book.setPages((Integer) obj.get("pages"));
    book.setPublisher((String) obj.get("publisher"));

    return book;
  }
}
