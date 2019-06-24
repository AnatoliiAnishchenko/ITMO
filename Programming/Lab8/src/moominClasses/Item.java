package moominClasses;

public class Item extends Thing implements Breakable, Mineable, Constants {
    double hp; // hp is the double in range of 0 to 1

    public Item(String name) {
        super(name);
        this.hp = 1.0d;
    }
                        
    private String hpToString() {
        StringBuilder res = new StringBuilder();

        if (this.hp < 0) {
            //TODO errprintln
            println("HP of " + getName() + " isn't correct!!!");
        } else if (this.hp < 0.33 + EPS) {
            res.append("broken");
        } else if (this.hp < 0.66 + EPS) {
            res.append("battered");
        } else if (this.hp < 1.0) {
            res.append("shabby");
        } else if (Math.abs(this.hp - 1.0) < EPS) {
            res.append("new");
        } else {
            //TODO errprintln
            println("HP of " + getName() + " isn't correct!!!");
        }

        return res.toString();
    }

    @Override
    public boolean isBroken() {
        if (this.hp < 0) {
            //TODO errprintln
            println("HP of " + getName() + " isn't correct!!!");
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
    public Item produce() {
        return null;
    }

    @Override
    public boolean equals(Object that) {
        return false;
        //TODO
    }

    @Override
    public int hashCode() {
        //TODO
        return 0;
    }

    @Override
    public String toString() {
        return hpToString() + " " + getName();
    }
}
