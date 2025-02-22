package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
                String msg = inputStream.readUTF();
                System.out.println(msg);
                outputStream.writeUTF("All files were sent!");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
