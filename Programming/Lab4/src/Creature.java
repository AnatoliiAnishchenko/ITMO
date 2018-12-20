public abstract class Creature extends Thing {
    public Creature(){
        super("Unnamed creature");
    }

    public Creature(String name){
        super(name);
    }

    public Creature(String name, Area area){
        super(name);
        this.area = area;
    }

    @Override
    public int hashCode() {
        //TODO
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        //TODO
        return super.equals(obj);
    }

    @Override
    public String toString() {
        //TODO
        return this.getName();
    }
}