package pt.up.fe.sources;

import org.json.JSONObject;
import pt.up.fe.Source;

public class Book extends Source {
  private Integer pages;
  private String publisher;

  public Book(String name) {
    super(name);
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
}
