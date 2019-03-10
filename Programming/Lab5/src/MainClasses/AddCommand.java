package MainClasses;

import MoominClasses.Moomin;

public class AddCommand implements Command {
    private MoominManager manager;
    private Moomin moomin;

    public AddCommand(MoominManager manager, Moomin moomin) {
        this.manager = manager;
        this.moomin = moomin;
    }

    @Override
    public void execute() {
        manager.addMoomin(moomin);
    }
}
