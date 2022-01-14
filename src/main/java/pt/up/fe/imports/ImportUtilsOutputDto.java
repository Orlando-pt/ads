package pt.up.fe.imports;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import pt.up.fe.events.Event;
import pt.up.fe.person.Person;
import pt.up.fe.places.Place;
import pt.up.fe.sources.Source;

public class ImportUtilsOutputDto {
  private final List<Person> people = new ArrayList<>();
  private final List<Source> sources = new ArrayList<>();
  private final List<Place> places = new ArrayList<>();
  private final List<Event> events = new ArrayList<>();

  public ImportUtilsOutputDto(
      Map<UUID, Person> people,
      Map<UUID, Source> sources,
      Map<UUID, Place> places,
      Map<UUID, Event> events) {
    for (Person p : people.values()) {
      this.people.add(p);
    }
    for (Source p : sources.values()) {
      this.sources.add(p);
    }
    for (Place p : places.values()) {
      this.places.add(p);
    }
    for (Event p : events.values()) {
      this.events.add(p);
    }
  }

  public List<Person> getPeople() {
    return people;
  }

  public List<Source> getSources() {
    return sources;
  }

  public List<Place> getPlaces() {
    return places;
  }

  public List<Event> getEvents() {
    return events;
  }
}
