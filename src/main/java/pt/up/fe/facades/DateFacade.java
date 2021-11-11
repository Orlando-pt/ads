package pt.up.fe.facades;

import java.util.Scanner;

import pt.up.fe.dates.*;

public class DateFacade {
    public static IDate createDate(Scanner sc) {
        int typeOfDate = 0;
        while(typeOfDate != 1 && typeOfDate != 2) {
            System.out.println("What kind of date you want to create?");
            System.out.println("1 - Simple Date");
            System.out.println("2 - Interval Date");
            typeOfDate = sc.nextInt();
            sc.nextLine();
        }

        if(typeOfDate == 1) {
            System.out.println("--- Simple Date constructor ---");
            return createSimpleDate(sc);
        }

        System.out.println("\n--- Date 1 ---");
        IDate date1 = createSimpleDate(sc);
        System.out.println("\n--- Date 2 --- ");
        IDate date2 = createSimpleDate(sc);

        return new IntervalDate((SimpleDate) date1, (SimpleDate) date2);
    }

    public static IDate createSimpleDate(Scanner sc) {
        IBuilder dateBuilder = new SimpleDateBuilder().reset();

        System.out.println("\nDo you want to add Year? (Y to add)");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.println("Year: ");
            dateBuilder.setYear(sc.nextInt());
            sc.nextLine();
        }

        System.out.println("\nDo you want to add Month? (Y to add)");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.println("Month: ");
            dateBuilder.setMonth(sc.nextInt());
            sc.nextLine();
        }

        System.out.println("\nDo you want to add Day? (Y to add)");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.println("Day: ");
            dateBuilder.setDay(sc.nextInt());
            sc.nextLine();
        }

        System.out.println("\nDo you want to add Hour? (Y to add)");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.println("Hour: ");
            dateBuilder.setHour(sc.nextInt());
            sc.nextLine();
        }

        System.out.println("\nDo you want to add Minute? (Y to add)");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.println("Minute: ");
            dateBuilder.setMinute(sc.nextInt());
            sc.nextLine();
        }

        System.out.println("\nDo you want to add Second? (Y to add)");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.println("Second: ");
            dateBuilder.setSecond(sc.nextInt());
            sc.nextLine();
        }

        System.out.println("... \n");

        return dateBuilder.build();
    }
}
