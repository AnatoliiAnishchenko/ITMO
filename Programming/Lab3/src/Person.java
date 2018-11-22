import java.util.ArrayList;

class Person implements PickUpping, Wearing, Mining, Constants {
    private String name;
    private TypeOfPerson type;
    /*
    Items of the person.
     */
    private ArrayList<Item> items = new ArrayList<>();
    /*
    Mood of the person,
    If mood == 0 person is in the normal state
    else if moon > 0 person is happy
    else if < 0 person is sad
     */
    private double mood;

    Person (String name) {
        this.name = name;
        this.type = TypeOfPerson.CommonPerson;
        this.mood = 0.0d;
    }

    Person(String name, TypeOfPerson type) {
        this.name = name;
        this.type = type;
        this.mood = 0.0d;
    }

    private Person(Person that) {
        this.mood = that.mood;
        this.type = that.type;
        this.name = that.name;
        this.items = that.items;
    }

    boolean changeTypeOfPerson(TypeOfPerson type) {
        try {
            TypeOfPerson prevType = this.type;
            this.type = type;
            System.out.println(this.name + " changes type from " + prevType + " to " + this.type + ".");
            return true;
        } catch (Exception e) {
            System.err.println("Something go wrong!!!\n" +
                    "You can't change type of " + this.name + " from " + this.type + " to " + type + ".");
            return false;
        }
    }

    @Override
    public boolean wear(Clothes clothes) {
        if (clothes.isSizeFits(this)) {
            this.pickUp(clothes);
            System.out.println(this + " wears " + clothes + ".");
            return true;
        } else {
            System.out.println(this + " can't wear " + clothes + ".");
            return false;
        }
    }

    @Override
    public ArrayList<Clothes> getClothes() {
        ArrayList<Clothes> res = new ArrayList<>();

        for (Item item : this.items) {
            if (item.getClass() == Clothes.class) {
                res.add((Clothes) item);
            }
        }

        return res;
    }

    @Override
    public boolean pickUp(Item item) {
        if (items.add(item)) {
            System.out.println(this + " pick up " + item + ".");
            return true;
        } else {
            System.out.println(this + " can't pick up " + item + ".");
            return false;
        }
    }

    @Override
    public boolean drop(Item item) {
        if (!items.remove(item)) {
            System.out.println(this + " hasn't got " + item + ".");
            return false;
        } else {
            System.out.println(this + " dropped " + item + ".");
            return true;
        }
    }

    @Override
    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public boolean mine(Item item) {
        if (this.type != TypeOfPerson.GoldMiner) {
            System.out.println(this + " can't mine because he isn't a " + TypeOfPerson.GoldMiner + "!!!");
            return false;
        }
        GoldPiece gp = item.produce();
        if (gp == null) {
            System.out.println(item + " is not mineable!!!");
            return false;
        }
        if (items.add(gp)) {
            System.out.println(this + " mine " + gp + ".");
            return true;
        } else {
            System.err.println("Something go wrong!!!\n" +
                    this + " can't mine.");
            return false;
        }
    }

    @Override
    public ArrayList<GoldPiece> getGoldPieces() {
        ArrayList<GoldPiece> res = new ArrayList<>();

        for (Item item : this.items) {
            if (item.getClass() == GoldPiece.class) {
                res.add((GoldPiece) item);
            }
        }

        return res;
    }

    @Override
    public Person clone() {
        return new Person(this);
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || that.getClass() != this.getClass()) {
            return false;
        }
        Person other = (Person) that;
        return this.name.equals(other.name) && this.type.equals(other.type) &&
                this.items.equals(other.items) && (Math.abs(this.mood - other.mood) < EPS);
    }

    @Override
    public String toString() {
        return this.type + " " + this.name;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + this.type.hashCode() + this.items.hashCode() + Double.hashCode(this.mood);
    }
}