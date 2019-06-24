package moominClasses;

public class Field extends Area {
    private TypeOfField type;

    public Field (String name) {
        super (name);
        type = TypeOfField.OTHER;
    }

    public Field () {
        super ();
        type = TypeOfField.OTHER;
    }

    protected static enum TypeOfField {
        ROCK,
        SAND_GROUND,
        MEADOW,
        RIVER,
        MOOMIN_HOUSE,
        CANYON,
        OTHER;
    }
}
