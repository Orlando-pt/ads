package pt.up.fe.facades;

import pt.up.fe.Main;
import pt.up.fe.dates.IBuilder;
import pt.up.fe.dates.IDate;
import pt.up.fe.dates.SimpleDateBuilder;

public class DateFacade {
    public static IDate createDate() {
        int typeOfDate = 0;
        while(typeOfDate != 1 && typeOfDate != 2) {
            System.out.println("What kind of date you want to create?");
            System.out.println("1 - Simple Date");
            System.out.println("2 - Interval Date");
            typeOfDate = Main.sc.nextInt();
        }

        if(typeOfDate == 1) {
            IBuilder dateBuilder = new SimpleDateBuilder().reset();

            System.out.println("--- Simple Date constructor ---");
            System.out.println("Year: ");
            dateBuilder.setYear(Main.sc.nextInt());
            System.out.println("Month: ");
            dateBuilder.setMonth(Main.sc.nextInt());
            System.out.println("Day: ");
            dateBuilder.setDay(Main.sc.nextInt());
            System.out.println("Hour: ");
            dateBuilder.setHour(Main.sc.nextInt());
            System.out.println("Minute: ");
            dateBuilder.setMinute(Main.sc.nextInt());
            System.out.println("Second: ");
            dateBuilder.setSecond(Main.sc.nextInt());
            System.out.println("... \n");

            return dateBuilder.build();
        }

        // TODO: Implement interval date, create function with above logic and reuse twice

        return null;
    }
}
