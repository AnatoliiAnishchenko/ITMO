package MainClasses;

public class SaveCommand implements Command {
    private MoominManager manager;
    private String fileName;

    public SaveCommand(MoominManager manager) {
        this.manager = manager;
        this.fileName = manager.getFileName();
    }

    public SaveCommand(MoominManager manager, String fileName) {
        this(manager);
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        manager.saveDataToFile(fileName);
    }
}
