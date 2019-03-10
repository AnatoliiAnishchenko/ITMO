package MainClasses;

import MoominClasses.Moomin;

public class RemoveLowerCommand implements Command {
    private MoominManager manager;
    private Moomin moomin;

    public RemoveLowerCommand(MoominManager manager, Moomin moomin) {
        this.manager = manager;
        this.moomin = moomin;
    }

    @Override
    public void execute() {
        manager.removeLower(moomin);
    }
}
