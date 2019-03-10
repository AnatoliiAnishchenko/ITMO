import javax.swing.*;
import java.awt.*;

public class LogPanel extends JPanel {
    private static JTextArea textArea = new JTextArea();
    private static final int fontSize = 22;

    public LogPanel() {
        super();
        setLayout(new BorderLayout());
        JPanel workPanel = new JPanel();
        add(workPanel);

        workPanel.setBackground(Color.BLACK);

        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.PLAIN, fontSize));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(null);
        textArea.setBorder(null);

        textArea.setForeground(Color.WHITE);

        scrollPane.setBackground(Color.BLACK);
        textArea.setBackground(Color.BLACK);

        add(scrollPane, BorderLayout.CENTER);
    }

    static void print(String str) {
        textArea.append(str);
    }

}
