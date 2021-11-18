package pt.up.fe;

import pt.up.fe.events.Event;
import pt.up.fe.facades.*;
import pt.up.fe.person.Person;
import pt.up.fe.sources.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    public static List<Source> sourcesList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PlaceFacade placeFacade = new PlaceFacade(sc);
        DateFacade dateFacade = new DateFacade(sc);
        EventFacade eventFacade = new EventFacade(placeFacade, dateFacade, sc);
        SourceFacade sourceFacade = new SourceFacade(sc);
        PersonFacade personFacade = new PersonFacade(sc, sourceFacade);
        while (true) {
            System.out.println("What do you want to perform?\n 0 - Quit\n 1 - Create Person" +
                    "\n 2 - List people\n 3 - Create Event\n 4 - Create Source\n 5 - List Sources");

            int selection = sc.nextInt();
            sc.nextLine();

            switch (selection) {
                case 0:
                    System.err.println("Bye bye, have a nice day.\n");
                    System.exit(0);
                    break;
                case 1:
                    Person newPerson = personFacade.createPerson();
                    System.out.println("You've created a new Person:\n" + newPerson);
                    break;
                case 2:
                    personFacade.displayPeople();
                    break;
                case 3:
                    Event newEvent = eventFacade.createEvent();
                    System.out.println(newEvent.toString());
                    break;
                case 4:
                    Source newSource = sourceFacade.newSourceInstance();
                    System.out.println(newSource.toString());
                    sourcesList.add(newSource);
                    break;
                case 5:
                    sourceFacade.displaySources();
                    break;
                default:
                    System.err.println("Invalid input.\n");
            }
        }
    }
}
