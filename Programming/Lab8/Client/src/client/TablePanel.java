package client;

import javax.swing.*;
import java.awt.*;

class TablePanel extends JPanel {
    private MoominTable table;

    TablePanel() {
        super();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, ClientApp.SCREEN_PlANE_WIDTH - ClientApp.BORDER * 3 / 2, ClientApp.HEIGHT - 2 * ClientApp.BORDER - ClientApp.MENU_HEIGHT);

        table = new MoominTable();

        table.setPreferredSize(new Dimension(ClientApp.SCREEN_PlANE_WIDTH - ClientApp.BORDER * 3 / 2, ClientApp.HEIGHT - 2 * ClientApp.BORDER - ClientApp.MENU_HEIGHT));
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);

        scrollPane.setViewportView(table);
        add(scrollPane);
    }

    void updateMoomins() {
        table.updateModel();
        table.setVisible(false);
        table.setVisible(true);
    }

    void updateText() {
        for (int i = 0; i < MoominTable.MoominModel.columnCount; i++)
            table.getColumnModel().getColumn(i).setHeaderValue(table.getColumnName(i));
        table.getTableHeader().resizeAndRepaint();
    }
}
