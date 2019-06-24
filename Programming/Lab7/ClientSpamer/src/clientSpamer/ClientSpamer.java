package clientSpamer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class ClientSpamer {
    private static final int SPAM_COUNT = 10;

    public static void main(String[] args) {
        //We need to get server port and address
        String address;
        int port = 1234;

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

        long time = System.currentTimeMillis();

        ArrayList<Thread> list = new ArrayList<>();
        for (int i = 0; i < SPAM_COUNT; i++) {
            SpamerThread thread = new SpamerThread(address, port);
            list.add(thread);
            thread.start();
        }

        boolean isAlive = true;
        while (isAlive) {
            isAlive = false;
            for (Thread thread : list) {
                isAlive |= thread.isAlive();
            }
        }

        System.err.println(System.currentTimeMillis() - time);
    }
}
