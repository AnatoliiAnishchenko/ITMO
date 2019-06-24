package moominClasses;

import java.time.ZonedDateTime;

public abstract class Creature extends Thing implements LookingFor, Movable {
    public Creature() {
        super(null);
    }

    public Creature(String name) {
        super(name);
    }

    public Creature(String name, Field field) {
        super(name, field);
    }

    public Creature(ZonedDateTime time, String name, Field field) {
        super(time, name, field);
    }

}
