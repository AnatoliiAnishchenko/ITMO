import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ConsolePanel extends JPanel {
    private JTextArea textArea = new JTextArea("Type \"help\" if you want to know all commands\n");
    private JTextField textField = new JTextField();

    private static final int bound = 8;
    private static final int fontSize = 22;

    public ConsolePanel() {
        super();
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        textArea.setEditable(false);
        textField.setFont(new Font("Consolas", Font.PLAIN, fontSize));
        textArea.setFont(new Font("Consolas", Font.PLAIN, fontSize));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(null);
        textArea.setBorder(null);
        textField.setBorder(null);

        textArea.setForeground(Color.WHITE);
        textField.setForeground(Color.WHITE);

        scrollPane.setBackground(Color.BLACK);
        textField.setBackground(Color.BLACK);
        textArea.setBackground(Color.BLACK);

        add(scrollPane, BorderLayout.CENTER);
        add(textField, BorderLayout.SOUTH);

        textField.addKeyListener(new KeyAdapter() {
                                     public void keyPressed(KeyEvent e) {
                                         int key = e.getKeyCode();
                                         if (key == KeyEvent.VK_ENTER) {
                                             String command = textField.getText();
                                             textArea.append(command + "\n");
                                             textField.setText("");

                                             //TODO command parser

                                             switch (command) {
                                                 case ("exit"):
                                                     System.exit(0);
                                                     //TODO normal exit
                                                     break;
                                                 case ("clear"):
                                                     textArea.setText("");
                                                     break;
                                                 case ("help"):
                                                     textArea.append("List of commands:\n");
                                                     textArea.append("\"clear\" - to clear the console\n");
                                                     textArea.append("\"exit\" - to close the program\n");
                                                     break;
                                                 case ("test"): //TODO remove after test
                                                     String str = "test1\n" +
                                                             "test2\n" +
                                                             "test3\n" +
                                                             "test4\n" +
                                                             "test5\n" +
                                                             "test6\n" +
                                                             "test7\n" +
                                                             "test8\n";
                                                     LogPanel.print(str);
                                                     break;
                                                 case ("longTest"): //TODO remove after test
                                                     str = "long long long long long long long long long long " +
                                                             "long long long long long long long long long long " +
                                                             "long long long long long long long long long long " +
                                                             "long long long long long long long long long long " +
                                                             "long long long long long long long long long test\n";
                                                     LogPanel.print(str);
                                                     break;
                                             }

                                             updateScrollPane();
                                         }
                                     }

                                     private void updateScrollPane() {
                                         textArea.setCaretPosition(textArea.getDocument().getLength());
                                     }
                                 }
        );
    }
}
