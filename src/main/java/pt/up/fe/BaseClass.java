package pt.up.fe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.JSONObject;
import pt.up.fe.exports.IExportObject;
import pt.up.fe.sources.Source;

public abstract class BaseClass implements IExportObject {
  private final UUID id;
  private String name;
  private String description;
  private Source source;
  private UUID auxSource;

  public BaseClass() {
    this.id = UUID.randomUUID();
  }

  public BaseClass(String id) {
    this.id = UUID.fromString(id);
  }

  public BaseClass(JSONObject obj) {
    if (obj.has("id")) {
      this.id = UUID.fromString(obj.getString("id"));
    } else {
      this.id = UUID.randomUUID();
    }
    if (obj.has("name")) {
      this.name = obj.getString("name");
    }
    if (obj.has("description")) {
      this.description = obj.getString("description");
    }
    if (obj.has("source")) {
      this.auxSource = UUID.fromString(obj.getString("source"));
    }
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Source getSource() {
    return this.source;
  }

  public void setSource(Source source) {
    this.source = source;
  }

  public UUID getAuxSource() {
    return this.auxSource;
  }

  @Override
  public JSONObject toJSONObject() {
    JSONObject obj = new JSONObject();
    obj.put("id", this.id.toString());
    obj.put("name", this.getName());
    obj.put("description", this.getDescription());
    if (this.getSource() != null) {
      obj.put("source", this.getSource().getId());
    }

    return obj;
  }

  @Override
  public Map<String, Object> toYAMLObject() {
    Map<String, Object> obj = new HashMap<>();
    obj.put("id", this.id.toString());
    obj.put("name", this.getName());
    obj.put("description", this.getDescription());
    if (this.getSource() != null) {
      obj.put("source", this.getSource().getId().toString());
    }
    return obj;
  }
}
