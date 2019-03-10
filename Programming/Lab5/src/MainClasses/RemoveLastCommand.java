package MainClasses;

public class RemoveLastCommand implements Command {
    private MoominManager manager;

    public RemoveLastCommand(MoominManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.remove(manager.getMoomins().size() - 1);
    }
}
