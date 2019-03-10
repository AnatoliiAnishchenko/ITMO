package MoominClasses;

public abstract class Thing extends MyObject {
    private Area area;

    public Thing() {
        super();
        this.area = null;
    }

    public Thing(String name) {
        super(name);
        this.area = null;
    }

    public Thing(String name, Area area) {
        super(name);
        this.area = area;
        area.addThing(this);
    }

    public void setArea(Area area) {
        this.area = area;
        area.addThing(this);
    }

    public Area getArea() {
        return area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Thing)) return false;
        if (!super.equals(o)) return false;
        Thing thing = (Thing) o;
        return getArea().equals(thing.getArea());
    }

    @Override
    public int hashCode() {
        return 0;
        //TODO
    }

    @Override
    public String toString() {
        return getName() + " in " + getArea();
    }
}