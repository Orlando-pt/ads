package pt.up.fe.facades;

import pt.up.fe.Main;
import pt.up.fe.dates.IDate;
import pt.up.fe.dtos.events.*;
import pt.up.fe.events.*;
import pt.up.fe.person.Person;
import pt.up.fe.places.Place;
import pt.up.fe.sources.Source;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EventFacade {

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

    public static Event createBirthEvent(BirthEventDTO event) {
        Event birthEvent = new Birth();

        if (!event.getMaternity().isEmpty()) {
            birthEvent.addSpecialPurposeField("Maternity", event.getMaternity());
        }

        addCommonFields(
                birthEvent,
                event.getPlace(),
                event.getDate(),
                event.getSource(),
                event.getPersons(),
                event.getSpecialFields(),
                event.getDescription()
        );

        handleEditOrCreate(birthEvent, event.getEditId(), event.getPerson());
        return birthEvent;
    }

    public static Event createDeathEvent(DeathEventDTO event) {
        Event deathEvent = new Death();

        if (!event.getTypeOfDeath().isEmpty()) {
            deathEvent.addSpecialPurposeField("Type of Death", event.getTypeOfDeath());
        }

        addCommonFields(
                deathEvent,
                event.getPlace(),
                event.getDate(),
                event.getSource(),
                event.getPersons(),
                event.getSpecialFields(),
                event.getDescription()
        );

        handleEditOrCreate(deathEvent, event.getEditId(), event.getPerson());
        return deathEvent;
    }

    public static Event createEmigrationEvent(EmigrationEventDTO event) {
        Event emigrationEvent = new Emigration();

        if (!event.getTypeOfEmigration().isEmpty()) {
            emigrationEvent.addSpecialPurposeField("Type of Emigration", event.getTypeOfEmigration());
        }

        if (event.getPushFactor() != null) {
            emigrationEvent.addSpecialPurposeField("Push factor", event.getPushFactor());
        }

        if (event.getPullFactor() != null) {
            emigrationEvent.addSpecialPurposeField("Pull factor", event.getPullFactor());
        }

        addCommonFields(
                emigrationEvent,
                event.getPlace(),
                event.getDate(),
                event.getSource(),
                event.getPersons(),
                event.getSpecialFields(),
                event.getDescription()
        );

        handleEditOrCreate(emigrationEvent, event.getEditId(), event.getPerson());
        return emigrationEvent;
    }

    public static Event createMarriageEvent(MarriageEventDTO event) {
        Event marriageEvent = new Marriage();

        if (!event.getMarriageName().isEmpty()) {
            marriageEvent.addSpecialPurposeField("Marriage Name", event.getMarriageName());
        }

        if (event.getTypeOfMarriage() != null) {
            marriageEvent.addSpecialPurposeField("Type Of Marriage", event.getTypeOfMarriage());
        }

        addCommonFields(
                marriageEvent,
                event.getPlace(),
                event.getDate(),
                event.getSource(),
                event.getPersons(),
                event.getSpecialFields(),
                event.getDescription()
        );

        handleEditOrCreate(marriageEvent, event.getEditId(), event.getPerson());
        return marriageEvent;
    }

    public static Event createResidenceEvent(ResidenceEventDTO event) {
        Event residenceEvent = new Residence();

        if (!event.getResidenceName().isEmpty()) {
            residenceEvent.addSpecialPurposeField("Residence Name", event.getResidenceName());
        }

        if (event.getTypeOfPlace() == null) {
            residenceEvent.addSpecialPurposeField("Type Of Place", event.getTypeOfPlace());
        }

        addCommonFields(
                residenceEvent,
                event.getPlace(),
                event.getDate(),
                event.getSource(),
                event.getPersons(),
                event.getSpecialFields(),
                event.getDescription()
        );

        handleEditOrCreate(residenceEvent, event.getEditId(), event.getPerson());
        return residenceEvent;
    }

    public static Event createCustomEvent(CustomEventDTO event) {
        Event customEvent = new CustomEvent(event.getCustomName());

        if (!event.getTypeOfCustom().isEmpty()) {
            customEvent.addSpecialPurposeField("Type of Custom Event", event.getTypeOfCustom());
        }

        addCommonFields(
                customEvent,
                event.getPlace(),
                event.getDate(),
                event.getSource(),
                event.getPersons(),
                event.getSpecialFields(),
                event.getDescription()
        );

        handleEditOrCreate(customEvent, event.getEditId(), event.getPerson());
        return customEvent;
    }

    private static void handleEditOrCreate(Event event, UUID id, Person person) {
        if (id != null) {
            ListIterator<Event> iterator = Main.eventsList.listIterator();
            while (iterator.hasNext()) {
                Event next = iterator.next();
                if (next.getId().equals(id)) {
                    iterator.set(event);
                }
            }

            ListIterator<Event> iterator1 = person.getEvents().listIterator();
            while (iterator1.hasNext()) {
                Event next = iterator1.next();
                if (next.getId().equals(id)) {
                    iterator1.set(event);
                }
            }
        } else {
            person.addEvent(event);
            Main.eventsList.add(event);
        }
    }

    private static void addCommonFields(Event ev, Place place, IDate date, Source source, HashMap<String, Person> persons, HashMap<String, String> specialFields, String description) {
        if (place != null) {
            ev.setPlace(place);
        }

        if (date != null) {
            ev.setDate(date);
        }

        if(source != null) {
            ev.setSource(source);
        }

        persons.forEach((key, value) -> {
            ev.addPeopleRelation(key, value);
        });

        specialFields.forEach((key, value) -> {
            ev.addSpecialPurposeField(key, value);
        });

        if (!description.isEmpty()) {
            ev.setDescription(description);
        }
    }
}