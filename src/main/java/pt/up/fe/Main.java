package pt.up.fe;

import pt.up.fe.dates.IDate;
import pt.up.fe.events.*;
import pt.up.fe.places.Place;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("What do you want to perform?\n 0 - Quit\n 1 - Create Event \n....");
            int selection = sc.nextInt();

            switch (selection) {
                case 0:
                    System.err.println ("Bye bye, have a nice day.\n");
                    System.exit(0);
                    break;
                case 1:
                    // Menu logic
                    List<String> eventTypes = Arrays.asList("Birth", "Death", "Emigration", "Marriage", "Residence", "Custom");
                    System.out.println("What type of event do you want to create?");
                    eventTypes.forEach(type -> {
                        System.out.println(String.format("%s - %s", eventTypes.indexOf(type), type));
                    });
                    int chosenEvent = sc.nextInt();

                    if(chosenEvent < 0 || chosenEvent > eventTypes.toArray().length - 1) {
                        System.err.println("Invalid input.\n");
                        System.exit(0);
                    }

                    Event newEvent = newEventInstance(eventTypes.get(chosenEvent), null, null);

                    System.out.println(newEvent.getName());
                    break;
                default:
                    System.err.println("Invalid input.\n");
            }
        }
    }

    public static Event newEventInstance(String event, Place place, IDate date) {
        switch (event.toLowerCase()) {
            case "birth":
                EventCreator birthCreator = new BirthCreator();
                return  birthCreator.createEvent(place, date);
            case "death":
                EventCreator deathCreator = new DeathCreator();
                return deathCreator.createEvent(place, date);
            case "emigration":
                EventCreator emigrationCreator = new EmigrationCreator();
                return emigrationCreator.createEvent(place, date);
            case "marriage":
                EventCreator marriageCreator = new MarriageCreator();
                return marriageCreator.createEvent(place, date);
            case "residence":
                EventCreator residenceCreator = new ResidenceCreator();
                return residenceCreator.createEvent(place, date);
            default:
                EventCreator customCreator = new CustomCreator(event);
                return customCreator.createEvent(place, date);
        }
    }
}
