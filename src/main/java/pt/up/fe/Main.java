package pt.up.fe;

import pt.up.fe.person.Gender;
import pt.up.fe.person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Person> peopleList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("What do you want to perform?\n 0 - Quit\n 1 - Record a new person \n....");
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
                default:
                    System.err.println("Invalid input.\n");
            }
        }

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
            while (choiceSource != 1 || choiceSource != 2) {
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
