package client;

import client.localization.LocalizationManager;
import server.Response;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;

class RegisterPanel extends JPanel {
    private JPanel loginPanel;
    private JPanel registerPanel;

    private LocalizationManager localizationManager;

    private JButton login, goToRegister, register, goToLogin;
    private JTextField loginUserNameField, registerUserNameField, emailField;
    private JPasswordField passwordField;

    RegisterPanel(ClientApp clientApp) {
        super();
        setSize(300,120);

        localizationManager = ClientApp.localizationManager;

        loginPanel = new JPanel();
        registerPanel = new JPanel();

        JPanel loginInputPanel, registerInputPanel, loginButtonsPanel, registerButtonsPanel;

        loginUserNameField = new JTextField();
        registerUserNameField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField(10);

        login = new JButton();
        goToRegister = new JButton();
        register = new JButton();
        goToLogin = new JButton();

        init();

        login.addActionListener(e -> {
            try {
                Response response = ClientApp.client.doCommand("login " + loginUserNameField.getText() + " " + passwordField.getText());

                if (response.getDoings().equals("You have been successfully logged in.")) {
                    clientApp.setUserName(loginUserNameField.getText());
                    clientApp.setLoggedIn(true);
                    passwordField.setBackground(Color.WHITE);
                } else {
                    loginUserNameField.setText("");
                    passwordField.setText("");
                    passwordField.setBackground(new Color(0XFF4444));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        goToLogin.addActionListener(e -> goToLoginPanel());
        register.addActionListener(e -> {
            try {
                Response response = ClientApp.client.doCommand("register " + registerUserNameField.getText() + " " + emailField.getText());

                if (response.getDoings().equals("Please try to login to the system by your username and password that was send to your email.")) {
                    goToLoginPanel();
                    loginUserNameField.setText(registerUserNameField.getText());
                    registerUserNameField.setText("");
                    emailField.setText("");
                    registerUserNameField.setBackground(Color.WHITE);
                    emailField.setBackground(Color.WHITE);
                } else {
                    registerUserNameField.setBackground(new Color(0XFF4444));
                    emailField.setBackground(new Color(0XFF4444));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        goToRegister.addActionListener(e -> goToRegisterPanel());

        loginButtonsPanel = new JPanel();
        loginButtonsPanel.add(login);
        loginButtonsPanel.add(goToRegister);

        registerButtonsPanel = new JPanel();
        registerButtonsPanel.add(register);
        registerButtonsPanel.add(goToLogin);

        loginInputPanel = new JPanel();
        loginInputPanel.setLayout(new BorderLayout());
        loginInputPanel.add(loginUserNameField, BorderLayout.NORTH);
        loginInputPanel.add(passwordField, BorderLayout.SOUTH);

        registerInputPanel = new JPanel();
        registerInputPanel.setLayout(new BorderLayout());
        registerInputPanel.add(registerUserNameField, BorderLayout.NORTH);
        registerInputPanel.add(emailField, BorderLayout.SOUTH);

        loginPanel.setLayout(new BorderLayout());
        loginPanel.add(loginInputPanel, BorderLayout.CENTER);
        loginPanel.add(loginButtonsPanel, BorderLayout.SOUTH);

        registerPanel.setLayout(new BorderLayout());
        registerPanel.add(registerInputPanel, BorderLayout.CENTER);
        registerPanel.add(registerButtonsPanel, BorderLayout.SOUTH);

        this.add(loginPanel);
        this.add(registerPanel);

        goToLoginPanel();
    }

    private void init() {
        login.setText(localizationManager.getNativeButton(LocalizationManager.BUTTON_LOGIN));
        goToRegister.setText(localizationManager.getNativeButton(LocalizationManager.BUTTON_REGISTER));
        register.setText(localizationManager.getNativeButton(LocalizationManager.BUTTON_REGISTER));
        goToLogin.setText(localizationManager.getNativeButton(LocalizationManager.BUTTON_LOGIN));

        loginUserNameField.setBorder(new TitledBorder(localizationManager.getNativeTitle(LocalizationManager.TITLE_USERNAME)));
        registerUserNameField.setBorder(new TitledBorder(localizationManager.getNativeTitle(LocalizationManager.TITLE_USERNAME)));
        emailField.setBorder(new TitledBorder(localizationManager.getNativeTitle(LocalizationManager.TITLE_EMAIL)));
        passwordField.setBorder(new TitledBorder(localizationManager.getNativeTitle(LocalizationManager.TITLE_PASSWORD)));
    }

    private void goToLoginPanel() {
        loginPanel.setVisible(true);
        registerPanel.setVisible(false);
    }

    private void goToRegisterPanel() {
        loginPanel.setVisible(false);
        registerPanel.setVisible(true);
    }

    void updateText() {
        init();
    }
}
