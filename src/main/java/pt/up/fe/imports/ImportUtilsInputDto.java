package pt.up.fe.imports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import pt.up.fe.events.Event;
import pt.up.fe.person.Person;
import pt.up.fe.places.Place;
import pt.up.fe.sources.Source;

public class ImportUtilsInputDto {
  private final Map<UUID, Person> people = new HashMap<>();
  private final Map<UUID, Source> sources = new HashMap<>();
  private final Map<UUID, Place> places = new HashMap<>();
  private final Map<UUID, Event> events = new HashMap<>();

  public ImportUtilsInputDto(
      List<Person> people, List<Source> sources, List<Place> places, List<Event> events) {
    for (Person p : people) {
      this.people.put(p.getId(), p);
    }
    for (Source p : sources) {
      this.sources.put(p.getId(), p);
    }
    for (Place p : places) {
      this.places.put(p.getId(), p);
    }
    for (Event p : events) {
      this.events.put(p.getId(), p);
    }
  }

  public Map<UUID, Person> getPeople() {
    return people;
  }

  public Map<UUID, Source> getSources() {
    return sources;
  }

  public Map<UUID, Place> getPlaces() {
    return places;
  }

  public Map<UUID, Event> getEvents() {
    return events;
  }
}
