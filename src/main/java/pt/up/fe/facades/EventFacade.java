package pt.up.fe.facades;

import pt.up.fe.Main;
import pt.up.fe.dates.IDate;
import pt.up.fe.dtos.events.FilterEventsDTO;
import pt.up.fe.events.*;
import pt.up.fe.person.Person;

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

    public Event createBirthEvent(String maternity, String placeOfBirth, IDate dateOfBirth, HashMap<String, Person> persons, HashMap<String, String> specialFields, String description, UUID editId) {
        Event birthEvent = new Birth();

        if (!maternity.isEmpty()) {
            birthEvent.addSpecialPurposeField("Maternity", maternity);
        }

        if (!placeOfBirth.isEmpty()) {
            // TODO Fix this
            //birthEvent.addPlaceRelation("Place of Birth", this.getPlaceFacade().choosePlace());
        }

        if (dateOfBirth != null) {
            birthEvent.setDate(dateOfBirth);
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

        handleEditOrCreate(birthEvent, editId);
        return birthEvent;
    }

    public Event createDeathEvent(String typeOfDeath, String placeOfDeath, IDate dateOfDeath, HashMap<String, Person> persons, HashMap<String, String> specialFields, String description, UUID editId) {
        Event deathEvent = new Death();

        if (!typeOfDeath.isEmpty()) {
            deathEvent.addSpecialPurposeField("Type of Death", typeOfDeath);
        }

        if (!placeOfDeath.isEmpty()) {
            // TODO Fix this
            //deathEvent.addPlaceRelation("Place of Death", this.getPlaceFacade().choosePlace());
        }

        if (dateOfDeath != null) {
            deathEvent.setDate(dateOfDeath);
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

        handleEditOrCreate(deathEvent, editId);
        return deathEvent;
    }

    public Event createEmigrationEvent(String typeOfEmigration, String placeOfEmigration, IDate dateOfEmigration, String pushFactor, String pullFactor, HashMap<String, Person> persons, HashMap<String, String> specialFields, String description, UUID editId) {
        Event emigrationEvent = new Emigration();

        if (!typeOfEmigration.isEmpty()) {
            emigrationEvent.addSpecialPurposeField("Type of Emigration", typeOfEmigration);
        }

        if (!placeOfEmigration.isEmpty()) {
            // TODO Fix this
            // emigrationEvent.addPlaceRelation("Country of Emigration", this.getPlaceFacade().choosePlace());
        }

        if (dateOfEmigration != null) {
            emigrationEvent.setDate(dateOfEmigration);
        }

        if (!pushFactor.isEmpty()) {
            emigrationEvent.addSpecialPurposeField("Push factor", pushFactor);
        }

        if (!pullFactor.isEmpty()) {
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

        handleEditOrCreate(emigrationEvent, editId);
        return emigrationEvent;
    }

    public Event createMarriageEvent(String marriageName, String placeOfMarriage, IDate dateOfMarriage, String typeOfMarriage, HashMap<String, Person> persons, HashMap<String, String> specialFields, String description, UUID editId) {
        Event marriageEvent = new Marriage();

        if (!marriageName.isEmpty()) {
            marriageEvent.addSpecialPurposeField("Marriage Name", marriageName);
        }

        if (!placeOfMarriage.isEmpty()) {
            // TODO Fix this
            // marriageEvent.addPlaceRelation("Country of Marriage", this.getPlaceFacade().choosePlace());
        }

        if (dateOfMarriage != null) {
            marriageEvent.setDate(dateOfMarriage);
        }

        if (!typeOfMarriage.isEmpty()) {
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

        handleEditOrCreate(marriageEvent, editId);
        return marriageEvent;
    }

    public Event createResidenceEvent(String residenceName, String placeOfResidence, IDate dateOfResidence, String typeOfPlace, HashMap<String, Person> persons, HashMap<String, String> specialFields, String description, UUID editId) {
        Event residenceEvent = new Residence();

        if (!residenceName.isEmpty()) {
            residenceEvent.addSpecialPurposeField("Residence Name", residenceName);
        }

        if (!placeOfResidence.isEmpty()) {
            // TODO Fix this
            // residenceEvent.addPlaceRelation("Country of Residence", this.getPlaceFacade().choosePlace());
        }

        if (dateOfResidence != null) {
            residenceEvent.setDate(dateOfResidence);
        }

        if (!typeOfPlace.isEmpty()) {
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

        handleEditOrCreate(residenceEvent, editId);
        return residenceEvent;
    }

    public Event createCustomEvent(String customName, String placeOfCustom, IDate dateOfCustom, String typeOfCustom, HashMap<String, Person> persons, HashMap<String, String> specialFields, String description, UUID editId) {
        Event customEvent = new CustomEvent(customName);

        if (!typeOfCustom.isEmpty()) {
            customEvent.addSpecialPurposeField("Type of Custom Event", typeOfCustom);
        }

        if (!placeOfCustom.isEmpty()) {
            // TODO Fix this
            // customEvent.addPlaceRelation("Country of Custom Event", this.getPlaceFacade().choosePlace());
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

        handleEditOrCreate(customEvent, editId);
        return customEvent;
    }

    private void handleEditOrCreate(Event event, UUID id) {
        if (id != null) {
            ListIterator<Event> iterator = Main.eventsList.listIterator();
            while (iterator.hasNext()) {
                Event next = iterator.next();
                if (next.getId().equals(id)) {
                    System.out.println("Entrou");
                    iterator.set(event);
                }
            }
        } else {
            Main.eventsList.add(event);
        }
    }
}