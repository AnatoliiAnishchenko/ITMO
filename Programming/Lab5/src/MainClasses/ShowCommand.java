package MainClasses;

public class ShowCommand implements Command {
    private MoominManager manager;
    public ShowCommand (MoominManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.show();
    }
}
