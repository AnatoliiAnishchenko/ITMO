package clientSpamer;

import client.Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class SpamerThread extends Thread {
    private final int REQUEST_COUNT = 10;
    private Client client;

    public SpamerThread(String address, int port) {
        try {
            client = new Client(address, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        for (int i = 0; i < REQUEST_COUNT; i++) {
            try {
                client.doCommand("wait");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            client.doCommand("exit");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
