class Band extends Item {
    private static int ind = 1;

    Band (String name) {
        this.name = name;
        this.hp = 1.0d;
    }

    Band () {
        this.name = "band" + ind++;
        this.hp = 1.0d;
    }

    @Override
    public GoldPiece produce() {
        this.breakDown();
        return new GoldPiece();
    }
}