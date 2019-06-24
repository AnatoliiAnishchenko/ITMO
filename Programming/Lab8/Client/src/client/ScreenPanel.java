package client;

import moominClasses.Moomin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ScreenPanel extends JPanel {
    private Point mouse;
    private ArrayList<MoominCircle> circles;

    ScreenPanel() {
        super();
        mouse = new Point(0, 0);
        circles = new ArrayList<>();


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouse.setLocation(e.getX(), e.getY());
                chooseCircle();
            }
        });

        paintComponent(this.getGraphics());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (circles.size() > 0) {
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, 1000, 1000);
        }

        circles = new ArrayList<>();

        for (Moomin moomin : ClientApp.moomins) {
            MoominCircle circle = new MoominCircle(moomin);
            circles.add(circle);
            g2.setColor(circle.getColor());
            g2.fill(circle);
        }

        revalidate();
    }

    private void chooseCircle() {
        for (MoominCircle circle : circles) {
            if (circle.contains(mouse)) {
                Moomin moomin = circle.getMoomin();
                ClientApp.setMoomin(moomin, ClientApp.clientId == moomin.getOwnerId());
                break;
            }
        }
    }

    void updateMoomins() {
        paintComponent(getGraphics());
    }
}
