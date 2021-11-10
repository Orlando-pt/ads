package pt.up.fe;

import pt.up.fe.person.Gender;
import pt.up.fe.person.Person;

import pt.up.fe.dates.IBuilder;
import pt.up.fe.dates.IDate;
import pt.up.fe.dates.SimpleDateBuilder;

import pt.up.fe.events.*;

import pt.up.fe.places.Place;
import pt.up.fe.places.PlaceBuilder;
import pt.up.fe.sources.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        List<Person> peopleList = new ArrayList<>();
        List<Source> sourcesList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {

            System.out.println("What do you want to perform?\n 0 - Quit\n 1 - Create Person " +
                    "\n 2 - List people \n  3 - Create Event \n 4 - Create Source \n 5 - List Sources");

            int selection = sc.nextInt();
            sc.nextLine();

            switch (selection) {
                case 0:
                    System.err.println("Bye bye, have a nice day.\n");
                    System.exit(0);
                    break;
                case 1:
                    Person newPerson = createPerson(sc, sourcesList);
                    peopleList.add(newPerson);
                    System.out.println("You've created a new Person:\n" + newPerson);
                    break;
                case 2:
                    displayPeople(sc, peopleList);
                    break;
                case 3:
                    Event newEvent = createEvent(sc);
                    System.out.println(newEvent.toString());
                    break;
                case 4:
                    Source newSource = createSource(sc);
                    System.out.println(newSource.toString());
                    break;
                case 5:
                    displaySources(sc, sourcesList);
                    break;
                default:
                    System.err.println("Invalid input.\n");
            }
        }
    }

    public static void displaySources(Scanner sc, List<Source> sourcesList) {
        sourcesList.forEach(source -> {
            System.out.println(String.format("%s - %s \n", sourcesList.indexOf(source) + 1, source));
        });
    }

    public static Source createSource(Scanner sc) {
        // Menu logic
        List<String> sourceTypes = Arrays.asList("Book", "HistoricalRecord", "OnlineResource", "OrallyTransmitted", "Custom");
        System.out.println("What type of source do you want to create?");
        sourceTypes.forEach(type -> {
            System.out.println(String.format("%s - %s", sourceTypes.indexOf(type), type));
        });
        int chosenSource = sc.nextInt();

        if (chosenSource < 0 || chosenSource > sourceTypes.toArray().length - 1) {
            System.err.println("Invalid input.\n");
            System.exit(0);
        }

        Source newSource = newSourceInstance(sourceTypes.get(chosenSource), sc);

        return newSource;
    }

    public static Source newSourceInstance(String source, Scanner sc) {
        System.out.println("What is the the name of the source?");
        String name = sc.nextLine();

        switch (source.toLowerCase()) {
            case "book":
                return new Book(name);
            case "historicalrecord":
                return new HistoricalRecord(name);
            case "onlineresource":
                OnlineResource onlineResource = new OnlineResource(name);
                return populateOnlineResource(onlineResource, sc);
            case "orallytransmitted":
                return new OrallyTransmitted(name);
            default:
                return new CustomSource(name);
        }
    }

    public static OnlineResource populateOnlineResource(OnlineResource onlineResource, Scanner sc) {
        System.out.println("--- Online Resource Source ---");

        while (true) {
            System.out.println("Do you want to add authors? (Y to add)");
            if (!sc.nextLine().equalsIgnoreCase("Y"))
                break;

            System.out.println("\nInsert Author:");
            onlineResource.addAuthor(sc.nextLine());
        }

        System.out.println("Do you want to add link? (Y to add)");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.println("\nInsert Link:");
            onlineResource.setLink(sc.nextLine());
        }

        return onlineResource;
    }


    public static Event createEvent(Scanner sc) {
        // Menu logic
        List<String> eventTypes = Arrays.asList("Birth", "Death", "Emigration", "Marriage", "Residence", "Custom");
        System.out.println("What type of event do you want to create?");
        eventTypes.forEach(type -> {
            System.out.println(String.format("%s - %s", eventTypes.indexOf(type), type));
        });
        int chosenEvent = sc.nextInt();

        if (chosenEvent < 0 || chosenEvent > eventTypes.toArray().length - 1) {
            System.err.println("Invalid input.\n");
            System.exit(0);
        }

        return newEventInstance(eventTypes.get(chosenEvent), sc);
    }

    public static Event newEventInstance(String event, Scanner sc) {
        switch (event.toLowerCase()) {
            case "birth":
                EventCreator birthCreator = new BirthCreator();
                return populateBirthEvent(birthCreator.createEvent(), sc);
            case "death":
                EventCreator deathCreator = new DeathCreator();
                return deathCreator.createEvent();
            case "emigration":
                EventCreator emigrationCreator = new EmigrationCreator();
                return emigrationCreator.createEvent();
            case "marriage":
                EventCreator marriageCreator = new MarriageCreator();
                return marriageCreator.createEvent();
            case "residence":
                EventCreator residenceCreator = new ResidenceCreator();
                return residenceCreator.createEvent();
            default:
                EventCreator customCreator = new CustomCreator(event);
                return populateCustomEvent(customCreator.createEvent(), sc);
        }
    }

    public static Event populateBirthEvent(Event birthEvent, Scanner sc) {
        System.out.println("--- Birth Event ---");
        System.out.println("\nMaternity:");
        String maternity = sc.nextLine() + sc.nextLine();
        birthEvent.addSpecialPurposeField("Maternity", maternity);

        System.out.println("\nPlace of Birth:");
        // TODO: List of possible places
        System.out.println("1 - Lisboa");
        int placeIndex = sc.nextInt();
        birthEvent.addPlaceRelation("Place of Birth", choosePlace());

        System.out.println("\nDate of Birth:");
        IDate dateOfBirth = createDate(sc);
        birthEvent.setDate(dateOfBirth);

        // TODO: Function that list all the people and returns the chosen person
        System.out.println("\nMother:");
        Person mother = new Person();
        System.out.println("Name of Mother: ");
        mother.setName(sc.nextLine() + sc.nextLine());
        birthEvent.addPeopleRelation("Mother", mother);
        System.out.println("\nFather:");
        Person father = new Person();
        System.out.println("Name of Father: ");
        father.setName(sc.nextLine());
        birthEvent.addPeopleRelation("Father", father);

        System.out.println("Description: ");
        birthEvent.setDescription(sc.nextLine());

        return birthEvent;
    }

    public static Event populateCustomEvent(Event customEvent, Scanner sc) {
        sc.nextLine();
        System.out.println("--- Custom Event ---");
        System.out.println("\nNote: You have to define they key values that you want.");

        Boolean counter = true;
        while (counter) {
            System.out.println("\nWhat kind of date you want to create?");
            System.out.println("Key: ");
            String key = sc.nextLine();
            System.out.println("Value");
            String value = sc.nextLine();
            customEvent.addSpecialPurposeField(key, value);

            System.out.println("\n--- Do you wanna continue (Y) or wanna stop (N)? ---");
            counter = sc.nextLine().equalsIgnoreCase("Y");
        }

        return customEvent;
    }

    public static Place choosePlace() {
        PlaceBuilder builder = new PlaceBuilder("Portugal");

        return builder
                .startCompound("Lisboa")
                .startCompound("Lisboa")
                .addParish("Benfica")
                .addParish("Parque das Nações")
                .endCompound()
                .startCompound("Sintra")
                .addParish("Mem Martins")
                .addParish("Mercês")
                .endCompound()
                .endCompound()
                .getResult();
    }

    public static IDate createDate(Scanner sc) {
        int typeOfDate = 0;
        while (typeOfDate != 1 && typeOfDate != 2) {
            System.out.println("What kind of date you want to create?");
            System.out.println("1 - Simple Date");
            System.out.println("2 - Interval Date");
            typeOfDate = sc.nextInt();
        }

        if (typeOfDate == 1) {
            IBuilder dateBuilder = new SimpleDateBuilder().reset();

            System.out.println("--- Simple Date constructor ---");
            System.out.println("Year: ");
            dateBuilder.setYear(sc.nextInt());
            System.out.println("Month: ");
            dateBuilder.setMonth(sc.nextInt());
            System.out.println("Day: ");
            dateBuilder.setDay(sc.nextInt());
            System.out.println("Hour: ");
            dateBuilder.setHour(sc.nextInt());
            System.out.println("Minute: ");
            dateBuilder.setMinute(sc.nextInt());
            System.out.println("Second: ");
            dateBuilder.setSecond(sc.nextInt());
            System.out.println("... \n");

            return dateBuilder.build();
        }

        // TODO: Implement interval date, create function with above logic and reuse twice

        return null;
    }

    public static void displayPeople(Scanner sc, List<Person> peopleList) {
        peopleList.forEach(person -> {
            System.out.println(String.format("%s - %s \n", peopleList.indexOf(person) + 1, person));
        });

        System.out.println("Do you want to edit a person? (Y to edit)");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            int choicePerson = 0;
            while (choicePerson < 1 || choicePerson > peopleList.size()) {
                System.out.println("Enter the person number.");
                choicePerson = sc.nextInt();
            }
            editPerson(sc, peopleList.get(choicePerson - 1));

        }

    }

    public static void editPerson(Scanner sc, Person person) {
        editPersonLoop:
        while (true) {
            System.out.println(String.format("Editing Person: \n %s", person));
            System.out.println("What do you want to edit?\n 0 - Leave \n 1 - Name\n 2 - Gender \n 3 - Description \n " +
                    "4 - Source");
            int editSelection = sc.nextInt();
            sc.nextLine();

            switch (editSelection) {
                case 0:
                    System.out.println("Leaving editing section...");
                    break editPersonLoop;
                case 1:
                    enterPersonName(sc, person);
                    break;
                case 2:
                    enterPersonGender(sc, person);
                    break;
                case 3:
                    enterPersonDescription(sc, person);
                    break;
                case 4:
                    System.err.println("Not implemented yet.");
                    break;
                default:
                    System.err.println("Invalid input.\n");
            }
        }
    }

    public static void enterPersonDescription(Scanner sc, Person person) {
        System.out.println("Enter the description wanted.");
        String description = sc.nextLine();
        person.setDescription(description);
    }

    public static void enterPersonName(Scanner sc, Person person) {
        System.out.println("What is the the name of the person?");
        String name = sc.nextLine();
        person.setName(name);
    }

    public static void enterPersonGender(Scanner sc, Person person) {
        int gender = 0;
        List<Gender> genderTypes = List.of(Gender.values());
        while (gender < 1 && gender > genderTypes.size()) {
            System.out.println("What is the gender of the person?");
            genderTypes.forEach(type -> {
                System.out.println(String.format("\n %s - %s", genderTypes.indexOf(type) + 1, type));
            });
            gender = sc.nextInt();
            sc.nextLine();
        }
        person.setGender(Gender.valueOf(gender));
    }

    public static void enterPersonSource(Scanner sc, Person person, List<Source> sourcesList) {
        int choiceSource = 0;
        while (choiceSource != 1 && choiceSource != 2) {
            System.out.println("Do you want to use a existent source (1) or a new one (2)?");
            choiceSource = sc.nextInt();
            sc.nextLine();
        }
        if (choiceSource == 1) {
            displaySources(sc, sourcesList);
            System.out.println("What is the source of the person? (if invalid is ignored)");
            int choiceSourcePerson = sc.nextInt() - 1;
            sc.nextLine();
            if (choiceSourcePerson > 0 && choiceSourcePerson < sourcesList.size()) {
                person.setSource(sourcesList.get(choiceSource));
            }

        } else {
            Source source = createSource(sc);
            person.setSource(source);
            System.out.println("Source added to person");
        }
    }

    public static Person createPerson(Scanner sc, List<Source> sourcesList) {
        System.out.println("A new person is being created...");
        Person person = new Person();
        enterPersonName(sc, person);

        enterPersonGender(sc, person);

        System.out.println("Do you want to add description? (Y to add)");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            enterPersonDescription(sc, person);
        }

        System.out.println("Do you want to add source? (Y to add)");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            enterPersonSource(sc, person, sourcesList);
        }

        return person;
    }
}
