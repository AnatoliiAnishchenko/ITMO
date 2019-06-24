package client;

import client.localization.LocalizationManager;
import moominClasses.Moomin;
import server.Response;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class ClientApp extends JFrame {
    static LocalizationManager localizationManager;

    private static RegisterPanel registerPanel;
    private static TablePanel tablePanel;
    private static ScreenPanel screenPanel;
    private static UserPanel userPanel;

    private static MoominMenu menu;

    private static final int FRAME_WIDTH = 1600;
    private static final int FRAME_HEIGHT = 1000;
    static final int SCREEN_PlANE_WIDTH = 1000;
    static final int BORDER = 20;
    private static final int USER_PLANE_WIDTH = FRAME_WIDTH - SCREEN_PlANE_WIDTH;
    static int HEIGHT;
    static final int MENU_HEIGHT = 30;


    private static boolean loggedIn;

    static Client client;
    static int clientId;
    private static String userName;

    static Vector<Moomin> moomins;

    private ClientApp() {
        super(localizationManager.getNativeTitle(LocalizationManager.TITLE_APPNAME));

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
                e.getWindow().dispose();
            }
        });
        setLocationRelativeTo(null);
        setResizable(false);

        loggedIn = false;
        clientId = -1;
        moomins = new Vector<>();

        new ColorManager();

        Timer timer = new Timer(20000, e -> update());

        menu = new MoominMenu(this);
        setJMenuBar(menu);

        HEIGHT = FRAME_HEIGHT - MENU_HEIGHT;

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        add(mainPanel);

        registerPanel = new RegisterPanel(this);
        registerPanel.setBounds((FRAME_WIDTH - registerPanel.getWidth()) / 2,
                (HEIGHT - registerPanel.getHeight()) / 2,
                registerPanel.getWidth(),
                registerPanel.getHeight());
        mainPanel.add(registerPanel);

        userPanel = new UserPanel(this);
        userPanel.setBounds(SCREEN_PlANE_WIDTH + BORDER / 2,
                menu.getHeight() + BORDER,
                USER_PLANE_WIDTH - BORDER * 3 / 2,
                HEIGHT - BORDER * 2 - MENU_HEIGHT);
        mainPanel.add(userPanel);

        tablePanel = new TablePanel();
        tablePanel.setBounds(BORDER,
                menu.getHeight() + BORDER,
                SCREEN_PlANE_WIDTH - BORDER * 3 / 2,
                HEIGHT - BORDER * 2 - MENU_HEIGHT);
        mainPanel.add(tablePanel);

        screenPanel = new ScreenPanel();
        screenPanel.setBackground(Color.LIGHT_GRAY);
        screenPanel.setBounds(BORDER,
                menu.getHeight() + BORDER,
                SCREEN_PlANE_WIDTH - BORDER * 3 / 2,
                HEIGHT - BORDER * 2 - MENU_HEIGHT);
        mainPanel.add(screenPanel);

        timer.start();
        goToRegisterPanel();
    }

    void exit() {
        try {
            setLoggedIn(false);
            client.doCommand("logout");
            client.doCommand("exit");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void goToRegisterPanel() {
        registerPanel.setVisible(true);
        tablePanel.setVisible(false);
        screenPanel.setVisible(false);
        userPanel.setVisible(false);
    }

    static void goToScreenPanel() {
        if (loggedIn) {
            registerPanel.setVisible(false);
            tablePanel.setVisible(false);
            screenPanel.setVisible(true);
            userPanel.setVisible(true);
        }
    }

    static void goToTablePanel() {
        if (loggedIn) {
            registerPanel.setVisible(false);
            tablePanel.setVisible(true);
            screenPanel.setVisible(false);
            userPanel.setVisible(true);
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
            client = new Client(address, port);
            System.out.println("Welcome!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        localizationManager = new LocalizationManager();
        ClientApp app = new ClientApp();
        app.setVisible(true);
    }

    static void setUserName(String userName) {
        ClientApp.userName = userName;
    }

    void setLoggedIn(boolean flag) {
        loggedIn = flag;

        if (flag) {
            update();

            goToScreenPanel();
        } else {
            try {
                client.doCommand("logout");
            } catch (IOException e) {
                e.printStackTrace();
            }
            userPanel.clear();
            clientId = -1;
            moomins = new Vector<>();

            goToRegisterPanel();
        }

        updatePanels();
    }

    static void update() {
        if (loggedIn) {
            try {
                Response response = client.doCommand("update");
                clientId = Integer.parseInt(response.getDoings());
                userPanel.setUserName(userName, clientId);
                moomins = response.getMoomins();
                tablePanel.updateMoomins();
                screenPanel.updateMoomins();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static void setMoomin(Moomin moomin, boolean isEditable) {
        userPanel.setMoomin(moomin, isEditable);
    }


    void updatePanels() {
        registerPanel.updateText();
        userPanel.updateText();
        tablePanel.updateText();
        menu.updateText();
        setTitle(localizationManager.getNativeTitle(LocalizationManager.TITLE_APPNAME));
    }
}
