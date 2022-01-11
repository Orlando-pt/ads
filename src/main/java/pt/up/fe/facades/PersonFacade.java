package pt.up.fe.facades;

import java.util.List;
import pt.up.fe.Main;
import pt.up.fe.dtos.persons.FilterPersonsDTO;
import pt.up.fe.dtos.persons.PersonDTO;
import pt.up.fe.person.Person;
import pt.up.fe.queries.ChildrenQuery;
import pt.up.fe.queries.FilterPersonByBirthQuery;
import pt.up.fe.queries.FilterPersonByNameQuery;
import pt.up.fe.queries.GrandChildrenQuery;
import pt.up.fe.queries.GrandGrandChildrenQuery;
import pt.up.fe.queries.NameAttribute;
import pt.up.fe.queries.QueryInvoker;
import pt.up.fe.queries.QueryMementoCaretaker;
import pt.up.fe.queries.QueryResultPersonList;
import pt.up.fe.queries.SpecifiedPersonAttributes;


public class PersonFacade {

  private static QueryMementoCaretaker caretaker = new QueryMementoCaretaker();
  private static QueryInvoker invoker = new QueryInvoker(caretaker);

  static {
    caretaker.setOriginator(invoker);
  }

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

  public static List<Person> filterPersons(FilterPersonsDTO filterPersonsDTO) {

    QueryResultPersonList resultReceiver = new QueryResultPersonList();

    switch (filterPersonsDTO.getFilterPersonType()){
      case DATE:
        filterByDate(filterPersonsDTO, resultReceiver);
        break;
      case NAMES:
        filterByNames(filterPersonsDTO, resultReceiver);
        break;
      case CHILDREN:
        getPersonChildren(filterPersonsDTO, resultReceiver);
        break;
      case GRANDCHILDREN:
        getGrandChildren(filterPersonsDTO, resultReceiver);
        break;
      case GRANDGRANDCHILDREN:
        getGrandGrandChildren(filterPersonsDTO, resultReceiver);
        break;
    }

    return resultReceiver.getPersonList();

  }

  public static Person editPerson(Person person, PersonDTO personDTO) {
    Person personEdited = setPersonProperties(person, personDTO);
    return personEdited;
  }

  // TODO pensar que fazer com memento

  public static QueryResultPersonList filterByNames(FilterPersonsDTO filterPersonDto, QueryResultPersonList receiver) {

    SpecifiedPersonAttributes attributes = new SpecifiedPersonAttributes();

    attributes.setName(
        filterPersonDto.getFirstName().isBlank() ? null :
            new NameAttribute(filterPersonDto.getFirstName())
    );
    attributes.setMiddleName(
        filterPersonDto.getMiddleName().isBlank() ? null :
            new NameAttribute(filterPersonDto.getMiddleName())
    );
    attributes.setLastName(
        filterPersonDto.getLastName().isBlank() ? null :
            new NameAttribute(filterPersonDto.getLastName())
    );

    FilterPersonByNameQuery query = new FilterPersonByNameQuery(
        receiver,
        attributes,
        Main.peopleList
    );

    // run the command
    invoker.setCommand(query);
    invoker.executeCommand();
    return receiver;
  }

  public static QueryResultPersonList filterByDate(FilterPersonsDTO filterPersonsDTO, QueryResultPersonList receiver) {
    //TODO
    FilterPersonByBirthQuery query = new FilterPersonByBirthQuery(
        receiver,
        null,
        Main.peopleList
    );

    invoker.setCommand(query);
    invoker.executeCommand();

    return receiver;
  }

  public static QueryResultPersonList getPersonChildren(FilterPersonsDTO filterPersonsDTO, QueryResultPersonList receiver) {
    // TODO change to root
    ChildrenQuery query = new ChildrenQuery(receiver, null);

    invoker.setCommand(query);
    invoker.executeCommand();

    return receiver;
  }

  public static QueryResultPersonList getGrandChildren(FilterPersonsDTO filterPersonsDTO, QueryResultPersonList receiver) {
    // TODO
    GrandChildrenQuery query = new GrandChildrenQuery(receiver, null);

    invoker.setCommand(query);
    invoker.executeCommand();

    return receiver;
  }

  public static QueryResultPersonList getGrandGrandChildren(FilterPersonsDTO filterPersonsDTO, QueryResultPersonList receiver) {
    // TODO
    GrandGrandChildrenQuery query = new GrandGrandChildrenQuery(receiver, null);

    invoker.setCommand(query);
    invoker.executeCommand();

    return receiver;
  }

}

