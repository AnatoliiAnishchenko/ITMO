package MainClasses;

import MoominClasses.Moomin;

public class RemoveCommand implements Command {
    private MoominManager manager;
    private Moomin moomin;

    public RemoveCommand(MoominManager manager, Moomin moomin) {
        this.manager = manager;
        this.moomin = moomin;
    }

    @Override
    public void execute() {
        manager.remove(moomin);
    }
}
