package pt.up.fe.facades;

import pt.up.fe.dates.IBuilder;
import pt.up.fe.dates.IDate;
import pt.up.fe.dates.SimpleDateBuilder;
import pt.up.fe.dates.*;

import java.util.Scanner;


public class DateFacade {
    private Scanner scanner;

    public DateFacade(Scanner scanner) {
        this.scanner = scanner;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public IDate createDate() {
        int typeOfDate = 0;
        while(typeOfDate != 1 && typeOfDate != 2) {
            System.out.println("What kind of date you want to create?");
            System.out.println("1 - Simple Date");
            System.out.println("2 - Interval Date");

            typeOfDate = this.getScanner().nextInt();
            this.getScanner().nextLine();

        }

        if(typeOfDate == 1) {
            System.out.println("--- Simple Date constructor ---");
            return createSimpleDate();
        }

        System.out.println("\n--- Date 1 ---");
        IDate date1 = createSimpleDate();
        System.out.println("\n--- Date 2 --- ");
        IDate date2 = createSimpleDate();

        return new IntervalDate((SimpleDate) date1, (SimpleDate) date2);
    }

    public IDate createSimpleDate() {
        IBuilder dateBuilder = new SimpleDateBuilder().reset();

        System.out.println("\nDo you want to add Year? (Y to add)");
        if (this.getScanner().nextLine().equalsIgnoreCase("Y")) {
            System.out.println("Year: ");
            dateBuilder.setYear(this.getScanner().nextInt());
            this.getScanner().nextLine();
        }

        System.out.println("\nDo you want to add Month? (Y to add)");
        if (this.getScanner().nextLine().equalsIgnoreCase("Y")) {
            System.out.println("Month: ");
            dateBuilder.setMonth(this.getScanner().nextInt());
            this.getScanner().nextLine();
        }

        System.out.println("\nDo you want to add Day? (Y to add)");
        if (this.getScanner().nextLine().equalsIgnoreCase("Y")) {
            System.out.println("Day: ");
            dateBuilder.setDay(this.getScanner().nextInt());
            this.getScanner().nextLine();
        }

        System.out.println("\nDo you want to add Hour? (Y to add)");
        if (this.getScanner().nextLine().equalsIgnoreCase("Y")) {
            System.out.println("Hour: ");
            dateBuilder.setHour(this.getScanner().nextInt());
            this.getScanner().nextLine();
        }

        System.out.println("\nDo you want to add Minute? (Y to add)");
        if (this.getScanner().nextLine().equalsIgnoreCase("Y")) {
            System.out.println("Minute: ");
            dateBuilder.setMinute(this.getScanner().nextInt());
            this.getScanner().nextLine();
        }

        System.out.println("\nDo you want to add Second? (Y to add)");
        if (this.getScanner().nextLine().equalsIgnoreCase("Y")) {
            System.out.println("Second: ");
            dateBuilder.setSecond(this.getScanner().nextInt());
            this.getScanner().nextLine();
        }

        System.out.println("... \n");

        return dateBuilder.build();
    }
}
