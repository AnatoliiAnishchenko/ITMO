abstract class Item implements Breakable, Mineable, Constants {
    protected String name;
    protected double hp; // hp is the double in range of 0 to 1

    private String hpToString() {
        StringBuilder res = new StringBuilder();

        if (this.hp < 0) {
            System.err.println("HP of " + this.name + " isn't correct!!!");
        } else if (this.hp < 0.33 + EPS) {
            res.append("broken");
        } else if (this.hp < 0.66 + EPS) {
            res.append("battered");
        } else if (this.hp < 1.0) {
            res.append("shabby");
        } else if (Math.abs(this.hp - 1.0) < EPS) {
            res.append("new");
        } else {
            System.err.println("HP of " + this.name + " isn't correct!!!");
        }

        return res.toString();
    }

    @Override
    public boolean isBroken() {
        if (this.hp < 0) {
            System.err.println("HP of " + this.name + " isn't correct!!!");
            return true;
        }
        return this.hp < 0.33 + EPS;
    }

    @Override
    public void breakDown() {
        this.hp = this.hp * (Math.random() * 0.6 + 0.4);
    }

    @Override
    public void restore() {
        this.hp = 1.0d;
    }

    @Override
    public String toString() {
        return hpToString() + " " + this.name;
    }

    @Override
    public GoldPiece produce() {
        return null;
    }
}