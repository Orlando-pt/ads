package pt.up.fe.facades;

import java.util.List;
import java.util.Scanner;

import pt.up.fe.person.Gender;
import pt.up.fe.person.Person;

public class PersonFacade {
    public static Person createPerson(Scanner sc) {
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
            enterPersonSource(sc, person);
        }

        return person;
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
        while (gender < 1 || gender > genderTypes.size()) {
            System.out.println("What is the gender of the person?");
            genderTypes.forEach(type -> {
                System.out.println(String.format("%s - %s", genderTypes.indexOf(type) + 1, type));
            });
            gender = sc.nextInt();
            sc.nextLine();
        }
        person.setGender(Gender.valueOf(gender));
    }

    public static void enterPersonSource(Scanner sc, Person person) {
        int choiceSource = 0;
        while (choiceSource != 1 && choiceSource != 2) {
            System.out.println("Do you want to use a existent source (1) or a new one (2)?");
            choiceSource = sc.nextInt();
        }
        if (choiceSource == 1) {
            // TODO Use Listing Function
        } else {
            // TODO Use Create Function
        }
    }

    public static void editPerson(Scanner sc, Person person) {
        editPersonLoop:
        while (true) {
            System.out.println(String.format("Editing Person:\n%s", person));
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

    public static void displayPeople(Scanner sc, List<Person> peopleList) {
        if(peopleList.isEmpty()){
            System.err.println("There are no people on the system.");
            return;
        }

        peopleList.forEach(person -> {
            System.out.println(String.format("\nPerson nº%s:\n%s \n", peopleList.indexOf(person) + 1, person));
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
}
