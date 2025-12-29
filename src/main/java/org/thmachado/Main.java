package org.thmachado;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("CRUD: Control the users :)");
        Scanner scanner = new Scanner(System.in);
        boolean openSystem = true;

        while(openSystem) {
            System.out.println("1 - List all users");
            System.out.println("2 - Insert a user");
            System.out.println("3 - Update a user");
            System.out.println("4 - Delete a user");
            System.out.println("5 - Exit the system");

            try {
                int option = Integer.parseInt(scanner.nextLine());
                switch (option){
                    case 1:
                        System.out.println("List all users");
                        break;
                    case 2:
                        System.out.println("Insert a user");
                        break;
                    case 3:
                        System.out.println("Update a user");
                        break;
                    case 4:
                        System.out.println("Delete a user");
                        break;
                    case 5:
                        System.out.println("System is out!");
                        openSystem = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please choose a number between 1 and 5.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, try again: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
