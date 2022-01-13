package pt.up.fe.facades;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;
import pt.up.fe.Main;
import pt.up.fe.dates.IntervalDate;
import pt.up.fe.dtos.persons.FilterPersonType;
import pt.up.fe.dtos.persons.FilterPersonsDTO;
import pt.up.fe.dtos.persons.PersonDTO;
import pt.up.fe.exports.DotExport;
import pt.up.fe.person.Person;
import pt.up.fe.queries.ChildrenQuery;
import pt.up.fe.queries.DateAttribute;
import pt.up.fe.queries.DateQueryTypeEnum;
import pt.up.fe.queries.FilterPersonByBirthQuery;
import pt.up.fe.queries.FilterPersonByNameQuery;
import pt.up.fe.queries.GrandChildrenQuery;
import pt.up.fe.queries.GrandGrandChildrenQuery;
import pt.up.fe.queries.NameAttribute;
import pt.up.fe.queries.QueryCommand;
import pt.up.fe.queries.QueryInvoker;
import pt.up.fe.queries.QueryMemento;
import pt.up.fe.queries.QueryMementoCaretaker;
import pt.up.fe.queries.QueryResultPersonList;
import pt.up.fe.queries.SpecifiedPersonAttributes;


public class PersonFacade {

  private static QueryMementoCaretaker caretaker = new QueryMementoCaretaker();
  private static QueryInvoker invoker = new QueryInvoker(caretaker);

  private static DotExport dotExporter = new DotExport(null);

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

    switch (filterPersonsDTO.getFilterPersonType()) {
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

  public static List<ImmutablePair<String, Integer>> queryHistoryMemento() {
    List<ImmutablePair<String, Integer>> queryHistory = new ArrayList<>();

    List<QueryMemento> list = caretaker.getCommandsHistory();

    for (int i = 0; i < list.size(); i++) {
      queryHistory.add(
          new ImmutablePair<String, Integer>(list.get(i).getQuery().toString(), i));
    }

    return queryHistory;
  }

  public static List<Person> executeQueryHistory(int index) {
    QueryResultPersonList resultReceiver = new QueryResultPersonList();

    caretaker.restoreCommand(index);
    QueryCommand command = invoker.getCurrentCommand();
    command.setReceiver(resultReceiver);

    invoker.executeCommand();
    return resultReceiver.getPersonList();
  }

  public static QueryResultPersonList filterByNames(FilterPersonsDTO filterPersonDto,
      QueryResultPersonList receiver) {

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

  public static QueryResultPersonList filterByDate(FilterPersonsDTO filterPersonsDTO,
      QueryResultPersonList receiver) {
    DateAttribute date;

    if (filterPersonsDTO.getStartDate() == null && filterPersonsDTO.getEndDate() == null) {
      filterPersonsDTO.setFilterPersonType(FilterPersonType.NAMES);
      return filterByNames(filterPersonsDTO, receiver);
    }

    if (!filterPersonsDTO.getStartDate().isEmpty() && !filterPersonsDTO.getEndDate().isEmpty())
    // it means it was requested a interval
    {
      date = new DateAttribute(
          new IntervalDate(
              filterPersonsDTO.getStartDate(),
              filterPersonsDTO.getEndDate()
          ), DateQueryTypeEnum.CONTAINS
      );
    } else if (!filterPersonsDTO.getStartDate().isEmpty())
    // check all dates after start date
    {
      date = new DateAttribute(
          filterPersonsDTO.getStartDate(),
          DateQueryTypeEnum.AFTER
      );
    } else
    // check all dates before end date
    {
      date = new DateAttribute(
          filterPersonsDTO.getEndDate(),
          DateQueryTypeEnum.BEFORE
      );
    }

    FilterPersonByBirthQuery query = new FilterPersonByBirthQuery(
        receiver,
        date,
        Main.peopleList
    );

    invoker.setCommand(query);
    invoker.executeCommand();

    return receiver;
  }

  public static QueryResultPersonList getPersonChildren(FilterPersonsDTO filterPersonsDTO,
      QueryResultPersonList receiver) {
    ChildrenQuery query = new ChildrenQuery(receiver, filterPersonsDTO.getPerson());

    invoker.setCommand(query);
    invoker.executeCommand();

    return receiver;
  }

  public static QueryResultPersonList getGrandChildren(FilterPersonsDTO filterPersonsDTO,
      QueryResultPersonList receiver) {
    GrandChildrenQuery query = new GrandChildrenQuery(receiver, filterPersonsDTO.getPerson());

    invoker.setCommand(query);
    invoker.executeCommand();

    return receiver;
  }

  public static QueryResultPersonList getGrandGrandChildren(FilterPersonsDTO filterPersonsDTO,
      QueryResultPersonList receiver) {
    GrandGrandChildrenQuery query = new GrandGrandChildrenQuery(receiver,
        filterPersonsDTO.getPerson());

    invoker.setCommand(query);
    invoker.executeCommand();

    return receiver;
  }

  public static void createFamilyTreeVisualization(Person root, String filename) throws Exception {
    dotExporter.createGraphHavingRoot(root, filename);
  }

}
