package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class FileClient {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 23456;
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private final UserInterface userInterface;

    public FileClient() {
        userInterface = new UserInterface();
    }

    public void start() {
        connectToServer();
        userInterface.displayMessage("""
                Enter action (1 - get a file, 2 - create a file, 3 - delete a file):""");
        String action = userInterface.getUserInput();

        if (action.equals("exit")) {
            shutdownServer();
            return;
        }
        userInterface.displayMessage("Enter filename:");
        String fileName = userInterface.getUserInput();

        switch (action) {
            case "1":
                getFileFromServer(fileName);
                break;
            case "2":
                createFileOnServer(fileName);
                break;
            case "3":
                deleteFileOnServer(fileName);
                break;
            default:
                userInterface.displayMessage("Invalid command!");
        }
    }

    private void shutdownServer() {
        sendRequest("exit");
    }

    private void deleteFileOnServer(String fileName) {
    }

    private void createFileOnServer(String fileName) {
        userInterface.displayMessage("Enter file content:");
        String fileContent = userInterface.getUserInput();
        String request = "PUT " + fileName + " " + fileContent;
        sendRequest(request);
        getResponse();
    }

    private void getFileFromServer(String fileName) {
        String request = "GET " + fileName;
        sendRequest(request);
        getResponse();
    }

    private void getResponse() {
        try {
            String response = inputStream.readUTF();
            userInterface.displayMessage("The response says that " + response);
        } catch (IOException ex) {
            userInterface.displayMessage(ex.getMessage());
        }
    }

    private void sendRequest(String request) {
        try {
            outputStream.writeUTF(request);
            userInterface.displayMessage("The request was sent.");
        } catch (IOException ex) {
            userInterface.displayMessage(ex.getMessage());
        }
    }

    private void connectToServer() {
        try {
            TimeUnit.SECONDS.sleep(3);
            socket = new Socket(InetAddress.getByName(SERVER_ADDRESS), SERVER_PORT);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException | InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
