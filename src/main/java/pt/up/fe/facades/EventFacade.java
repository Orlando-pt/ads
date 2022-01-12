package pt.up.fe.facades;

import pt.up.fe.Main;
import pt.up.fe.dates.IDate;
import pt.up.fe.dtos.events.FilterEventsDTO;
import pt.up.fe.events.*;
import pt.up.fe.person.Person;
import pt.up.fe.places.Place;
import pt.up.fe.sources.Source;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EventFacade {

    private PlaceFacade placeFacade;
    private DateFacade dateFacade;

    public EventFacade() {
        Scanner sc = new Scanner(System.in);
        this.dateFacade = new DateFacade();
    }

    public static List<Event> filterEvents(FilterEventsDTO filters) {

        Predicate<Event> byEvent = event -> filters.getEvent().isEmpty()
            || event.getName() != null && event.getName().toLowerCase()
            .contains(filters.getEvent().toLowerCase());

        Predicate<Event> byDate = event -> filters.getDate().isEmpty()
            || event.getDate() != null && event.getDate().toString()
            .contains(filters.getDate());

        Predicate<Event> byDescription = event -> filters.getDescription().isEmpty()
            || event.getDescription() != null && event.getDescription().toLowerCase().contains(
            filters.getDescription().toLowerCase());

        List<Event> result = Main.eventsList.stream()
            .filter(byEvent.and(byDate.and(byDescription)))
            .collect(Collectors.toList());

        return result;
    }

    public PlaceFacade getPlaceFacade() {
        return placeFacade;
    }

    public void setPlaceFacade(PlaceFacade placeFacade) {
        this.placeFacade = placeFacade;
    }

    public DateFacade getDateFacade() {
        return dateFacade;
    }

    public void setDateFacade(DateFacade dateFacade) {
        this.dateFacade = dateFacade;
    }

    public Event createBirthEvent(String maternity, Place placeOfBirth, IDate dateOfBirth, HashMap<String, Person> persons, HashMap<String, String> specialFields, String description, Source source, UUID editId, Person person) {
        Event birthEvent = new Birth();

        if (!maternity.isEmpty()) {
            birthEvent.addSpecialPurposeField("Maternity", maternity);
        }

        if (placeOfBirth != null) {
            birthEvent.setPlace(placeOfBirth);
        }

        if (dateOfBirth != null) {
            birthEvent.setDate(dateOfBirth);
        }

        if(source != null) {
            birthEvent.setSource(source);
        }

        persons.forEach((key, value) -> {
            birthEvent.addPeopleRelation(key, value);
        });

        specialFields.forEach((key, value) -> {
            birthEvent.addSpecialPurposeField(key, value);
        });

        if (!description.isEmpty()) {
            birthEvent.setDescription(description);
        }

        handleEditOrCreate(birthEvent, editId, person);
        return birthEvent;
    }

    public Event createDeathEvent(String typeOfDeath, Place placeOfDeath, IDate dateOfDeath, HashMap<String, Person> persons, HashMap<String, String> specialFields, String description, Source source, UUID editId, Person person) {
        Event deathEvent = new Death();

        if (!typeOfDeath.isEmpty()) {
            deathEvent.addSpecialPurposeField("Type of Death", typeOfDeath);
        }

        if (placeOfDeath != null) {
            deathEvent.setPlace(placeOfDeath);
        }

        if (dateOfDeath != null) {
            deathEvent.setDate(dateOfDeath);
        }

        if(source != null) {
            deathEvent.setSource(source);
        }

        persons.forEach((key, value) -> {
            deathEvent.addPeopleRelation(key, value);
        });

        specialFields.forEach((key, value) -> {
            deathEvent.addSpecialPurposeField(key, value);
        });

        if (!description.isEmpty()) {
            deathEvent.setDescription(description);
        }

        handleEditOrCreate(deathEvent, editId, person);
        return deathEvent;
    }

    public Event createEmigrationEvent(String typeOfEmigration, Place placeOfEmigration, IDate dateOfEmigration, String pushFactor, String pullFactor, HashMap<String, Person> persons, HashMap<String, String> specialFields, String description, Source source, UUID editId, Person person) {
        Event emigrationEvent = new Emigration();

        if (!typeOfEmigration.isEmpty()) {
            emigrationEvent.addSpecialPurposeField("Type of Emigration", typeOfEmigration);
        }

        if (placeOfEmigration != null) {
            emigrationEvent.setPlace(placeOfEmigration);
        }

        if (dateOfEmigration != null) {
            emigrationEvent.setDate(dateOfEmigration);
        }

        if(source != null) {
            emigrationEvent.setSource(source);
        }

        if (pushFactor != null) {
            emigrationEvent.addSpecialPurposeField("Push factor", pushFactor);
        }

        if (pullFactor != null) {
            emigrationEvent.addSpecialPurposeField("Pull factor", pullFactor);
        }

        persons.forEach((key, value) -> {
            emigrationEvent.addPeopleRelation(key, value);
        });

        specialFields.forEach((key, value) -> {
            emigrationEvent.addSpecialPurposeField(key, value);
        });

        if (!description.isEmpty()) {
            emigrationEvent.setDescription(description);
        }

        handleEditOrCreate(emigrationEvent, editId, person);
        return emigrationEvent;
    }

    public Event createMarriageEvent(String marriageName, Place placeOfMarriage, IDate dateOfMarriage, String typeOfMarriage, HashMap<String, Person> persons, HashMap<String, String> specialFields, String description, Source source, UUID editId, Person person) {
        Event marriageEvent = new Marriage();

        if (!marriageName.isEmpty()) {
            marriageEvent.addSpecialPurposeField("Marriage Name", marriageName);
        }

        if (placeOfMarriage != null) {
            marriageEvent.setPlace(placeOfMarriage);
        }

        if(source != null) {
            marriageEvent.setSource(source);
        }

        if (dateOfMarriage != null) {
            marriageEvent.setDate(dateOfMarriage);
        }

        if (typeOfMarriage != null) {
            marriageEvent.addSpecialPurposeField("Type Of Marriage", typeOfMarriage);
        }

        persons.forEach((key, value) -> {
            marriageEvent.addPeopleRelation(key, value);
        });

        specialFields.forEach((key, value) -> {
            marriageEvent.addSpecialPurposeField(key, value);
        });

        if (!description.isEmpty()) {
            marriageEvent.setDescription(description);
        }

        handleEditOrCreate(marriageEvent, editId, person);
        return marriageEvent;
    }

    public Event createResidenceEvent(String residenceName, Place placeOfResidence, IDate dateOfResidence, String typeOfPlace, HashMap<String, Person> persons, HashMap<String, String> specialFields, String description, Source source, UUID editId, Person person) {
        Event residenceEvent = new Residence();

        if (!residenceName.isEmpty()) {
            residenceEvent.addSpecialPurposeField("Residence Name", residenceName);
        }

        if (placeOfResidence != null) {
            residenceEvent.setPlace(placeOfResidence);
        }

        if (dateOfResidence != null) {
            residenceEvent.setDate(dateOfResidence);
        }

        if(source != null) {
            residenceEvent.setSource(source);
        }

        if (typeOfPlace == null) {
            residenceEvent.addSpecialPurposeField("Type Of Place", typeOfPlace);
        }

        persons.forEach((key, value) -> {
            residenceEvent.addPeopleRelation(key, value);
        });

        specialFields.forEach((key, value) -> {
            residenceEvent.addSpecialPurposeField(key, value);
        });

        if (!description.isEmpty()) {
            residenceEvent.setDescription(description);
        }

        handleEditOrCreate(residenceEvent, editId, person);
        return residenceEvent;
    }

    public Event createCustomEvent(String customName, Place placeOfCustom, IDate dateOfCustom, String typeOfCustom, HashMap<String, Person> persons, HashMap<String, String> specialFields, String description, Source source, UUID editId, Person person) {
        Event customEvent = new CustomEvent(customName);

        if (!typeOfCustom.isEmpty()) {
            customEvent.addSpecialPurposeField("Type of Custom Event", typeOfCustom);
        }

        if (placeOfCustom != null) {
            customEvent.setPlace(placeOfCustom);
        }

        if(source != null) {
            customEvent.setSource(source);
        }

        if (dateOfCustom != null) {
            customEvent.setDate(dateOfCustom);
        }

        persons.forEach((key, value) -> {
            customEvent.addPeopleRelation(key, value);
        });

        specialFields.forEach((key, value) -> {
            customEvent.addSpecialPurposeField(key, value);
        });

        if (!description.isEmpty()) {
            customEvent.setDescription(description);
        }

        handleEditOrCreate(customEvent, editId, person);
        return customEvent;
    }

    private void handleEditOrCreate(Event event, UUID id, Person person) {
        if (id != null) {
            ListIterator<Event> iterator = Main.eventsList.listIterator();
            while (iterator.hasNext()) {
                Event next = iterator.next();
                if (next.getId().equals(id)) {
                    iterator.set(event);
                }
            }

            ListIterator<Event> iterator1 = person.getEvents().listIterator();
            while (iterator.hasNext()) {
                Event next = iterator.next();
                if (next.getId().equals(id)) {
                    iterator.set(event);
                }
            }
        } else {
            person.addEvent(event);
            Main.eventsList.add(event);
        }
    }
}