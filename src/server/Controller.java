package server;

import client.UserInterface;

public class Controller {

    private final UserInterface userInterface;
    private final CommandHandler commandHandler;

    public Controller() {
        userInterface = new UserInterface();
        commandHandler = new CommandHandler();
    }

    public void start() {

        String input = userInterface.getUserInput();

        while (!input.equals("exit")) {

            String command = input.split(" ")[0];
            String fileName = input.split(" ")[1];

            userInterface.displayMessage(commandHandler.execute(command, fileName));
            input = userInterface.getUserInput();
        }
    }
}
