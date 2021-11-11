package pt.up.fe.facades;

import pt.up.fe.Main;
import pt.up.fe.sources.*;

import java.util.Arrays;
import java.util.List;

public class SourceFacade {

    public static void displaySources() {
        if (Main.peopleList.isEmpty()) {
            System.err.println("There are no Sources on the system.");
            return;
        }

        Main.sourcesList.forEach(source -> {
            System.out.println(String.format("Source nº%s: %s \n", Main.sourcesList.indexOf(source) + 1, source));
        });
    }

    public static Source newSourceInstance() {
        System.out.println("Creating new Source...");
        System.out.println("What is the the type of Source you want to create?");
        List<String> sourceTypes = Arrays.asList("Book", "Historical Record", "Online Resource",
                "Orally Transmitted", "Custom Source");

        sourceTypes.forEach(type -> {
            System.out.println(String.format("%s - %s", sourceTypes.indexOf(type) + 1, type));
        });
        int chosenSource = Main.sc.nextInt();
        Main.sc.nextLine();

        if (chosenSource < 1 || chosenSource > sourceTypes.toArray().length) {
            System.err.println("Invalid input.\n");
            System.exit(0);
        }

        System.out.println(sourceTypes.get(chosenSource - 1) + " was chosen.");

        System.out.println("What name do you want to give to the " + sourceTypes.get(chosenSource - 1) + "?");
        String name = Main.sc.nextLine();

        switch (chosenSource) {
            case 1:
                return new Book(name);
            case 2:
                return new HistoricalRecord(name);
            case 3:
                OnlineResource onlineResource = new OnlineResource(name);
                return populateOnlineResource(onlineResource);
            case 4:
                return new OrallyTransmitted(name);
            default:
                return new CustomSource(name);
        }
    }

    public static OnlineResource populateOnlineResource(OnlineResource onlineResource) {
        System.out.println("--- Online Resource Source ---");

        while (true) {
            System.out.println("Do you want to add authors? (Y to add)");
            if (!Main.sc.nextLine().equalsIgnoreCase("Y"))
                break;

            System.out.println("\nInsert Author:");
            onlineResource.addAuthor(Main.sc.nextLine());
        }

        System.out.println("Do you want to add link? (Y to add)");
        if (Main.sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.println("\nInsert Link:");
            onlineResource.setLink(Main.sc.nextLine());
        }

        return onlineResource;
    }
}
