public class Sea extends Area implements Destructing, Throwing {
    private static int ind = 1;

    public Sea(){
        super("Sea" + ind++);
    }

    public Sea(String name){
        super(name);
    }

    public void throwThing(Area area, Thing thing){
        this.removeThing(thing);
        area.addThing(thing);
        System.out.printf("The %1$s has thrown %2$s on the %3$s. \n", getName(), thing.getName(), area.getName());
    }

    @Override
    public String toString() {
        //TODO
        return super.toString();
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
    public Item destruct(Item item) {
        System.out.println(this + " destruct " + item + ".");
        String prevItem = item.toString();
        item.breakDown();
        System.out.println(prevItem + " -> " + item);
        return item.produce();
    }
}