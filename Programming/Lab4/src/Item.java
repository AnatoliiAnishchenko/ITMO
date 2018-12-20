import java.util.Objects;

public class Item extends Thing implements Breakable, Mineable, Constants, Cloneable {
    double hp; // hp is the double in range of 0 to 1

    public Item(String name) {
        super(name);
        this.hp = 1.0d;
    }

    public Item(Item that) {
        super(that.name);
        this.hp = that.hp;
    }

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
    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof Item)) return false;
        Item item = (Item) that;
        return Double.compare(item.hp, hp) == 0 &&
                Objects.equals(name, item.name);
    }

    @Override
    public Item produce() {
        return null;
    }

    @Override
    public int hashCode() {
        //TODO
        return Objects.hash(name, hp);
    }

    @Override
    public String toString() {
        //TODO
        return hpToString() + " " + this.name;
    }

    @Override
    public Item clone() {
        //TODO
        return new Item(this);
    }
}