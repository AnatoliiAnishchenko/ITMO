package MainClasses;

public class InfoCommand implements Command {
    private MoominManager manager;

    public InfoCommand(MoominManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.info();
    }
}
