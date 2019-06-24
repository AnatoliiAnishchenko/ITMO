package moominClasses;

import moominException.*;

import java.util.ArrayList;
import java.util.Collections;

public class GroupOfMoomins extends Creature {
    private ArrayList<Moomin> group;

    public GroupOfMoomins(Moomin... persons) {
        super("They");
        group = new ArrayList<>();
        Collections.addAll(this.group, persons);
        setField(this.getField());
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
    public void goLookingFor(Field field, Thing thing) {
        try {
            moveToField(field);
            println(getName() + " go looking for " + thing.getName() + ".");
        } catch (ImpossibleDirection exception) {
            System.out.println(LookingFor.discription);
        }
    }

    public Field getField() {
        if (group.size() == 0) {
            throw new EmptyGroup("Group is empty!");
        }

        Field field = group.get(0).getField();
        for (Moomin moomin : group) {
            if (!moomin.getField().equals(field)) {
                return null;
            }
        }
        return field;
    }

    @Override
    public void moveToField(Field field) {
        if (field == null) {
            throw new ImpossibleDirection("This is direction is not available.");
        }

        for (Moomin person : group) {
            person.moveToField(field);
        }

        super.setField(field);
    }

    @Override
    public void setField(Field field) {
        for (Moomin moomin : group) {
            moomin.setField(field);
        }
        super.setField(field);
    }

    //TODO equals ...
}