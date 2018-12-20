import com.sun.xml.internal.bind.v2.TODO;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Objects;

public class Person extends Creature implements PickUpping, Wearing, Mining, Constants, LookingFor, Movable {
    private TypeOfPerson type;
    private Gender gender;
    private Position position;
    private Condition condition;

    /*
    Items of the person.
     */
    private ArrayList<Item> items = new ArrayList<>();

    Person (String name, Gender gender) {
        super (name);
        this.type = TypeOfPerson.CommonPerson;
        this.gender = gender;
        this.condition = Condition.NORMAL;
        this.area = new Area();
    }

    Person () {
        super ("Somebody");
        this.type = TypeOfPerson.CommonPerson;
        this.gender = Gender.ELSE;
        this.condition = Condition.NORMAL;
        this.area = new Area();
    }

    Person(String name, TypeOfPerson type, Gender gender) {
        super (name);
        this.type = type;
        this.gender = gender;
        this.condition = Condition.NORMAL;
        this.area = new Area();
    }

    public TypeOfPerson getType() {
        return type;
    }

    public void setType(TypeOfPerson type) {
        this.type = type;
    }

    public void perform(Action action) {
        System.out.println(name + " " + action.describe() + ".");
    }

    public boolean wear(Clothes clothes) {
        this.pickUp(clothes);
        System.out.println(this + " wears " + clothes + ".");
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
    public void mine(Item item) {
        if (this.type != TypeOfPerson.GoldMiner) {
            System.out.println(this + " can't mine because he isn't a " + TypeOfPerson.GoldMiner + "!!!");
            return;
        }
        Item gp = item.produce();
        if (gp == null) {
            System.out.println(item + " is not mineable!!!");
            return;
        }
        if (items.add(gp)) {
            System.out.println(this + " mine " + gp + ".");
        } else {
            System.err.println("Something go wrong!!!\n" +
                    this + " can't mine.");
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

    public Gender getGender(){
        return this.gender;
    }

    @Override
    public void goLookingFor(Area area, Thing thing){
        try {
            System.out.printf("%1$s go looking for %2$s. \n", this.name, thing.name);
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

    public void lieNear(Thing thing){
        setPosition(Position.LIE);
        System.out.printf("%1$s lie near %2$s. \n", this.getName(), thing.getName());
    }

    public void see() {
        if (this.getPosition() == Position.LIE){
            StringBuilder str = new StringBuilder("%1$s see ");
            for(Thing thing : getArea().getThings()) {
                if(thing.getClass() == Plant.class){
                    Plant plant = (Plant) thing;
                    if(plant.getHeight() == Plant.Height.HIGH){
                        str.append(plant.getName()).append(", ");
                    }
                }
            }
            System.out.printf(str + "blue sky. \n", this.getName());
        } else if (this.getPosition() == Position.SIT) {
            StringBuilder str = new StringBuilder("%1$s see ");
            for(Thing thing : getArea().getThings()) {
                str.append(thing.getName()).append(", ");
            }
            System.out.printf(str + "dark walls. \n", this.getName());
        } else {
            System.out.println(this.name + " see everything in the neighborhood.");
        }
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Condition getCondition() {
        return condition;
    }

    public void sleep(){
        this.setCondition(Condition.SLEEP);
        System.out.printf("%1$s falls asleep. \n", this.getName());
    }

    public void think(String str){
        System.out.printf("\"%1$s\" - %2$s think. \n", str, this.getName());
    }

    @Override
    public void moveToArea(Area area) {
        if (area == null) {
            throw new ImpossibleDirection("This is direction is not available.");
        }

        System.out.printf("%1$s moved from %2$s to %3$s. \n", getName(), getArea().getName(), area.getName());
        setArea(area);
    }

    @Override
    public Area getArea() {
        return this.area;
    }

    @Override
    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return this.type + " " + this.name;
    }

    @Override
    public boolean equals(Object that) {
        //TODO
        if (this == that) return true;
        if (!(that instanceof Person)) return false;
        Person person = (Person) that;
        return Objects.equals(name, person.name) &&
                type == person.type &&
                Objects.equals(getItems(), person.getItems());
    }

    @Override
    public int hashCode() {
        //TODO
        return Objects.hash(name, type, getItems());
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