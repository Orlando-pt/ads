package pt.up.fe.person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.json.JSONArray;
import org.json.JSONObject;
import pt.up.fe.BaseClass;
import pt.up.fe.events.Event;

public class Person extends BaseClass {
  private Gender gender;
  private List<Event> events = new ArrayList<>();
  private List<Person> children = new ArrayList<>();

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public List<Event> getEvents() {
    return events;
  }

  public void addEvent(Event event) {
    this.events.add(event);
  }

  public void removeEvent(Event event) {
    this.events.remove(event);
  }

  public List<Person> getChildren() {
    return children;
  }

  public void addChild(Person person) {
    this.children.add(person);
  }

  public void removeChild(Person person) {
    this.children.remove(person);
  }

  public Map<String, Person> getParents() {
    Map<String, Person> parents = new HashMap<>();
    Person mother = null;
    Person father = null;
    for (Event event : this.events) {
      if (event.getClass().getSimpleName().equals("Birth")) {

        mother = event.getPeopleRelations().get("Mother");
        father = event.getPeopleRelations().get("Father");
        break;
      }
    }
    Optional.ofNullable(mother).ifPresent(v -> parents.put("Mother", v));
    Optional.ofNullable(father).ifPresent(v -> parents.put("Father", v));
    return parents;
  }

  @Override
  public String toString() {
    StringBuilder sBuilder = new StringBuilder();
    sBuilder.append("Name: " + super.getName());
    sBuilder.append("\nGender: " + this.getGender());

    sBuilder.append("\nEvents: ");
    for (Event event : this.getEvents()) {
      sBuilder.append("\n" + event);
    }

    sBuilder.append("\nParents: ");
    Map<String, Person> parents = this.getParents();
    for (String parent : parents.keySet()) {
      sBuilder.append("\n" + parent + ": " + parents.get(parent));
    }

    sBuilder.append("\nChildren: ");
    for (Person child : this.getChildren()) {
      sBuilder.append("\n" + child.getName());
    }

    sBuilder.append("\nDescription: " + super.getDescription());
    return sBuilder.toString();
  }

  @Override
  public JSONObject toJSONObject() {
    JSONObject obj = super.toJSONObject();
    if (this.getGender() != null) {
      obj.put("gender", this.getGender());
    }
    JSONArray events = new JSONArray();
    for (Event event : this.getEvents()) {
      events.put(event.getId().toString());
    }
    obj.put("events", events);
    JSONArray children = new JSONArray();
    for (Person child : this.getChildren()) {
      children.put(child.getId().toString());
    }
    obj.put("children", children);

    return obj;
  }

  @Override
  public Map<String, Object> toYAMLObject() {
    Map<String, Object> obj = super.toYAMLObject();

    if (this.getGender() != null) {
      obj.put("gender", this.getGender().toString());
    }
    List<String> events = new ArrayList<>();
    for (Event event : this.getEvents()) {
      events.add(event.getId().toString());
    }
    obj.put("events", events);
    List<String> children = new ArrayList<>();
    for (Person child : this.getChildren()) {
      children.add(child.getId().toString());
    }
    obj.put("children", children);
    return obj;
  }
}
