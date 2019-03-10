import java.util.ArrayList;

public class Moomin extends Creature implements PickUpping, Wearing, Mining, Constants {
    private TypeOfPerson type;
    private Gender gender;
    private Position position;
    private Condition condition;

    private ArrayList<Item> items = new ArrayList<>();

    public Moomin(String name, Gender gender) {
        super(name);
        this.type = TypeOfPerson.CommonPerson;
        this.gender = gender;
        this.condition = Condition.NORMAL;
    }

    public Moomin() {
        super("Somebody");
        this.type = TypeOfPerson.CommonPerson;
        this.gender = Gender.ELSE;
        this.condition = Condition.NORMAL;
    }

    public Moomin(String name, TypeOfPerson type, Gender gender) {
        super(name);
        this.type = type;
        this.gender = gender;
        this.condition = Condition.NORMAL;
    }

    public TypeOfPerson getType() {
        return type;
    }

    public void setType(TypeOfPerson type) {
        this.type = type;
    }

    public void perform(Action action) {
        println(getName() + " " + action.describe() + ".");
    }

    public boolean wear(Clothes clothes) {
        this.pickUp(clothes);
        println(this + " wears " + clothes + ".");
        return true;
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
            println(this + " pick up " + item + ".");
            return true;
        } else {
            println(this + " can't pick up " + item + ".");
            return false;
        }
    }

    @Override
    public boolean drop(Item item) {
        if (!items.remove(item)) {
            println(this + " hasn't got " + item + ".");
            return false;
        } else {
            println(this + " dropped " + item + ".");
            return true;
        }
    }

    @Override
    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public void mine(Item item) {
        if (this.type != TypeOfPerson.GoldMiner) {
            println(this + " can't mine because he isn't a " + TypeOfPerson.GoldMiner + "!!!");
            return;
        }
        Item gp = item.produce();
        if (gp == null) {
            println(item + " is not mineable!!!");
            return;
        }
        if (items.add(gp)) {
            println(this + " mine " + gp + ".");
        } else {
            //TODO errprintln
            println("Something go wrong!!!\n" + this + " can't mine.");
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

    public Gender getGender() {
        return this.gender;
    }

    @Override
    public void goLookingFor(Area area, Thing thing) {
        try {
            println(getName() + " go looking for " + thing.getName() + ".");
            moveToArea(area);
        } catch (ImpossibleDirection e) {
            System.out.println(LookingFor.discription);
        }
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void lieNear(Thing thing) {
        setPosition(Position.LIE);
        println(getName() + " lie near " + thing.getName() + ".");
    }

    public void see() {
        if (this.getPosition() == Position.LIE) {
            StringBuilder str = new StringBuilder(getName()+ " see ");
            for (Thing thing : getArea().getThings()) {
                if (thing.getClass() == Plant.class) {
                    Plant plant = (Plant) thing;
                    if (plant.getHeight() == Plant.Height.HIGH) {
                        str.append(plant.getName()).append(", ");
                    }
                }
            }
            println(str + "blue sky.");
        } else if (this.getPosition() == Position.SIT) {
            StringBuilder str = new StringBuilder(getName() + " see ");
            for (Thing thing : getArea().getThings()) {
                str.append(thing.getName()).append(", ");
            }
            println(str + "dark walls.");
        } else {
            println(getName() + " see everything in the neighborhood.");
        }
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Condition getCondition() {
        return condition;
    }

    public void sleep() {
        this.setCondition(Condition.SLEEP);
        println(getName() + " falls asleep.");
    }

    public void think(String str) {
        println("\"str\" - " + getName() + " think.");
    }

    @Override
    public void moveToArea(Area area) {
        if (area == null) {
            throw new ImpossibleDirection("This is direction is not available.");
        }

        println(getName() + " moved from " + getArea().getName() + " to " + area.getName() + ".");
        setArea(area);
    }

    @Override
    public String toString() {
        return this.type + " " + getName();
    }

    @Override
    public boolean equals(Object that) {
        //TODO
        return false;
    }

    @Override
    public int hashCode() {
        //TODO
        return 0;
    }

    public static enum Condition {
        NORMAL,
        SLEEP;

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public static enum Gender {
        MALE,
        FEMALE,
        ELSE;

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public static enum Position {
        STAND,
        LIE,
        SIT;

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public static enum TypeOfPerson {
        GoldMiner {
            @Override
            public String toString() {
                return "Gold Miner";
            }
        },
        Picker {
            @Override
            public String toString() {
                return "Picker";
            }
        },
        CommonMumi {
            @Override
            public String toString() {
                return "Common Mumi";
            }
        },
        CommonPerson {
            @Override
            public String toString() {
                return "Common Person";
            }
        }
    }
}