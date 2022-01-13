package pt.up.fe.imports;

import java.util.Map;
import java.util.UUID;
import pt.up.fe.events.Event;
import pt.up.fe.person.Person;
import pt.up.fe.places.CompoundPlace;
import pt.up.fe.places.Place;
import pt.up.fe.sources.HistoricalRecord;
import pt.up.fe.sources.OrallyTransmitted;
import pt.up.fe.sources.Source;

public abstract class ImportUtils {
  public static ImportUtilsOutputDto link(ImportUtilsInputDto obj) {
    Map<UUID, Place> places = ImportUtils.populatePlaces(obj.getPlaces());
    Map<UUID, Source> sources = ImportUtils.populateSources(obj.getSources(), places);
    Map<UUID, Person> people = obj.getPeople();
    Map<UUID, Event> events = ImportUtils.populateEvents(obj.getEvents(), places, people);
    people = ImportUtils.populatePeople(people, events);

    return new ImportUtilsOutputDto(people, sources, places, events);
  }

  private static Map<UUID, Place> populatePlaces(Map<UUID, Place> places) {
    for (Place p : places.values()) {
      if (p.isComposite() == false) {
        continue;
      }
      for (UUID childId : ((CompoundPlace) p).getAuxChildren()) {
        ((CompoundPlace) p).addChild(places.get(childId));
      }
    }
    return places;
  }

  private static Map<UUID, Source> populateSources(
      Map<UUID, Source> sources, Map<UUID, Place> places) {
    for (Source s : sources.values()) {
      if (s instanceof HistoricalRecord) {
        UUID pId = ((HistoricalRecord) s).getAuxNationalArchiveCountry();
        ((HistoricalRecord) s).setNationalArchiveCountry(places.get(pId));
        continue;
      }
      if (s instanceof OrallyTransmitted) {
        UUID pId = ((OrallyTransmitted) s).getAuxPlace();
        ((OrallyTransmitted) s).setPlace(places.get(pId));
        continue;
      }
    }
    return sources;
  }

  private static Map<UUID, Event> populateEvents(
      Map<UUID, Event> events, Map<UUID, Place> places, Map<UUID, Person> people) {

    for (Event e : events.values()) {
      // Place
      e.setPlace(places.get(e.getAuxPlace()));

      // Place relationship
      for (Map.Entry<String, UUID> entry : e.getAuxPlaceRelations().entrySet()) {
        e.addPlaceRelation(entry.getKey(), places.get(entry.getValue()));
      }

      // People relationship
      for (Map.Entry<String, UUID> entry : e.getAuxPeopleRelations().entrySet()) {
        e.addPeopleRelation(entry.getKey(), people.get(entry.getValue()));
      }
    }
    return events;
  }

  private static Map<UUID, Person> populatePeople(
      Map<UUID, Person> people, Map<UUID, Event> events) {
    for (Person p : people.values()) {
      for (UUID childId : p.getAuxChildren()) {
        p.addChild(people.get(childId));
      }
      for (UUID childId : p.getAuxEvents()) {
        p.addEvent(events.get(childId));
      }
    }

    return people;
  }
}
