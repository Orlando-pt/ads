package pt.up.fe.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.events.Birth;
import pt.up.fe.events.Event;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private Person person;
    private List<Event> personEvents;
    private List<Person> children;

    @BeforeEach
    public void setUp() {
        person = new Person();
        person.setGender(Gender.MALE);
        person.setDescription("description");
        person.setName("Hugo");
        Event birth = new Birth();
        birth.setName("Birth of Hugo");

        personEvents = new ArrayList<>();
        personEvents.add(birth);
        person.addEvent(birth);

        children = new ArrayList<>();
        Person child = new Person();
        child.setGender(Gender.FEMALE);
        child.setName("Mariana");
        children.add(child);
        person.addChild(child);
    }

    @Test
    public void testPersonParams() {
        assertEquals(Gender.MALE, person.getGender());
        assertEquals("description", person.getDescription());
        assertEquals("Hugo", person.getName());
        assertEquals(personEvents, person.getEvents());
        assertEquals(children, person.getChildren());
    }

}