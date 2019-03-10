package MainClasses;

public class RemoveFirstCommand implements Command {
    private MoominManager manager;

    public RemoveFirstCommand(MoominManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.remove(0);
    }
}
