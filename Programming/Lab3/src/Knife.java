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
}
