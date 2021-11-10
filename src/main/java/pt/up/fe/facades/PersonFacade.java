package pt.up.fe.facades;

import java.util.List;
import java.util.Scanner;

import pt.up.fe.person.Gender;
import pt.up.fe.person.Person;

public class PersonFacade {
    public static Person createPerson(Scanner sc) {
        System.out.println("A new person if being created...");
        Person person = new Person();

        String name = new String();

        while (name.isEmpty()) {
            System.out.println("What is the the name of the person?");
            name = sc.nextLine();
        }
        person.setName(name);

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
                sc.nextLine();
            }
            if (choiceSource == 1) {
                // TODO Use Listing Function
            } else {
                // TODO Use Create Function
            }
        }

        return person;
    }
}
