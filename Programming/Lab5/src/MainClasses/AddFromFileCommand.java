package MainClasses;

public class AddFromFileCommand implements Command {
    private MoominManager manager;
    private String fileName;

    public AddFromFileCommand(MoominManager manager, String fileName) {
        this.manager = manager;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        manager.addDataFromFile(fileName);
    }
}
