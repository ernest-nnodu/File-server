package server;

import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
