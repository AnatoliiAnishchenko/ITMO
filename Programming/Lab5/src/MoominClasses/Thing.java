package MoominClasses;

public abstract class Thing extends MyObject {
    private Field field;

    public Thing() {
        super();
        this.field = null;
    }

    public Thing(String name) {
        super(name);
        this.field = null;
    }

    public Thing(String name, Field field) {
        super(name);
        this.field = field;
        field.addThing(this);
    }

    public void setField(Field field) {
        this.field = field;
        field.addThing(this);
    }

    public Field getField() {
        return field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Thing)) return false;
        if (!super.equals(o)) return false;
        Thing thing = (Thing) o;
        return getField().equals(thing.getField());
    }

    @Override
    public int hashCode() {
        return 0;
        //TODO
    }

    @Override
    public String toString() {
        return getName() + " in " + getField();
    }
}