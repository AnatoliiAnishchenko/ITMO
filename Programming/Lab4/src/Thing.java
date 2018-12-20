import java.util.Objects;

public abstract class Thing {
    protected String name;
    protected Area area;

    public Thing(String name){
        this.name = name;
    }

    public Thing(String name, Area area){
        this.name = name;
        setArea(area);
    }

    public String getName(){
        return name;
    }

    public void setArea(Area area) {
        this.area = area;
        area.addThing(this);
    }

    public Area getArea() {
        return area;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof Thing)) return false;
        Thing thing = (Thing) that;
        return Objects.equals(name, thing.name) &&
                Objects.equals(area, thing.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, area);
    }

    @Override
    public String toString() {
        return name + " in " + area;
    }
}