package pt.up.fe.facades;

import pt.up.fe.Main;
import pt.up.fe.sources.*;

public class SourceFacade {

    public static void displaySources() {
        Main.sourcesList.forEach(source -> {
            System.out.println(String.format("%s - %s \n", Main.sourcesList.indexOf(source) + 1, source));
        });
    }

    public static Source newSourceInstance() {
        System.out.println("What is the the name of the source?");
        String name = Main.sc.nextLine();

        switch (name.toLowerCase()) {
            case "book":
                return new Book(name);
            case "historicalrecord":
                return new HistoricalRecord(name);
            case "onlineresource":
                OnlineResource onlineResource = new OnlineResource(name);
                return populateOnlineResource(onlineResource);
            case "orallytransmitted":
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
