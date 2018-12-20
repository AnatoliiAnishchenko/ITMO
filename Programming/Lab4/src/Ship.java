public class Ship extends Item {
    private static int ind = 1;

    Ship (String name) {
        super (name);
    }

    Ship () {
        super ("ship" + ind++);
    }

    @Override
    public Item produce() {
        return new Item("wreckage");
    }
}
