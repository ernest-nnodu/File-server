package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
    private final String address = "127.0.0.1";
    private final int port = 23456;
    private final int backlog = 50;

    public void start() {
        System.out.println("Server started!");
        try(ServerSocket serverSocket = new ServerSocket(port, backlog,
                InetAddress.getByName(address))) {
            try(Socket socket = serverSocket.accept();
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {

                String msgFromClient = inputStream.readUTF();
                System.out.println("Received: " + msgFromClient);

                String msgToClient = "All files were sent!";
                outputStream.writeUTF(msgToClient);
                System.out.println("Sent: " + msgToClient);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
