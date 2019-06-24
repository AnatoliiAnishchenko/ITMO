package MainClasses;

public class SortCommand implements Command {
    private MoominManager manager;

    public SortCommand(MoominManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.sort();
    }
}
