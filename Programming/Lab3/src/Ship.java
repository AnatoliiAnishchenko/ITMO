class Ship extends Item {
    private static int ind = 1;

    Ship (String name) {
        this.name = name;
        this.hp = 1.0d;
    }

    Ship () {
        this.name = "ship" + ind++;
        this.hp = 1.0d;
    }
}
