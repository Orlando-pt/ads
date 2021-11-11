package pt.up.fe.facades;

import java.util.List;

import pt.up.fe.Main;
import pt.up.fe.person.Gender;
import pt.up.fe.person.Person;
import pt.up.fe.sources.Source;

public class PersonFacade {
    public static Person createPerson() {
        System.out.println("A new person is being created...");
        Person person = new Person();
        enterPersonName(person);

        enterPersonGender(person);

        System.out.println("Do you want to add deMain.scription? (Y to add)");
        if (Main.sc.nextLine().equalsIgnoreCase("Y")) {
            enterPersonDescription(person);
        }

        System.out.println("Do you want to add source? (Y to add)");
        if (Main.sc.nextLine().equalsIgnoreCase("Y")) {
            enterPersonSource(person);
        }

        return person;
    }

    public static void enterPersonDescription(Person person) {
        System.out.println("Enter the deMain.scription wanted.");
        String description = Main.sc.nextLine();
        person.setDescription(description);
    }

    public static void enterPersonName(Person person) {
        System.out.println("What is the the name of the person?");
        String name = Main.sc.nextLine();
        person.setName(name);
    }

    public static void enterPersonGender(Person person) {
        int gender = 0;
        List<Gender> genderTypes = List.of(Gender.values());
        while (gender < 1 || gender > genderTypes.size()) {
            System.out.println("What is the gender of the person?");
            genderTypes.forEach(type -> {
                System.out.println(String.format("%s - %s", genderTypes.indexOf(type) + 1, type));
            });
            gender = Main.sc.nextInt();
            Main.sc.nextLine();
        }
        person.setGender(Gender.valueOf(gender));
    }

    public static void enterPersonSource(Person person) {
        int choiceSource = 0;
        while (choiceSource != 1 && choiceSource != 2) {
            System.out.println("Do you want to use a existent source (1) or a new one (2)?");
            choiceSource = Main.sc.nextInt();
            Main.sc.nextLine();
        }
        if (choiceSource == 1) {
            SourceFacade.displaySources();
            System.out.println("What is the source of the person? (if invalid is ignored)");
            int choiceSourcePerson = Main.sc.nextInt() - 1;
            Main.sc.nextLine();
            if (choiceSourcePerson > 0 && choiceSourcePerson < Main.sourcesList.size()) {
                person.setSource(Main.sourcesList.get(choiceSource));
            }

        } else {
            Source source = SourceFacade.newSourceInstance();
            Main.sourcesList.add(source);
            person.setSource(source);
            System.out.println("Source added to person");
        }
    }

    public static void editPerson(Person person) {
        editPersonLoop:
        while (true) {
            System.out.println(String.format("Editing Person:\n%s", person));
            System.out.println("What do you want to edit?\n 0 - Leave \n 1 - Name\n 2 - Gender \n 3 - DeMain.scription \n " +
                    "4 - Source");
            int editSelection = Main.sc.nextInt();
            Main.sc.nextLine();

            switch (editSelection) {
                case 0:
                    System.out.println("Leaving editing section...");
                    break editPersonLoop;
                case 1:
                    enterPersonName(person);
                    break;
                case 2:
                    enterPersonGender(person);
                    break;
                case 3:
                    enterPersonDescription(person);
                    break;
                case 4:
                    System.err.println("Not implemented yet.");
                    break;
                default:
                    System.err.println("Invalid input.\n");
            }
        }
    }

    public static void displayPeople() {
        if(Main.peopleList.isEmpty()){
            System.err.println("There are no people on the system.");
            return;
        }

        Main.peopleList.forEach(person -> {
            System.out.println(String.format("\nPerson nº%s:\n%s \n", Main.peopleList.indexOf(person) + 1, person));
        });

        System.out.println("Do you want to edit a person? (Y to edit)");
        if (Main.sc.nextLine().equalsIgnoreCase("Y")) {
            int choicePerson = 0;
            while (choicePerson < 1 || choicePerson > Main.peopleList.size()) {
                System.out.println("Enter the person number.");
                choicePerson = Main.sc.nextInt();
            }
            editPerson(Main.peopleList.get(choicePerson - 1));

        }

    }
}
