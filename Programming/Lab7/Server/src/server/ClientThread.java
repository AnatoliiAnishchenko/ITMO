package server;

import commands.Command;
import commands.CommandDescriptor;
import commands.CommandsManager;
import commands.FileHandler;
import mainClasses.MoominManager;
import moominClasses.Moomin;

import java.io.*;
import java.net.Socket;
import java.util.Vector;

public class ClientThread extends Thread {
    private static CommandsManager manager;
    private final MoominManager moominManager;
    private Vector<Moomin> moomins;
    private Socket socket;
    private boolean isActive;
    private boolean isLoggedin;
    private int userId = -1;

    public ClientThread(Socket socket, MoominManager moominManager) {
        this.moominManager = moominManager;

        isActive = true;
        isLoggedin = false;

        manager = moominManager.getCommandsManager();
        manager.addCommand(
                new Command("connect", 0, false) {
                    @Override
                    public void execute(int userID) {
                        manager.println("Please login or register if you are not registered yet.");
                    }

                    @Override
                    public void describe() {
                        manager.println("Command to run connection.");
                    }
                },
                new Command("load", 1, true) {
                    @Override
                    public void execute(int userID) {
                        if (DBConnector.addAllMooomins(FileHandler.readFile(getArguments()), userID)) {
                            manager.println("Collection was updated.");
                        } else {
                            manager.println("Collection wasn't updated.");
                        }
                    }

                    @Override
                    public void describe() {
                        manager.println("Add elements from the file on server to collection.");
                    }
                },
                new Command("wait", 0, false) {
                    @Override
                    public void execute(int userID) {
                        long start = System.currentTimeMillis();

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        manager.println(Thread.currentThread().getId() + " wait for " + (System.currentTimeMillis() - start));
                    }

                    @Override
                    public void describe() {
                        manager.println("Make a delay.");
                    }
                },
                new Command("import", -1, true) {
                    @Override
                    public void execute(int userID) {
                        if (DBConnector.addAllMooomins(moominManager.createMoomin(getArguments()), userID)) {
                            manager.println("Collection was updated.");
                        } else {
                            manager.println("Collection wasn't updated.");
                        }
                    }

                    @Override
                    public void describe() {
                        manager.println("Add elements from the client's file to collection.");
                    }
                },
                new Command("update", 0, true) {
                    @Override
                    public void execute(int userID) {
                        manager.println("Collection was updated.");
                    }

                    @Override
                    public void describe() {
                        manager.println("Send collection to the client.");
                    }
                },
                new Command("exit", 0, false) {
                    @Override
                    public void execute(int userID) {
                        manager.println("Good bye!");
                    }

                    @Override
                    public void describe() {
                        manager.println("Disconnect the user from the server.");
                    }
                },
                new Command("login", 2, false) {
                    @Override
                    public void execute(int userID) {
                        String username = getArguments().split(" ")[0];
                        String password = getArguments().split(" ")[1];

                        if (checkPassword(username, password)) {
                            isLoggedin = true;
                            userId = DBConnector.getUserID(username);
                            manager.println("You have been successfully logged in.");
                        } else {
                            manager.println("Wrong password, try again.");
                        }
                    }

                    @Override
                    public void describe() {
                        manager.println("Login the user in the system.");
                    }
                },
                new Command("logout", 0, true) {
                    @Override
                    public void execute(int userID) {
                        isLoggedin = false;
                        userId = -1;
                        manager.println("You have been successfully logged out.");
                    }

                    @Override
                    public void describe() {
                        manager.println("Logout the user from the system.");
                    }
                },
                new Command("register", 2, false) {
                    @Override
                    public void execute(int userID) {
                        String email = getArguments().split(" ")[0];
                        String username = getArguments().split(" ")[1];

                        register(email, username);
                    }

                    @Override
                    public void describe() {
                        manager.println("Register the user in the system.");
                    }
                });

        this.socket = socket;
        this.moomins = moomins;
    }

    public void run() {
        while(isActive) {
            byte[] request = new byte[4096];
            try {
                InputStream ins = socket.getInputStream();
                if (ins.read(request) < 0) {
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            answer(request);
        }
    }

    private void answer(byte[] request) {
        CommandDescriptor descriptor;

        try (ByteArrayInputStream bais = new ByteArrayInputStream(request);
             ObjectInputStream ois = new ObjectInputStream(bais);
             ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos);
             ByteArrayOutputStream bao = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(bao)) {

            descriptor = (CommandDescriptor) ois.readObject();

            manager.setPrintStream(printStream);

            if (descriptor.getNAME().equals("exit")) {
                isActive = false;
            }

            Command command = manager.recognizeCommand(descriptor);
            if (command != null) {
                if (isLoggedin || !command.isMUST_BE_LOGINED()) {
                    command.execute(userId);
                } else {
                    manager.println("You must be logged in!!!");
                }
            }

            printStream.flush();
            String doings = new String(bao.toByteArray()).trim();

            synchronized (System.out) {
                System.out.println();
                System.out.println("Client's command: " + descriptor.getNAME() + " " + descriptor.getArguments());
                System.out.println();
                System.out.println(doings);
            }

            Response response = new Response(doings, isLoggedin ? moominManager.getMoomins() : null);

            oos.writeObject(response);
            oos.flush();
            try {
                OutputStream outs = socket.getOutputStream();
                outs.write(baos.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static CommandsManager getManager() {
        return manager;
    }

    private boolean checkPassword(String username, String password) {
        return DBConnector.checkPassword(username, password);
    }

    private void register(String username, String email) {
        String password = StringRandomizer.randomPassword();
        if (!DBConnector.newUser(username, email, password)) {
            manager.println("User with this username or email already exist.");
            return;
        }

        EmailSendler.sendMail(email, "Your secret password", "Your username: " + username + "\nYour password: " + password);
        manager.println("Please try to login to the system by your username and password that was send to your email.");
    }
}
