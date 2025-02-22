package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class FileClient {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 23456;

    public void start() {
        try(Socket socket = new Socket(InetAddress.getByName(SERVER_ADDRESS), SERVER_PORT);
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {

            String msgToServer = "Give me everything you have!";
            outputStream.writeUTF(msgToServer);
            System.out.println("Sent: " + msgToServer);

            String msgFromServer = inputStream.readUTF();
            System.out.println("Received: " + msgFromServer);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
