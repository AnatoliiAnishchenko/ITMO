import java.util.ArrayList;

public abstract class Area extends MyObject {
    private ArrayList<Thing> things;

    public Area() {
        super();
        things = new ArrayList<Thing>();
    }

    public Area(String name) {
        super(name);
        things = new ArrayList<Thing>();
    }

    public void addThing(Thing thing) {
        things.add(thing);
    }

    public void removeThing(Thing thing) {
        things.remove(thing);
    }

    public ArrayList<Thing> getThings() {
        return things;
    }

    public void setThings(ArrayList<Thing> things) {
        this.things.addAll(things);
    }

    @Override
    public boolean equals(Object that) {
        return false;
        //TODO
    }

    @Override
    public int hashCode() {
        return 0;
        //TODO
    }
}