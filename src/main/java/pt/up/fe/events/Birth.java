package pt.up.fe.events;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pt.up.fe.person.Person;

public class Birth extends Event {

    private Person parent1;
    private Person parent2;
    private Person child;


    public Birth(Person parent1, Person parent2, Person child) {
        this.parent1 = parent1;
        this.parent2 = parent2;
        this.child = child;

        this.setName(this.getClass().getSimpleName());
    }

    public Birth() {
        this.setName(this.getClass().getSimpleName());
    }

    public Logger initializeLogger() {
        return LogManager.getLogger(Birth.class);
    }

    public Person getParent1() {
        return parent1;
    }

    public void setParent1(Person parent1) {
        this.parent1 = parent1;
    }

    public Person getParent2() {
        return parent2;
    }

    public void setParent2(Person parent2) {
        this.parent2 = parent2;
    }

    public Person getChild() {
        return child;
    }

    public void setChild(Person child) {
        this.child = child;
    }
}
