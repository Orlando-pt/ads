package pt.up.fe.events;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import pt.up.fe.person.Person;

public class Marriage extends Event {

    private Person person1;
    private Person person2;

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
    }

    public Marriage(Map<String, Object> obj) {
      super(obj);
        this.setName(this.getClass().getSimpleName());
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

    public Person getPartner(Person known) {
        // TODO when the equals() is created maybe change this method to compare the objects
        if (this.person1.getName().equals(known.getName()))
            return this.person2;
        else
            return this.person1;
    }

    public Logger initializeLogger() {
        return LogManager.getLogger(Marriage.class);
    }

  @Override
  public JSONObject toJSONObject() {
    JSONObject obj = super.toJSONObject();
    obj.put("type", this.getClass().getSimpleName());
    return obj;
  }

  @Override
  public Map<String, Object> toYAMLObject() {
    Map<String, Object> obj = super.toYAMLObject();
    obj.put("type", this.getClass().getSimpleName());
    return obj;
  }
}
