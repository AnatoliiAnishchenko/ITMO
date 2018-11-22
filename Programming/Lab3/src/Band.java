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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || that.getClass() != this.getClass()) {
            return false;
        }
        Band other = (Band) that;
        return this.name.equals(other.name) && (Math.abs(this.hp - other.hp) < EPS);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + Double.hashCode(this.hp);
    }
}