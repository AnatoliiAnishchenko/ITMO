package MoominClasses;

import MoominException.*;

import java.util.ArrayList;
import java.util.Collections;

public class GroupOfMoomins extends Creature {
    private ArrayList<Moomin> group;

    public GroupOfMoomins(Moomin... persons) {
        super("They");
        group = new ArrayList<>();
        Collections.addAll(this.group, persons);
        setArea(this.getArea());
    }

    public ArrayList<Moomin> getGroup() {
        return group;
    }

    public void addPerson(Moomin moomin) {
        group.add(moomin);
    }

    public ArrayList<Moomin> divide() {
        println(this + " divide.");

        return group;
    }

    public void perform(Action action) {
        println("Everyone " + action.describe() + ".");
    }

    @Override
    public void goLookingFor(Area area, Thing thing) {
        try {
            moveToArea(area);                                               
            println(getName() + " go looking for " + thing.getName() + ".");
        } catch (ImpossibleDirection exception) {
            System.out.println(LookingFor.discription);
        }
    }

    public Area getArea() {
        if (group.size() == 0) {
            throw new EmptyGroup("Group is empty!");
        }

        Area area = group.get(0).getArea();
        for (Moomin moomin : group) {
            if (!moomin.getArea().equals(area)) {
                return null;
            }
        }
        return area;
    }

    @Override
    public void moveToArea(Area area) {
        if (area == null) {
            throw new ImpossibleDirection("This is direction is not available.");
        }

        for (Moomin person : group) {
            person.moveToArea(area);
        }

        super.setArea(area);
    }

    @Override
    public void setArea(Area area) {
        for (Moomin moomin : group) {
            moomin.setArea(area);
        }
        super.setArea(area);
    }

    //TODO equals ...
}