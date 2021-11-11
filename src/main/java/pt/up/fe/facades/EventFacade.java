package pt.up.fe.facades;

import java.util.Arrays;
import java.util.List;

import pt.up.fe.Main;
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
    public static Event createEvent() {
        // Menu logic
        List<String> eventTypes = Arrays.asList("Birth", "Death", "Emigration", "Marriage", "Residence", "Custom");
        System.out.println("What type of event do you want to create?");
        eventTypes.forEach(type -> {
            System.out.println(String.format("%s - %s", eventTypes.indexOf(type), type));
        });
        int chosenEvent = Main.sc.nextInt();

        if (chosenEvent < 0 || chosenEvent > eventTypes.toArray().length - 1) {
            System.err.println("Invalid input.\n");
            System.exit(0);
        }

        return newEventInstance(eventTypes.get(chosenEvent));
    }
    
    public static Event newEventInstance(String event) {
        switch (event.toLowerCase()) {
            case "birth":
                Event birthEvent = new Birth();
                return populateBirthEvent(birthEvent);
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
                return populateCustomEvent(customEvent);
        }
    }

    public static Event populateBirthEvent(Event birthEvent) {
        System.out.println("--- Birth Event ---");
        System.out.println("\nMaternity:");
        String maternity = Main.sc.nextLine() + Main.sc.nextLine();
        birthEvent.addSpecialPurposeField("Maternity", maternity);

        System.out.println("\nPlace of Birth:");
        // TODO: List of possible places
        System.out.println("1 - Lisboa");
        int placeIndex = Main.sc.nextInt();
        birthEvent.addPlaceRelation("Place of Birth", PlaceFacade.choosePlace());

        System.out.println("\nDate of Birth:");
        IDate dateOfBirth = DateFacade.createDate();
        birthEvent.setDate(dateOfBirth);

        // TODO: Function that list all the people and returns the chosen person
        System.out.println("\nMother:");
        Person mother = new Person();
        System.out.println("Name of Mother: ");
        mother.setName(Main.sc.nextLine() + Main.sc.nextLine());
        birthEvent.addPeopleRelation("Mother", mother);
        System.out.println("\nFather:");
        Person father = new Person();
        System.out.println("Name of Father: ");
        father.setName(Main.sc.nextLine());
        birthEvent.addPeopleRelation("Father", father);

        System.out.println("Description: ");
        birthEvent.setDescription(Main.sc.nextLine());

        return birthEvent;
    }
    
    public static Event populateCustomEvent(Event customEvent) {
        Main.sc.nextLine();
        System.out.println("--- Custom Event ---");
        System.out.println("\nNote: You have to define they key values that you want.");

        Boolean counter = true;
        while(counter) {
            System.out.println("\nWhat kind of date you want to create?");
            System.out.println("Key: ");
            String key = Main.sc.nextLine();
            System.out.println("Value");
            String value = Main.sc.nextLine();
            customEvent.addSpecialPurposeField(key, value);

            System.out.println("\n--- Do you wanna continue (Y) or wanna stop (N)? ---");
            counter = Main.sc.nextLine().equalsIgnoreCase("Y");
        }

        return customEvent;
    }
}
