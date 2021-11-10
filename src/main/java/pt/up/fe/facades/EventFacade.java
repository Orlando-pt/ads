package pt.up.fe.facades;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import pt.up.fe.person.Person;
import pt.up.fe.dates.IDate;
import pt.up.fe.events.Birth;
import pt.up.fe.events.CustomEvent;
import pt.up.fe.events.Death;
import pt.up.fe.events.Emigration;
import pt.up.fe.events.Event;
import pt.up.fe.events.Marriage;
import pt.up.fe.events.Residence;

public class EventFacade {
    public static Event createEvent(Scanner sc) {
        // Menu logic
        List<String> eventTypes = Arrays.asList("Birth", "Death", "Emigration", "Marriage", "Residence", "Custom");
        System.out.println("What type of event do you want to create?");
        eventTypes.forEach(type -> {
            System.out.println(String.format("%s - %s", eventTypes.indexOf(type), type));
        });
        int chosenEvent = sc.nextInt();
        sc.nextLine();

        if (chosenEvent < 0 || chosenEvent > eventTypes.toArray().length - 1) {
            System.err.println("Invalid input.\n");
            System.exit(0);
        }

        return newEventInstance(eventTypes.get(chosenEvent), sc);
    }

    public static Event newEventInstance(String event, Scanner sc) {
        switch (event.toLowerCase()) {
            case "birth":
                Event birthEvent = new Birth();
                return populateBirthEvent(birthEvent, sc);
            case "death":
                Event deathEvent = new Death();
                return deathEvent;
            case "emigration":
                Event emigrationEvent = new Emigration();
                return emigrationEvent;
            case "marriage":
                Event marriageEvent = new Marriage();
                return marriageEvent;
            case "residence":
                Event residenceEvent = new Residence();
                return residenceEvent;
            default:
                Event customEvent = new CustomEvent(event);
                return populateCustomEvent(customEvent, sc);
        }
    }

    public static Event populateBirthEvent(Event birthEvent, Scanner sc) {
        System.out.println("--- Birth Event ---");

        System.out.println("\nDo you want to add Maternity? (Y to add)");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.println("\nMaternity:");
            String maternity = sc.nextLine();
            birthEvent.addSpecialPurposeField("Maternity", maternity);
        }

        System.out.println("Do you want to add a Place of Birth? (Y to add)");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.println("\nPlace of Birth:");
            // TODO: List of possible places
            System.out.println("1 - Lisboa");
            int placeIndex = sc.nextInt();
            sc.nextLine();
            birthEvent.addPlaceRelation("Place of Birth", PlaceFacade.choosePlace());
        }

        System.out.println("Do you want to add a Date of Birth? (Y to add)");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.println("\nDate of Birth:");
            IDate dateOfBirth = DateFacade.createDate(sc);
            sc.nextLine();
            birthEvent.setDate(dateOfBirth);
        }

        // TODO: Function that list all the people and returns the chosen person
        System.out.println("Do you want to add a Mother? (Y to add)");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            birthEvent.addPeopleRelation("Mother", createPerson("Mother", sc));
        }

        System.out.println("Do you want to add a Father? (Y to add)");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            birthEvent.addPeopleRelation("Father", createPerson("Father", sc));
        }

        System.out.println("Do you want to add more People? (Y to add)");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            Boolean peopleCounter = true;
            while (peopleCounter) {

                System.out.println("What's the relation with the person?");
                String relation = sc.nextLine();
                birthEvent.addPeopleRelation(relation, createPerson(relation, sc));

                System.out.println("\n--- Do you wanna continue (Y) or wanna stop (N)? ---");
                peopleCounter = sc.nextLine().equalsIgnoreCase("Y");
            }
        }

        System.out.println("Do you want to add more Fields? (Y to add)");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            Boolean counter = true;
            while (counter) {
                System.out.println("\nWhat kind of field you want to create?");
                System.out.println("Key: ");
                String key = sc.nextLine();
                System.out.println("Value:");
                String value = sc.nextLine();
                birthEvent.addSpecialPurposeField(key, value);

                System.out.println("\n--- Do you wanna continue (Y) or wanna stop (N)? ---");
                counter = sc.nextLine().equalsIgnoreCase("Y");
            }
        }

        System.out.println("Do you want to add a Description? (Y to add)");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.println("\nDescription: ");
            birthEvent.setDescription(sc.nextLine());
        }


        return birthEvent;
    }

    public static Event populateCustomEvent(Event customEvent, Scanner sc) {
        sc.nextLine();
        System.out.println("--- Custom Event ---");
        System.out.println("\nNote: You have to define they key values that you want.");

        Boolean counter = true;
        while (counter) {
            System.out.println("\nWhat kind of field you want to create?");
            System.out.println("Key: ");
            String key = sc.nextLine();
            System.out.println("Value:");
            String value = sc.nextLine();
            customEvent.addSpecialPurposeField(key, value);

            System.out.println("\n--- Do you wanna continue (Y) or wanna stop (N)? ---");
            counter = sc.nextLine().equalsIgnoreCase("Y");
        }

        return customEvent;
    }

    public static Person createPerson(String field, Scanner sc) {
        Person cur = new Person();
        System.out.printf("\nName of %s: ", field);
        cur.setName(sc.nextLine());
        return cur;
    }
}
