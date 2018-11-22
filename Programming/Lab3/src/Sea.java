class Sea extends Water {
    private static int ind = 1;

    Sea (String name) {
        this.name = name;
    }

    Sea () {
        this.name = "sea" + ind++;
    }

    @Override
    public void destruct(Item item) {
        System.out.println(this + " destruct " + item + ".");
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
        Sea other = (Sea) that;
        return this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
