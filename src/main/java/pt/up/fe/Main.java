package pt.up.fe;

import pt.up.fe.events.Event;
import pt.up.fe.facades.EventFacade;
import pt.up.fe.facades.PersonFacade;
import pt.up.fe.person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Person> peopleList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {

            System.out.println("\nWhat do you want to perform?\n 0 - Quit\n 1 - Record a new person " +
                    "\n 2 - Display people on the system\n 3 - Create Event");

            int selection = sc.nextInt();
            sc.nextLine();

            switch (selection) {
                case 0:
                    System.err.println("Bye bye, have a nice day.\n");
                    System.exit(0);
                    break;
                case 1:
                    Person newPerson = PersonFacade.createPerson(sc);
                    peopleList.add(newPerson);
                    System.out.println("You've created a new Person:\n" + newPerson);
                    break;
                case 2:
                    PersonFacade.displayPeople(sc, peopleList);
                    break;
                case 3:
                    Event newEvent = EventFacade.createEvent(sc);
                    System.out.println(newEvent.toString());
                    break;
                default:
                    System.err.println("Invalid input.\n");
            }
        }
    }
}
