package pt.up.fe.dtos.events;

import pt.up.fe.person.Person;

public class PersonEventDTO {
    private String relationship;
    private Person person;
    private String name;

    public PersonEventDTO(String relationship, Person person) {
        this.relationship = relationship;
        this.person = person;
        this.name = person.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
