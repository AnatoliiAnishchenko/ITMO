public abstract class Creature extends Thing implements LookingFor, Movable {
    public Creature() {
        super(null);
    }

    public Creature(String name) {
        super(name);
    }

    public Creature(String name, Area area) {
        super(name, area);
    }
}
