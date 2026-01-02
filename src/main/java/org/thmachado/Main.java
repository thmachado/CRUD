package org.thmachado;

import org.thmachado.controllers.UserController;
import org.thmachado.repositories.InMemoryUserRepository;
import org.thmachado.services.UserService;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        System.out.println("CRUD: Control the users :)");

        InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();
        UserService userService = new UserService(inMemoryUserRepository);
        UserController userController = new UserController(userService);

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
                        userController.index().forEach(user ->
                                System.out.println(user.uuid + " | " + user.firstname + " " + user.lastname + " | " + user.email)
                        );
                        break;
                    case 2:
                        System.out.print("Firstname: ");
                        String firstname = scanner.nextLine();

                        System.out.print("Lastname: ");
                        String lastname = scanner.nextLine();

                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        userController.store(firstname, lastname, email);
                        System.out.println("User created!");
                        break;
                    case 3:
                        System.out.print("User UUID: ");
                        UUID id = UUID.fromString(scanner.nextLine());

                        System.out.print("New firstname: ");
                        String firstnameUpdate = scanner.nextLine();

                        System.out.print("New lastname: ");
                        String lastnameUpdate = scanner.nextLine();

                        System.out.print("New email: ");
                        String emailUpdate = scanner.nextLine();

                        userController.update(id, firstnameUpdate, lastnameUpdate, emailUpdate);
                        System.out.println("User updated!");
                        break;
                    case 4:
                        System.out.print("User UUID: ");
                        UUID idDelete = UUID.fromString(scanner.nextLine());

                        userController.destroy(idDelete);
                        System.out.println("User deleted!");
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
