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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || that.getClass() != this.getClass()) {
            return false;
        }
        Ship other = (Ship) that;
        return this.name.equals(other.name) && (Math.abs(this.hp - other.hp) < EPS);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + Double.hashCode(this.hp);
    }
}
