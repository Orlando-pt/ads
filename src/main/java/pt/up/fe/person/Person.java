package pt.up.fe.person;

import pt.up.fe.BaseClass;
import pt.up.fe.events.Event;

import java.util.ArrayList;
import java.util.List;

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


    @Override
    public String toString() {
        return "Name - " + this.getName() +
                "\nGender - " + this.getGender() +
                "\nEvents - \n" + this.getEvents() +
                "\nChildren - \n" + this.getChildren() +
                "\nDescription - " + this.getDescription() +
                "\nSource - " + this.getSource();
    }
}
