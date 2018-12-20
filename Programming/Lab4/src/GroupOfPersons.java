import sun.invoke.empty.Empty;

import java.util.Collections;
import java.util.ArrayList;

public class GroupOfPersons implements LookingFor, Movable {
    private ArrayList<Person> group = new ArrayList<Person>();
    private String name;

    public GroupOfPersons(Person ... persons) {
        Collections.addAll(this.group, persons);
        this.name = "They";
    }

    public ArrayList<Person> getGroup() {
        return group;
    }

    public void addPerson(Person person) {
        group.add(person);
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Person> divide() {
        System.out.println(this + " divide.");

        return group;
    }

    public void perform(Action action) {
        System.out.println("Everyone " + action.describe() + ".");
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void goLookingFor(Area area, Thing thing){
        try {
            moveToArea(area);
            //TODO
            System.out.printf("%1$s отправляется искать %2$s. \n", this.name, thing.name);
        } catch (ImpossibleDirection e) {
            System.out.println(LookingFor.discription);
        }
    }

    @Override
    public Area getArea() throws EmptyGroup {
        if (group.size() == 0) {
            throw new EmptyGroup("Group is empty!");
        }

        Area area = group.get(0).getArea();
        for (Person person : group) {
            if (!person.getArea().equals(area)) {
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

        for (Person person : group) {
            person.moveToArea(area);
        }
    }

    @Override
    public void setArea(Area area) {
        for (Person person : group) {
            person.setArea(area);
        }
    }

    //TODO equals ...
}
