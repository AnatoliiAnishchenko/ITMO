package client;

import client.localization.LocalizationManager;

import javax.swing.*;

class MoominMenu extends JMenuBar {
    private static LocalizationManager localizationManager;

    private JMenu file, language, view;
    private JMenuItem screen;
    private JMenuItem table;
    private JMenuItem exit;

    MoominMenu(ClientApp clientApp) {
        super();
        localizationManager = ClientApp.localizationManager;

        file = new JMenu();
        language = new JMenu();
        view = new JMenu();

        screen = new JMenuItem();
        table = new JMenuItem();
        exit = new JMenuItem();

        init();

        screen.addActionListener(e -> ClientApp.goToScreenPanel());
        table.addActionListener(e -> ClientApp.goToTablePanel());
        exit.addActionListener(e -> clientApp.exit());

        JMenuItem ru = new JMenuItem("Русский");
        ru.addActionListener(e -> {
            localizationManager.setLocale("RU");
            clientApp.updatePanels();
        });
        JMenuItem uk = new JMenuItem("Український");
        uk.addActionListener(e -> {
            localizationManager.setLocale("UA");
            clientApp.updatePanels();
        });
        JMenuItem nl = new JMenuItem("Nederlands");
        nl.addActionListener(e -> {
            localizationManager.setLocale("NL");
            clientApp.updatePanels();
        });
        JMenuItem es = new JMenuItem("Español");
        es.addActionListener(e -> {
            localizationManager.setLocale("PR");
            clientApp.updatePanels();
        });

        file.add(exit);

        language.add(ru);
        language.add(uk);
        language.add(nl);
        language.add(es);

        view.add(screen);
        view.add(table);

        add(file);
        add(language);
        add(view);
    }

    private void init() {
        file.setText(localizationManager.getNativeButton(LocalizationManager.BUTTON_FILE));
        language.setText(localizationManager.getNativeButton(LocalizationManager.BUTTON_LANGUAGE));
        view.setText(localizationManager.getNativeButton(LocalizationManager.BUTTON_VIEW));

        screen.setText(localizationManager.getNativeButton(LocalizationManager.BUTTON_SCREEN));
        table.setText(localizationManager.getNativeButton(LocalizationManager.BUTTON_TABLE));
        exit.setText(localizationManager.getNativeButton(LocalizationManager.BUTTON_EXIT));
    }

    void updateText() {
        init();
    }
}
