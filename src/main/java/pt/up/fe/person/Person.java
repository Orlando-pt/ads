package pt.up.fe.person;

import pt.up.fe.BaseClass;
import pt.up.fe.events.Birth;
import pt.up.fe.events.Event;
import pt.up.fe.events.Marriage;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Person extends BaseClass {
    private Gender gender;
    private List<Event> events = new ArrayList<>();
    private List<Person> children = new ArrayList<>();

    private static Logger logger = LogManager.getLogger(Person.class);

    public Person() {

    }

    public Person getFather() {
        // TODO
        return null;
    }

    public Person getMother() {
        // TODO
        return null;
    }
    
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

    // marriage methods
    
    public List<Event> getMarriages() {
        return this.events.stream().filter(
            (event) -> event.getClass() == Marriage.class
        ).collect(Collectors.toList());
    }

    public int getNumberOfMarriages() {
        return this.getMarriages().size();
    }

    public boolean marriedMoreThanOnce() {
        return this.getNumberOfMarriages() > 1;
    }

    public Person getPartner() {
        List<Event> marriages = this.getMarriages();

        if (marriages.size() != 1) {
            logger.error("Get partner of a person that has " + marriages.size() + " partner(s).");
            return null;
        }
        
        Marriage marriage = (Marriage) marriages.get(0);
        return marriage.getPartner(this);
    }

    public List<Person> getPartners() {
        List<Person> partners = new ArrayList<>();

        List<Event> marriages = this.getMarriages();

        marriages.forEach(
            (marriage) -> {
                Marriage currentMarriage = (Marriage) marriage;
                partners.add(
                    currentMarriage.getPartner(this)
                );
            }
        );
        return partners;
    }



    // birth methods
    public Birth getBirth() {
        return (Birth) this.events.stream().filter(
            (event) -> event.getClass() == Birth.class
        ).collect(Collectors.toList()).get(0);
    }

    public Person getOppositeParent(Person parent) {
        Birth birth = this.getBirth();
        if (birth.getParent1().equals(parent))
            return birth.getParent2();

        return birth.getParent1();
    }


    public Map<String, Person> getParents() {
        Map<String, Person> parents = new HashMap<>();
        Person mother = null;
        Person father = null;
        for (Event event : this.events) {
            if (event.getClass().getSimpleName().equals("Birth")) {

                mother = event.getPeopleRelations().get("Mother");
                father = event.getPeopleRelations().get("Father");
                break;
            }
        }
        Optional.ofNullable(mother).ifPresent(v -> parents.put("Mother", v));
        Optional.ofNullable(father).ifPresent(v -> parents.put("Father", v));
        return parents;
    }

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("Name: " + super.getName());
        sBuilder.append("\nGender: " + this.getGender());

        sBuilder.append("\nEvents: ");
        for (Event event : this.getEvents()) {
            sBuilder.append("\n" + event);
        }

        sBuilder.append("\nParents: ");
        Map<String, Person> parents = this.getParents();
        for (String parent : parents.keySet()) {
            sBuilder.append("\n" + parent + ": " + parents.get(parent));
        }

        sBuilder.append("\nChildren: ");
        for (Person child : this.getChildren()) {
            sBuilder.append("\n" + child.getName());
        }

        sBuilder.append("\nDescription: " + super.getDescription());
        return sBuilder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((children == null) ? 0 : children.hashCode());
        result = prime * result + ((events == null) ? 0 : events.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (children == null) {
            if (other.children != null)
                return false;
        } else if (!children.equals(other.children))
            return false;
        if (events == null) {
            if (other.events != null)
                return false;
        } else if (!events.equals(other.events))
            return false;
        if (gender != other.gender)
            return false;
        return true;
    }
}
