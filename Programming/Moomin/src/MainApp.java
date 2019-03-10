import javax.swing.*;
import java.awt.*;

public class MainApp extends JFrame {
    private MainPanel mainPanel = new MainPanel();
    private LogPanel logPanel = new LogPanel();
    private ListPanel listPanel = new ListPanel();
    private ConsolePanel consolePanel = new ConsolePanel();

    private static final int bound = 16;

    public MainApp() {
        super("Moomin Application");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);

        JPanel workPanel = new JPanel();
        getContentPane().add(workPanel);
        workPanel.setBackground(Color.DARK_GRAY);
        workPanel.setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        mainPanel.setBounds(bound, bound, (int) ((screenSize.getWidth() - bound * 3) / 4 * 3),
                (int) ((screenSize.getHeight() - bound * 3) / 4 * 3));
        listPanel.setBounds((int) ((screenSize.getWidth() - bound * 3) / 4 * 3 + 2 * bound), bound,
                (int) ((screenSize.getWidth() - bound * 3) / 4),
                (int) ((screenSize.getHeight() - bound * 3) / 4 * 3));
        consolePanel.setBounds(bound, (int) ((screenSize.getHeight() - bound * 3) / 4 * 3 + 2 * bound),
                (int) ((screenSize.getWidth() - bound * 3) / 2),
                (int) ((screenSize.getHeight() - bound * 3) / 4));
        logPanel.setBounds((int) ((screenSize.getWidth() - bound * 3) / 2 + 2 * bound),
                (int) ((screenSize.getHeight() - bound * 3) / 4 * 3 + 2 * bound),
                (int) ((screenSize.getWidth() - bound * 3) / 2),
                (int) ((screenSize.getHeight() - bound * 3) / 4));

        workPanel.add(mainPanel);
        workPanel.add(listPanel);
        workPanel.add(consolePanel);
        workPanel.add(logPanel);
    }

    void print(String str) {
        LogPanel.print(str);
    }
}
