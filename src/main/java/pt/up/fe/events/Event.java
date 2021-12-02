package pt.up.fe.events;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import pt.up.fe.BaseClass;
import pt.up.fe.dates.IDate;
import pt.up.fe.person.Person;
import pt.up.fe.places.Place;

public abstract class Event extends BaseClass {
  private Place place;
  private IDate date;
  private Map<String, Person> peopleRelations = new HashMap<>();
  private Map<String, IDate> dateRelations = new HashMap<>();
  private Map<String, Place> placeRelations = new HashMap<>();
  private Map<String, String> specialPurposeFields = new HashMap<>();

  private static Logger logger;

  public Event() {
    logger = initializeLogger();
  }

  public abstract Logger initializeLogger();

  public Place getPlace() {
    return place;
  }

  public void setPlace(Place place) {
    this.place = place;
  }

  public IDate getDate() {
    return date;
  }

  public void setDate(IDate date) {
    this.date = date;
  }

  public Map<String, Person> getPeopleRelations() {
    return peopleRelations;
  }

  public Map<String, IDate> getDateRelations() {
    return dateRelations;
  }

  public Map<String, Place> getPlaceRelations() {
    return placeRelations;
  }

  public Map<String, String> getSpecialPurposeFields() {
    return specialPurposeFields;
  }

  public void addPeopleRelation(String relation, Person person) {
    this.peopleRelations.put(relation, person);
  }

  public void addDateRelation(String relation, IDate date) {
    this.dateRelations.put(relation, date);
  }

  public void addPlaceRelation(String relation, Place place) {
    this.placeRelations.put(relation, place);
  }

  public void addSpecialPurposeField(String relation, String field) {
    this.specialPurposeFields.put(relation, field);
  }

  public void removePeopleRelation(String relation) {
    this.peopleRelations.remove(relation);
  }

  public void removeDateRelation(String relation) {
    this.dateRelations.remove(relation);
  }

  public void removePlaceRelation(String relation) {
    this.placeRelations.remove(relation);
  }

  public void removeSpecialPurposeField(String relation) {
    this.specialPurposeFields.remove(relation);
  }

  @Override
  public JSONObject toJSONObject() {
    JSONObject obj = super.toJSONObject();

    if (this.getPlace() != null) {
      obj.put("place", this.getPlace().getId().toString());
    }
    if (this.getDate() != null) {
      obj.put("date", this.getDate().toJSONObject());
    }

    JSONObject peopleRelations = new JSONObject();
    for (Map.Entry<String, Person> entry : this.getPeopleRelations().entrySet()) {
      if (entry.getValue() == null) {
        continue;
      }
      peopleRelations.put(entry.getKey(), entry.getValue().getId().toString());
    }
    obj.put("peopleRelations", peopleRelations);

    JSONObject dateRelations = new JSONObject();
    for (Map.Entry<String, IDate> entry : this.getDateRelations().entrySet()) {
      if (entry.getValue() == null) {
        continue;
      }
      dateRelations.put(entry.getKey(), entry.getValue().toJSONObject());
    }
    obj.put("dateRelations", dateRelations);

    JSONObject placeRelations = new JSONObject();
    for (Map.Entry<String, Place> entry : this.getPlaceRelations().entrySet()) {
      if (entry.getValue() == null) {
        continue;
      }
      placeRelations.put(entry.getKey(), entry.getValue().getId().toString());
    }
    obj.put("placeRelations", placeRelations);

    obj.put("specialPurposeFields", new JSONObject(this.getSpecialPurposeFields()));
    return obj;
  }

  @Override
  public Map<String, Object> toYAMLObject() {
    Map<String, Object> obj = super.toYAMLObject();
    if (this.getPlace() != null) {
      obj.put("place", this.getPlace().getId().toString());
    }
    if (this.getDate() != null) {
      obj.put("date", this.getDate().toYAMLObject());
    }

    Map<String, Object> peopleRelations = new HashMap<>();
    for (Map.Entry<String, Person> entry : this.getPeopleRelations().entrySet()) {
      if (entry.getValue() == null) {
        continue;
      }
      peopleRelations.put(entry.getKey(), entry.getValue().getId().toString());
    }
    obj.put("peopleRelations", peopleRelations);

    Map<String, Object> dateRelations = new HashMap<>();
    for (Map.Entry<String, IDate> entry : this.getDateRelations().entrySet()) {
      if (entry.getValue() == null) {
        continue;
      }
      dateRelations.put(entry.getKey(), entry.getValue().toYAMLObject());
    }
    obj.put("dateRelations", dateRelations);

    Map<String, Object> placeRelations = new HashMap<>();
    for (Map.Entry<String, Place> entry : this.getPlaceRelations().entrySet()) {
      if (entry.getValue() == null) {
        continue;
      }
      placeRelations.put(entry.getKey(), entry.getValue().getId().toString());
    }
    obj.put("placeRelations", placeRelations);

    obj.put("specialPurposeFields", this.getSpecialPurposeFields());
    return obj;
  }
}
