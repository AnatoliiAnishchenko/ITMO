package client;

import client.localization.LocalizationManager;
import moominClasses.Field;
import moominClasses.Moomin;
import org.json.simple.JSONArray;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.ZonedDateTime;

class UserPanel extends JPanel {
    private boolean isEditable;
    private Moomin selectedMoomin;

    private String userName;

    private LocalizationManager localizationManager = ClientApp.localizationManager;

    private JLabel userNameLabel;
    private JTextField color;
    private JButton logout;

    private JButton clear, update, add, remove, removeLess;

    private JLabel nameLabel, typeLabel, fieldLabel, genderLabel, conditionLabel, positionLabel, timeLabel, xLabel, yLabel;
    private JTextField nameTextField, typeTextField, fieldTextField, genderTextField, conditionTextField, positionTextField, timeTextField, xTextField, yTextField;

    UserPanel(ClientApp clientApp) {
        super();

        userName = "";

        userNameLabel = new JLabel();
        color = new JTextField(5);
        color.setEditable(false);

        logout = new JButton();
        logout.addActionListener(e -> clientApp.setLoggedIn(false));

        clear = new JButton();
        update = new JButton();
        add = new JButton();
        remove = new JButton();
        removeLess = new JButton();

        clear.addActionListener(e -> clear());
        update.addActionListener(e -> updateMoomin());
        add.addActionListener(e -> addMoomin());
        remove.addActionListener(e -> removeMoomin());
        removeLess.addActionListener(e -> removeLessMoomin());

        nameLabel = new JLabel();
        nameTextField = new JTextField();
        typeLabel = new JLabel();
        typeTextField = new JTextField();
        fieldLabel = new JLabel();
        fieldTextField = new JTextField();
        genderLabel = new JLabel();
        genderTextField = new JTextField();
        conditionLabel = new JLabel();
        conditionTextField = new JTextField();
        positionLabel = new JLabel();
        positionTextField = new JTextField();
        timeLabel = new JLabel();
        timeTextField = new JTextField();
        xLabel = new JLabel();
        xTextField = new JTextField();
        yLabel = new JLabel();
        yTextField = new JTextField();

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(userNameLabel)
                        .addComponent(color)
                        .addComponent(logout))
                .addGap(100, 200, 200)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(clear)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(add)
                                .addComponent(update))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(remove)
                                .addComponent(removeLess)))
                .addGap(100, 200, 200)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nameLabel)
                                .addComponent(nameTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(typeLabel)
                                .addComponent(typeTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(fieldLabel)
                                .addComponent(fieldTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(genderLabel)
                                .addComponent(genderTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(conditionLabel)
                                .addComponent(conditionTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(positionLabel)
                                .addComponent(positionTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(timeLabel)
                                .addComponent(timeTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(xLabel)
                                .addComponent(xTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(yLabel)
                                .addComponent(yTextField))));

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(userNameLabel)
                                .addComponent(color)
                                .addComponent(logout))
                        .addComponent(clear)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(add)
                                        .addComponent(remove))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(update)
                                        .addComponent(removeLess)))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(nameLabel)
                                        .addComponent(typeLabel)
                                        .addComponent(fieldLabel)
                                        .addComponent(genderLabel)
                                        .addComponent(conditionLabel)
                                        .addComponent(positionLabel)
                                        .addComponent(timeLabel)
                                        .addComponent(xLabel)
                                        .addComponent(yLabel))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(nameTextField)
                                        .addComponent(typeTextField)
                                        .addComponent(fieldTextField)
                                        .addComponent(genderTextField)
                                        .addComponent(conditionTextField)
                                        .addComponent(positionTextField)
                                        .addComponent(timeTextField)
                                        .addComponent(xTextField)
                                        .addComponent(yTextField)))));
        clear();
        init();
    }

    private void init() {
        userNameLabel.setText(localizationManager.getNativeTitle(LocalizationManager.TITLE_USERNAME) + ": " + userName);
        logout.setText(localizationManager.getNativeButton(LocalizationManager.BUTTON_LOGOUT));
        clear.setText(localizationManager.getNativeButton(LocalizationManager.BUTTON_CLEAR));
        update.setText(localizationManager.getNativeButton(LocalizationManager.BUTTON_UPDATE));
        add.setText(localizationManager.getNativeButton(LocalizationManager.BUTTON_ADD));
        remove.setText(localizationManager.getNativeButton(LocalizationManager.BUTTON_REMOVE));
        removeLess.setText(localizationManager.getNativeButton(LocalizationManager.BUTTON_REMOVE_LESS));

        nameLabel.setText(localizationManager.getNativeTitle(LocalizationManager.TITLE_NAME));
        typeLabel.setText(localizationManager.getNativeTitle(LocalizationManager.TITLE_TYPE));
        fieldLabel.setText(localizationManager.getNativeTitle(LocalizationManager.TITLE_FIELD));
        genderLabel.setText(localizationManager.getNativeTitle(LocalizationManager.TITLE_GENDER));
        conditionLabel.setText(localizationManager.getNativeTitle(LocalizationManager.TITLE_CONDITION));
        positionLabel.setText(localizationManager.getNativeTitle(LocalizationManager.TITLE_POSITION));
        timeLabel.setText(localizationManager.getNativeTitle(LocalizationManager.TITLE_TIME));
        xLabel.setText("x");
        yLabel.setText("y");
    }

    void setUserName(String userName, int id) {
        this.userName = userName;
        color.setBackground(ColorManager.getColor(id));
        updateText();
    }

    void updateText() {
        init();
    }

    private void updateMoomin() {
        if (isEditable && (selectedMoomin != null)) {
            removeCurMoomin(selectedMoomin);
            addMoomin();
        }
    }

    private void addMoomin() {
        Moomin moomin = getMoomin();

        if (moomin == null) {
            return;
        }

        try {
            JSONArray arr = new JSONArray();
            arr.add(moomin.toJSON());
            ClientApp.client.doCommand("add " + arr.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ClientApp.update();
    }


    private void removeMoomin() {
        if (isEditable) {
            Moomin moomin = getMoomin();

            if (moomin == null) {
                return;
            }

            removeCurMoomin(moomin);
            ClientApp.update();
        }
    }

    private void removeCurMoomin(Moomin moomin) {
        try {
            ClientApp.client.doCommand("remove " + moomin.toJSON().toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeLessMoomin() {
        if (isEditable) {
            Moomin moomin = getMoomin();

            if (moomin == null) {
                return;
            }

            try {
                ClientApp.client.doCommand("remove_lowerest " + moomin.toJSON().toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            ClientApp.update();
        }
    }

    private Moomin getMoomin() {
        String name = nameTextField.getText();
        Moomin.TypeOfMoomin type;
        Field field = new Field(fieldTextField.getText());
        Moomin.Gender gender;
        Moomin.Condition condition;
        Moomin.Position position;
        ZonedDateTime time = ZonedDateTime.now();
        double x;
        double y;

        try {
            type = Moomin.TypeOfMoomin.strToType(typeTextField.getText());
            typeTextField.setBackground(Color.WHITE);
        } catch (IllegalArgumentException e) {
            typeTextField.setBackground(new Color(0XFF4444));
            return null;
        }

        try {
            gender = Moomin.Gender.strToGender(genderTextField.getText());
            genderTextField.setBackground(Color.WHITE);
        } catch (IllegalArgumentException e) {
            genderTextField.setBackground(new Color(0XFF4444));
            return null;
        }

        try {
            condition = Moomin.Condition.strToCondition(conditionTextField.getText());
            conditionTextField.setBackground(Color.WHITE);
        } catch (IllegalArgumentException e) {
            conditionTextField.setBackground(new Color(0XFF4444));
            return null;
        }

        try {
            position = Moomin.Position.strToPosition(positionTextField.getText());
            positionTextField.setBackground(Color.WHITE);
        } catch (IllegalArgumentException e) {
            positionTextField.setBackground(new Color(0XFF4444));
            return null;
        }

        try {
            x = Double.parseDouble(xTextField.getText());
            xTextField.setBackground(Color.WHITE);
        } catch (Exception e) {
            xTextField.setBackground(new Color(0XFF4444));
            return null;
        }

        try {
            y = Double.parseDouble(yTextField.getText());
            yTextField.setBackground(Color.WHITE);
        } catch (Exception e) {
            yTextField.setBackground(new Color(0XFF4444));
            return null;
        }

        return new Moomin(time, name, field, type, gender, position, condition, x, y, ClientApp.clientId);
    }

    void clear() {
        isEditable = true;
        selectedMoomin = null;

        nameTextField.setBackground(Color.WHITE);
        typeTextField.setBackground(Color.WHITE);
        fieldTextField.setBackground(Color.WHITE);
        genderTextField.setBackground(Color.WHITE);
        conditionTextField.setBackground(Color.WHITE);
        positionTextField.setBackground(Color.WHITE);
        timeTextField.setText(ZonedDateTime.now().toString());
        xTextField.setBackground(Color.WHITE);
        yTextField.setBackground(Color.WHITE);

        nameTextField.setText("");
        typeTextField.setText("");
        fieldTextField.setText("");
        genderTextField.setText("");
        conditionTextField.setText("");
        positionTextField.setText("");
        timeTextField.setText(ZonedDateTime.now().toString());
        xTextField.setText("");
        yTextField.setText("");

        setEditable();
    }

    void setMoomin(Moomin moomin, boolean isEditable) {
        this.isEditable = isEditable;
        selectedMoomin = moomin;

        fillTextFields();
    }

    private void setEditable() {
        nameTextField.setEditable(isEditable);
        typeTextField.setEditable(isEditable);
        fieldTextField.setEditable(isEditable);
        genderTextField.setEditable(isEditable);
        conditionTextField.setEditable(isEditable);
        positionTextField.setEditable(isEditable);
        timeTextField.setEditable(false);
        xTextField.setEditable(isEditable);
        yTextField.setEditable(isEditable);
    }

    private void fillTextFields() {
        nameTextField.setText(selectedMoomin.getName());
        typeTextField.setText(selectedMoomin.getType().toString());
        fieldTextField.setText(selectedMoomin.getField().toString());
        genderTextField.setText(selectedMoomin.getGender().toString());
        conditionTextField.setText(selectedMoomin.getCondition().toString());
        positionTextField.setText(selectedMoomin.getPosition().toString());
        timeTextField.setText(selectedMoomin.getCreationTime().toString());
        xTextField.setText(String.valueOf(selectedMoomin.getX()));
        yTextField.setText(String.valueOf(selectedMoomin.getY()));

        setEditable();
    }
}
