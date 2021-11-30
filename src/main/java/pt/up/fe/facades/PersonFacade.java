package pt.up.fe.facades;

import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import pt.up.fe.Main;
import pt.up.fe.dtos.persons.FilterPersonsDTO;
import pt.up.fe.person.Gender;
import pt.up.fe.person.Person;
import pt.up.fe.sources.Source;

public class PersonFacade {

  private Scanner scanner;
  private SourceFacade sourceFacade;

  public PersonFacade(Scanner scanner, SourceFacade sourceFacade) {
    this.scanner = scanner;
    this.sourceFacade = sourceFacade;
  }

  public Scanner getScanner() {
    return scanner;
  }

  public void setScanner(Scanner scanner) {
    this.scanner = scanner;
  }

  public SourceFacade getSourceFacade() {
    return sourceFacade;
  }

  public void setSourceFacade(SourceFacade sourceFacade) {
    this.sourceFacade = sourceFacade;
  }

  public Person createPerson() {
    System.out.println("A new person is being created...");
    Person person = new Person();
    enterPersonName(person);

    enterPersonGender(person);

    System.out.println("Do you want to add description? (Y to add)");
    if (this.getScanner().nextLine().equalsIgnoreCase("Y")) {
      enterPersonDescription(person);
    }

    System.out.println("Do you want to add source? (Y to add)");
    if (this.getScanner().nextLine().equalsIgnoreCase("Y")) {
      enterPersonSource(person);
    }

    return person;
  }

  public void enterPersonDescription(Person person) {
    System.out.println("Enter the description wanted.");
    String description = this.getScanner().nextLine();
    person.setDescription(description);
  }

  public void enterPersonName(Person person) {
    System.out.println("What is the the name of the person?");
    String name = this.getScanner().nextLine();
    person.setName(name);
  }

  public void enterPersonGender(Person person) {
    int gender = 0;
    List<Gender> genderTypes = List.of(Gender.values());
    while (gender < 1 || gender > genderTypes.size()) {
      System.out.println("What is the gender of the person?");
      genderTypes.forEach(type -> {
        System.out.println(String.format("%s - %s", genderTypes.indexOf(type) + 1, type));
      });
      gender = this.getScanner().nextInt();
      this.getScanner().nextLine();
    }
    person.setGender(Gender.valueOf(gender));
  }

  public void enterPersonSource(Person person) {
    int choiceSource = 0;

    if (Main.sourcesList.isEmpty()) {
      choiceSource = 2;
    }

    while (choiceSource != 1 && choiceSource != 2) {
      System.out.println("Do you want to use a existent source (1) or a new one (2)?");
      choiceSource = this.getScanner().nextInt();
      this.getScanner().nextLine();
    }
    if (choiceSource == 1) {
      this.getSourceFacade().displaySources();
      System.out.println("What is the source of the person? (if invalid is ignored)");
      int choiceSourcePerson = this.getScanner().nextInt() - 1;
      this.getScanner().nextLine();
      if (choiceSourcePerson > 0 && choiceSourcePerson < Main.sourcesList.size()) {
        person.setSource(Main.sourcesList.get(choiceSource));
      }

    } else {
      Source source = this.getSourceFacade().newSourceInstance();
      Main.sourcesList.add(source);
      person.setSource(source);
      System.out.println("Source added to person");
    }
  }

  public void editPerson(Person person) {
    editPersonLoop:
    while (true) {
      System.out.println(String.format("Editing Person:\n%s", person));
      System.out.println(
          "What do you want to edit?\n 0 - Leave \n 1 - Name\n 2 - Gender \n 3 - Description \n " +
              "4 - Source");
      int editSelection = this.getScanner().nextInt();
      this.getScanner().nextLine();

      switch (editSelection) {
        case 0:
          System.out.println("Leaving editing section...");
          break editPersonLoop;
        case 1:
          enterPersonName(person);
          break;
        case 2:
          enterPersonGender(person);
          break;
        case 3:
          enterPersonDescription(person);
          break;
        case 4:
          System.err.println("Not implemented yet.");
          break;
        default:
          System.err.println("Invalid input.\n");
      }
    }
  }

  public void displayPeople() {
    if (Main.peopleList.isEmpty()) {
      System.err.println("There are no people on the system.");
      return;
    }

    Main.peopleList.forEach(person -> {
      System.out.println(
          String.format("\nPerson nº%s:\n%s \n", Main.peopleList.indexOf(person) + 1, person));
    });

    System.out.println("Do you want to edit a person? (Y to edit)");
    if (this.getScanner().nextLine().equalsIgnoreCase("Y")) {
      int choicePerson = 0;
      while (choicePerson < 1 || choicePerson > Main.peopleList.size()) {
        System.out.println("Enter the person number.");
        choicePerson = this.getScanner().nextInt();
      }
      editPerson(Main.peopleList.get(choicePerson - 1));

    }

  }

  public static List<Person> filterPersons(FilterPersonsDTO filterPersonsDTO) {

    Predicate<Person> byFirstName = person -> filterPersonsDTO.getFirstName().isEmpty()
        || person.getName()
        .contains(filterPersonsDTO.getFirstName());
    Predicate<Person> byMiddleName = person -> filterPersonsDTO.getMiddleName().isEmpty()
        || person.getMiddleName()
        .contains(filterPersonsDTO.getMiddleName());
    Predicate<Person> byLastName = person -> filterPersonsDTO.getLastName().isEmpty()
        || person.getLastName()
        .contains(filterPersonsDTO.getLastName());

    List<Person> result = Main.peopleList.stream().filter(byFirstName).filter(byMiddleName)
        .filter(byLastName)
        .collect(Collectors.toList());

    return result;

  }

}

