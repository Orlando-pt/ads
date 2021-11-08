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
            System.out.println("What do you want to perform?\n 0 - Quit\n 1 - Record a new person \n 2 - Display people on the system...");
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
                    displayPeople(sc, peopleList);
                    break;
                default:
                    System.err.println("Invalid input.\n");
            }
        }

    }

    public static void displayPeople(Scanner sc, List<Person> peopleList) {
        int numberPerson = 1;
        for (Person p : peopleList) {
            System.out.println(String.format("Person %d: \n %s", numberPerson++, p));
        }

        System.out.println("Do you want to edit a person? (Y to edit)");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            int choicePerson = 0;
            while (choicePerson < 1 || choicePerson < numberPerson) {
                System.out.println("Enter the person number.");
                choicePerson = sc.nextInt();
            }
            editPerson(sc, peopleList.get(choicePerson - 1));

        }

    }

    public static void editPerson(Scanner sc, Person person) {
        while (true) {
            System.out.println(String.format("Editing Person: \n %s", person));
            System.out.println("What do you want to edit?\n 0 - Name\n 1 - Gender \n 2 - Description \n 3 - Source");
            int editSelection = sc.nextInt();

            switch (editSelection) {
                case 0:
                    enterPersonName(sc, person);
                    break;
                case 1:
                    enterPersonGender(sc, person);
                    break;
                case 2:
                    enterPersonDescription(sc, person);
                    break;
                case 3:
                    System.err.println("Not implemented yet.");
                    break;
                default:
                    System.err.println("Invalid input.\n");
            }
        }
    }

    public static void enterPersonDescription(Scanner sc, Person person){
        System.out.println("Enter the description wanted.");
        String description = sc.nextLine();
        person.setDescription(description);
    }

    public static void enterPersonName(Scanner sc, Person person){
        System.out.println("What is the the name of the person?");
        String name = sc.nextLine();
        person.setName(name);
    }

    public static void enterPersonGender(Scanner sc, Person person){
        int gender = 0;
        while (gender != 1 || gender != 2) {
            System.out.println("What is the gender of the person? \n 1 - Male \n 2 - Female");
            gender = sc.nextInt();
        }
        person.setGender(Gender.valueOf(gender));
    }

    public static void enterPersonSource(Scanner sc, Person person){
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

    public static Person createPerson(Scanner sc) {
        System.out.println("A new person if being created...");
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
}
