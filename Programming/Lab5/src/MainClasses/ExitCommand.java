package MainClasses;

public class ExitCommand implements Command {
    private MoominManager manager;

    public ExitCommand(MoominManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.exit();
    }
}
