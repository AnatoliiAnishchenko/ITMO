package MainClasses;

public class LoadCommand implements Command {
    private MoominManager manager;
    private String fileName;

    public LoadCommand(MoominManager manager) {
        this.manager = manager;
        this.fileName = manager.getFileName();
    }

    public LoadCommand(MoominManager manager, String fileName) {
        this(manager);
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        manager.loadDataFromFile(fileName);
    }
}
