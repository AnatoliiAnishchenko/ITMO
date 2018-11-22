class Knife extends Item implements Destructing {
    private static int ind = 1;

    Knife (String name) {
        this.name = name;
        this.hp = 1.0d;
    }

    Knife () {
        this.name = "knife" + ind++;
        this.hp = 1.0d;
    }

    @Override
    public void destruct(Item item) {
        System.out.println(this + " destruct " + item);
        String prevItem = item.toString();
        item.breakDown();
        System.out.println(prevItem + " -> " + item);
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || that.getClass() != this.getClass()) {
            return false;
        }
        Knife other = (Knife) that;
        return this.name.equals(other.name) && (Math.abs(this.hp - other.hp) < EPS);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + Double.hashCode(this.hp);
    }
}
