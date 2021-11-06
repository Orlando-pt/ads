package pt.up.fe.events;

import pt.up.fe.BaseClass;
import pt.up.fe.Person;
import pt.up.fe.dates.IDate;
import pt.up.fe.places.Place;

import java.util.HashMap;
import java.util.Map;

public abstract class Event extends BaseClass {
    private Place place;
    private IDate date;
    private Map<String, Person> peopleRelations;
    private Map<String, IDate> dateRelations;
    private Map<String, Place> placeRelations;
    private Map<String, String> specialPurposeFields;

    public Event(Place place, IDate date) {
        this.place = place;
        this.date = date;
        this.peopleRelations = new HashMap<>();
        this.dateRelations = new HashMap<>();
        this.placeRelations = new HashMap<>();
        this.specialPurposeFields = new HashMap<>();

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

}
