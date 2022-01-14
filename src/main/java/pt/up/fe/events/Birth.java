package pt.up.fe.events;

import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import pt.up.fe.person.Person;

public class Birth extends Event {

    private Person parent1;
    private Person parent2;
    private Person child;

    private UUID auxParent1;
    private UUID auxParent2;
    private UUID auxChild;

    public Birth(Person parent1, Person parent2, Person child) {
        this.parent1 = parent1;
        this.parent2 = parent2;
        this.child = child;

        this.setName(this.getClass().getSimpleName());
    }

    public Birth() {
        this.setName(this.getClass().getSimpleName());
    }

    public Birth(String id) {
        super(id);
        this.setName(this.getClass().getSimpleName());
    }

    public Birth(JSONObject obj) {
      super(obj);
        this.setName(this.getClass().getSimpleName());

      if (obj.has("parent1")) {
        this.auxParent1 = UUID.fromString(obj.getString("parent1"));
      }
      if (obj.has("parent2")) {
        this.auxParent2 = UUID.fromString(obj.getString("parent2"));
      }
      if (obj.has("child")) {
        this.auxChild = UUID.fromString(obj.getString("child"));
      }
    }

    public Logger initializeLogger() {
        return LogManager.getLogger(Birth.class);
    }

    public Person getParent1() {
        return parent1;
    }

    public void setParent1(Person parent1) {
        this.parent1 = parent1;
    }

    public Person getParent2() {
        return parent2;
    }

    public void setParent2(Person parent2) {
        this.parent2 = parent2;
    }

    public Person getChild() {
        return child;
    }

    public void setChild(Person child) {
        this.child = child;
    }

    public UUID getAuxParent1() {
      return this.auxParent1;
    }
    public UUID getAuxParent2() {
      return this.auxParent2;
    }
    public UUID getAuxChild() {
      return this.auxChild;
    }
  @Override
  public JSONObject toJSONObject() {
    JSONObject obj = super.toJSONObject();
    obj.put("type", this.getClass().getSimpleName());
    if (this.parent1 != null) {
      obj.put("parent1", this.getParent1().getId().toString());
    }
    if (this.parent2 != null) {
      obj.put("parent2", this.getParent2().getId().toString());
    }
    if (this.child != null) {
      obj.put("child", this.getChild().getId().toString());
    }
    return obj;
  }

  @Override
  public Map<String, Object> toYAMLObject() {
    Map<String, Object> obj = super.toYAMLObject();
    obj.put("type", this.getClass().getSimpleName());
    if (this.parent1 != null) {
      obj.put("parent1", this.getParent1().getId().toString());
    }
    if (this.parent2 != null) {
      obj.put("parent2", this.getParent2().getId().toString());
    }
    if (this.child != null) {
      obj.put("child", this.getChild().getId().toString());
    }
    return obj;
  }
}
