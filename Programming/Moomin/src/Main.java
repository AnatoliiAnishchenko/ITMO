public class Main {
    private static MoominManager manager;

    public static void main(String[] args) {
        MainApp app = new MainApp();
        app.setVisible(true);

        manager = new MoominManager(app);

        manager.start();
    }

    public static MoominManager getManager() {
        return manager;
    }
}
