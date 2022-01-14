package pt.up.fe.events;

import java.util.Map;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import pt.up.fe.person.Person;

public class Marriage extends Event {

  private Person person1;
  private Person person2;
  private UUID auxPerson1;
  private UUID auxPerson2;

  public Marriage() {
    this.setName(this.getClass().getSimpleName());
  }

  public Marriage(String id) {
    super(id);
    this.setName(this.getClass().getSimpleName());
  }

  public Marriage(JSONObject obj) {
    super(obj);
    this.setName(this.getClass().getSimpleName());
    if (obj.has("person1")) {
      this.auxPerson1 = UUID.fromString(obj.getString("person1"));
    }
    if (obj.has("person2")) {
      this.auxPerson2 = UUID.fromString(obj.getString("person2"));
    }
  }

  public Marriage(Person person1, Person person2) {
    this.person1 = person1;
    this.person2 = person2;

    this.setName(this.getClass().getSimpleName());
  }

  public Person getPerson1() {
    return person1;
  }

  public void setPerson1(Person person1) {
    this.person1 = person1;
  }

  public Person getPerson2() {
    return person2;
  }

  public void setPerson2(Person person2) {
    this.person2 = person2;
  }

  public UUID getAuxPerson1() {
    return this.auxPerson1;
  }

  public UUID getAuxPerson2() {
    return this.auxPerson2;
  }

  public Person getPartner(Person known) {
    if (this.person1.getName().equals(known.getName())) return this.person2;
    else return this.person1;
  }

  public Logger initializeLogger() {
    return LogManager.getLogger(Marriage.class);
  }

  @Override
  public JSONObject toJSONObject() {
    JSONObject obj = super.toJSONObject();
    obj.put("type", this.getClass().getSimpleName());
    if (this.person1 != null) {
      obj.put("person1", this.getPerson1().getId().toString());
    }
    if (this.person2 != null) {
      obj.put("person2", this.getPerson2().getId().toString());
    }
    return obj;
  }

  @Override
  public Map<String, Object> toYAMLObject() {
    Map<String, Object> obj = super.toYAMLObject();
    obj.put("type", this.getClass().getSimpleName());
    if (this.person1 != null) {
      obj.put("person1", this.getPerson1().getId().toString());
    }
    if (this.person2 != null) {
      obj.put("person2", this.getPerson2().getId().toString());
    }
    return obj;
  }
}
