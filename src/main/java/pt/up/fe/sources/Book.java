package pt.up.fe.sources;

import java.util.Map;
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

  public Book(JSONObject obj) {
    super(obj);

    if (obj.has("pages")) {
      this.pages = obj.getInt("pages");
    }
    if (obj.has("publisher")) {
      this.publisher = obj.getString("publisher");
    }
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
}
