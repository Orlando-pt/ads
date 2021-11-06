package pt.up.fe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("What do you want to perform?\n 0 - Quit\n 1 - Search for person \n....");
            int selection = sc.nextInt();

            switch (selection) {
                case 0:
                    System.err.println("Bye bye, have a nice day.\n");
                    System.exit(0);
                    break;
                case 1:
                    // code block
                    break;
                default:
                    System.err.println("Invalid input.\n");
            }
        }

    }
}
