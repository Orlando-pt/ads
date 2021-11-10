package pt.up.fe;

import pt.up.fe.person.Gender;
import pt.up.fe.person.Person;

import pt.up.fe.dates.IBuilder;
import pt.up.fe.dates.IDate;
import pt.up.fe.dates.SimpleDateBuilder;

import pt.up.fe.events.*;

import pt.up.fe.places.Place;
import pt.up.fe.places.PlaceBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        List<Person> peopleList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("What do you want to perform?\n 0 - Quit\n 1 - Record a new person \n 2 - Create Event....");

            int selection = sc.nextInt();

            switch (selection) {
                case 0:
                    System.err.println("Bye bye, have a nice day.\n");
                    System.exit(0);
                    break;
                case 1:
                    Person newPerson = createPerson(sc);
                    peopleList.add(newPerson);

                    break;
                case 2:
                    Event newEvent = createEvent(sc);
                    System.out.println(newEvent.toString());

                    break;
                default:
                    System.err.println("Invalid input.\n");
            }
        }
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
        while(counter) {
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
        while(typeOfDate != 1 && typeOfDate != 2) {
            System.out.println("What kind of date you want to create?");
            System.out.println("1 - Simple Date");
            System.out.println("2 - Interval Date");
            typeOfDate = sc.nextInt();
        }

        if(typeOfDate == 1) {
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

    public static Person createPerson(Scanner sc) {
        System.out.println("A new person if being created...");
        Person person = new Person();
        System.out.println("What is the the name of the person?");
        String name = sc.nextLine();
        person.setName(name);

        int gender = 0;
        while (gender != 1 || gender != 2) {
            System.out.println("What is the gender of the person? \n 1 - Male \n 2 - Female");
            gender = sc.nextInt();
        }
        person.setGender(Gender.valueOf(gender));

        System.out.println("Do you want to add description? (Y to add)");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.println("Enter the description wanted.");
            String description = sc.nextLine();
            person.setDescription(description);
        }

        System.out.println("Do you want to add source? (Y to add)");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            int choiceSource = 0;
            while (choiceSource != 1 && choiceSource != 2) {
                System.out.println("Do you want to use a existent source (1) or a new one (2)?");
                choiceSource = sc.nextInt();
            }
            if (choiceSource == 1) {
                // Use Listing Function
            } else {
                // Use Create Function
            }
        }

        return person;
    }
}
