package MainClasses;

public class Main {
    private static MoominManager manager;

    public static void main(String[] args) {
        manager = new MoominManager(args[0]);
        manager.start();
    }

    public static MoominManager getManager() {
        return manager;
    }
}
