package client;

import commands.CommandDescriptor;
import moominClasses.Moomin;
import server.Response;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.Vector;

public class Client {
    private SocketChannel socket;
    private Scanner scanner;
    private boolean working;
    private Vector<Moomin> moomins;
    private final long WAITING_TIME = 10000;

    public Client(String serverAddress, int port) throws IOException {
        //Set working flag
        working = true;

        //Create socket
        socket = SocketChannel.open(new InetSocketAddress(serverAddress, port));

        scanner = new Scanner(System.in);
        moomins = new Vector<>();
        doCommand("connect");
    }

    private void work() throws IOException {
        if (working) {
            System.out.println("Client is ready to work.");
        } else {
            System.out.println("Client can't work.");
        }

        while (working){
            System.out.println();
            System.out.print("Your command: ");
            String stringIn = scanner.nextLine().trim();
            System.out.println();

            doCommand(stringIn);
        }

        System.out.println("Session end.");
    }

    private byte[] createRequest(String description) throws IOException {
        byte[] sending;
        CommandDescriptor command = new CommandDescriptor(description);

        switch (command.getNAME()) {
            case "exit":
                if (command.getARGS_COUNT() == 0) {
                    working = false;
                }
                break;

            case "import":
                if (command.getARGS_COUNT() == 1) {
                    char[] buf = new char[1024];

                    try (FileReader fr = new FileReader(command.getArguments())) {
                        fr.read(buf);
                        String json = String.valueOf(buf);
                        command.setArguments(json);
                    } catch (FileNotFoundException e){
                        System.err.println("File not found!");
                        command = null;
                    }
                }
                break;

            case "my":
                moomins.forEach(System.out::println);
                if (moomins.size() == 0) {
                    System.out.println("Collection is empty.");
                }
                command = null;
                break;
        }

        if (command == null) {
            return null;
        }

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos)){

            oos.writeObject(command);
            oos.flush();
            sending = baos.toByteArray();

            return sending;
        } catch (IOException e) {
            throw e;
        }
    }

    public void doCommand(String command) throws IOException {
        byte[] byteRequest = createRequest(command);

        //If we need to send the command
        if (byteRequest != null) {
            ByteBuffer request = ByteBuffer.wrap(byteRequest);

            while(request.hasRemaining()) {
                socket.write(request);
            }

            ByteBuffer respBuf = ByteBuffer.allocate(4096);

            socket.read(respBuf);

            byte[] byteResponse = respBuf.array();

            try (ByteArrayInputStream bais = new ByteArrayInputStream(byteResponse);
                ObjectInputStream ois = new ObjectInputStream(bais)) {

                Response response = (Response) ois.readObject();

                System.out.println(response.getDoings());
                moomins = response.getMoomins();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //We need to get server port and address
        String address;
        int port;

        //By arguments
        if (args.length == 2) {
            address = args[0];
            port = Integer.valueOf(args[1]);
        } else {
            //By input stream
            Scanner scanner = new Scanner(System.in);

            System.out.print("Address: ");
            address=scanner.nextLine();

            System.out.print("Port: ");
            port=scanner.nextInt();
        }
        try {
            //Creating the client.Client
            Client client = new Client(address, port);
            System.out.println("Welcome!!!");

            //Start working
            client.work();
        } catch (Exception e) {
            System.err.println("Something gone wrong. Please DEBUG!!!");
            e.printStackTrace();
        }
    }
}