package server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
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
        System.out.println("Goodbye!");
    }

    private void connectToClient() {
            try {
                serverSocket = new ServerSocket(port, backlog, InetAddress.getByName(address));
                while (true) {
                    socket = serverSocket.accept();
                    inputStream = new DataInputStream(socket.getInputStream());
                    outputStream = new DataOutputStream(socket.getOutputStream());

                    String requestFromClient = inputStream.readUTF();
                    System.out.println(requestFromClient);

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

        String command = extractRequestDetail(requestFromClient, "command");
        String fileName = extractRequestDetail(requestFromClient, "file name");

        switch (command) {
            case "PUT":
                String content = extractRequestDetail(requestFromClient, "content");
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

    private String extractRequestDetail(String requestFromClient, String detailToExtract) {
        String[] details = requestFromClient.split(" ");

        return switch (detailToExtract) {
            case "command" -> details[0];
            case "file name" -> details[1];
            case "content" -> {
                StringBuilder builder = new StringBuilder();
                for (int index = 2; index < details.length; index++) {
                    builder.append(details[index]).append(" ");
                }
                yield builder.toString();
            }
            default -> "";
        };
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
