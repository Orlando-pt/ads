package pt.up.fe.dtos.events;

import pt.up.fe.dates.IDate;
import pt.up.fe.person.Person;
import pt.up.fe.places.Place;
import pt.up.fe.sources.Source;

import java.util.HashMap;
import java.util.UUID;

public class DeathEventDTO {
    private String typeOfDeath;
    private Place place;
    private IDate date;
    private HashMap<String, Person> persons;
    private HashMap<String, String> specialFields;
    private String description;
    private Source source;
    private UUID editId;
    private Person person;

    public String getTypeOfDeath() {
        return typeOfDeath;
    }

    public void setTypeOfDeath(String typeOfDeath) {
        this.typeOfDeath = typeOfDeath;
    }

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

    public HashMap<String, Person> getPersons() {
        return persons;
    }

    public void setPersons(HashMap<String, Person> persons) {
        this.persons = persons;
    }

    public HashMap<String, String> getSpecialFields() {
        return specialFields;
    }

    public void setSpecialFields(HashMap<String, String> specialFields) {
        this.specialFields = specialFields;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public UUID getEditId() {
        return editId;
    }

    public void setEditId(UUID editId) {
        this.editId = editId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
