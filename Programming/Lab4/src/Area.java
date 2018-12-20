import java.util.ArrayList;
import java.util.Objects;

public class Area {
    private String name;
    private ArrayList<Thing> things = new ArrayList<Thing>();

    public Area (){
        name = "Somewhere";
    }

    public Area (String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
       return name;
    }

    public void addThing(Thing thing){
        things.add(thing);
    }

    public void removeThing(Thing thing){
        things.remove(thing);
    }

    public ArrayList<Thing> getThings(){
        return things;
    }

    @Override
    public int hashCode() {
        //TODO
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object that) {
        //TODO
        if (this == that) return true;
        if (!(that instanceof Area)) return false;
        Area area = (Area) that;
        return Objects.equals(getName(), area.getName());
    }
}
