package client;

import commands.CommandDescriptor;
import server.Response;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

class Client {
    private SocketChannel socket;

    Client(String serverAddress, int port) throws IOException {
        socket = SocketChannel.open(new InetSocketAddress(serverAddress, port));

        doCommand("connect");
    }

    private byte[] createRequest(String description) throws IOException {
        byte[] sending;
        CommandDescriptor command = new CommandDescriptor(description);

        if ("import".equals(command.getNAME())) {
            if (command.getARGS_COUNT() == 1) {
                char[] buf = new char[1024];

                try (FileReader fr = new FileReader(command.getArguments())) {
                    fr.read(buf);
                    String json = String.valueOf(buf);
                    command.setArguments(json);
                } catch (FileNotFoundException e) {
                    command = null;
                }
            }
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
        }
    }

    Response doCommand(String command) throws IOException {
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

                return response;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}