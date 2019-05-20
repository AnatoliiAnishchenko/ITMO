package moominClasses;

import java.util.ArrayList;

public class Sea extends Area implements Destructing, Throwing {
    private ArrayList<Island> islands;

    public Sea() {
        super();
        islands = new ArrayList<Island>();
    }

    public Sea(String name) {
        super(name);
        islands = new ArrayList<Island>();
    }

    @Override
    public void throwThing(Area area, Thing thing) {
        this.removeThing(thing);
        area.addThing(thing);
        println("The " + getName() + " has thrown " + thing.getName() + " on the " + area.getName() + ".");
    }

    @Override
    public Item destruct(Item item) {
        println(this + " destruct " + item + ".");
        String prevItem = item.toString();
        item.breakDown();
        println(prevItem + " -> " + item);
        return item.produce();
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