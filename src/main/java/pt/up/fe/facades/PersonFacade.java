package pt.up.fe.facades;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import pt.up.fe.Main;
import pt.up.fe.dtos.persons.FilterPersonsDTO;
import pt.up.fe.dtos.persons.PersonDTO;
import pt.up.fe.events.Event;
import pt.up.fe.person.Person;


public class PersonFacade {

  public static Person createPerson(PersonDTO personDTO) {
    Person person = new Person();
    person = setPersonProperties(person, personDTO);
    Main.peopleList.add(person);
    return person;
  }

  private static Person setPersonProperties(Person person, PersonDTO personDTO) {
    person.setName(personDTO.getFirstName());
    person.setMiddleName(personDTO.getMiddleName());
    person.setLastName(personDTO.getLastName());
    person.setGender(personDTO.getGender());
    if (personDTO.getSource() != null) {
      person.setSource(personDTO.getSource());
    }
    person.setDescription((personDTO.getDescription()));
    return person;
  }

  public static Person addEventToPerson(Person person, Event event) {
    person.addEvent(event);
    return person;
  }

  public static List<Person> filterPersons(FilterPersonsDTO filterPersonsDTO) {

    Predicate<Person> byFirstName = person -> filterPersonsDTO.getFirstName().isEmpty()
        || person.getName() != null && person.getName().toLowerCase()
        .contains(filterPersonsDTO.getFirstName().toLowerCase());

    Predicate<Person> byMiddleName = person -> filterPersonsDTO.getMiddleName().isEmpty()
        || person.getMiddleName() != null && person.getMiddleName().toLowerCase()
        .contains(filterPersonsDTO.getMiddleName().toLowerCase());

    Predicate<Person> byLastName = person -> filterPersonsDTO.getLastName().isEmpty()
        || person.getLastName() != null && person.getLastName().toLowerCase().contains(
        filterPersonsDTO.getLastName().toLowerCase());

    List<Person> result = Main.peopleList.stream()
        .filter(byFirstName.and(byMiddleName.and(byLastName)))
        .collect(Collectors.toList());

    return result;

  }

  public static Person editPerson(Person person, PersonDTO personDTO) {
    Person personEdited = setPersonProperties(person, personDTO);
    return personEdited;
  }

}

