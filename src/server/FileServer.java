package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FileServer {
    private final String address = "127.0.0.1";
    private final int port = 23456;
    private final int backlog = 50;
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private final FileService fileService;

    public FileServer() {
        fileService = new FileService();
    }
    public void start() {
        System.out.println("Server started!");
        connectToClient();
    }

    private void connectToClient() {
        try {
            serverSocket = new ServerSocket(port, backlog, InetAddress.getByName(address));
            socket = serverSocket.accept();
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String requestFromClient = inputStream.readUTF();
                if (requestFromClient.equals("exit")) {
                    shutdown();
                    break;
                }
                String response = processRequest(requestFromClient);
                outputStream.writeUTF(response);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private String processRequest(String requestFromClient) {

        String[] requestDetails = requestFromClient.split(" ");
        String command = requestDetails[0];
        String fileName = requestDetails[1];

        switch (command) {
            case "PUT":
                String content = String.join("", Arrays.copyOfRange(requestDetails, 2, requestDetails.length));
                return createFile(fileName, content);
            case "GET":
                break;
            case "DELETE":
                break;
            default:
                return "Invalid command";
        }
        return command;
    }

    private String createFile(String fileName, String content) {
        return fileService.addFile(fileName, content);
    }

    private void shutdown() {
        System.out.println("Shutting down server...");
        try {
            serverSocket.close();
            socket.close();
            inputStream.close();
            outputStream.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
