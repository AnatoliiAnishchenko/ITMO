class Clothes extends Item {
    Clothes (String name) {
        this.name = name;
        this.hp = 1.0d;
    }

    boolean isSizeFits(Person person) {
        return true;
        // TODO: 22.11.2018
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || that.getClass() != this.getClass()) {
            return false;
        }
        Clothes other = (Clothes) that;
        return this.name.equals(other.name) && (Math.abs(this.hp - other.hp) < EPS);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + Double.hashCode(this.hp);
    }
}

