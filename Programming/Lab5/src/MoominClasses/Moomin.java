package MoominClasses;

import MoominException.ImpossibleDirection;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Moomin extends Creature implements PickUpping, Wearing, Mining, Constants, Comparable<Moomin> {
    private TypeOfMoomin type;
    private Gender gender;
    private Position position;
    private Condition condition;

    private ArrayList<Item> items = new ArrayList<>();

    public Moomin(String name, Gender gender) {
        super(name);
        this.type = TypeOfMoomin.CommonMoomin;
        this.gender = gender;
        this.condition = Condition.NORMAL;
    }

    public Moomin() {
        super("Somebody");
        this.type = TypeOfMoomin.CommonMoomin;
        this.gender = Gender.ELSE;
        this.condition = Condition.NORMAL;
    }

    public Moomin(String name, TypeOfMoomin type, Gender gender) {
        super(name);
        this.type = type;
        this.gender = gender;
        this.condition = Condition.NORMAL;
    }

    public Moomin(String name, Area area, TypeOfMoomin type, Gender gender, Position position, Condition condition) {
        super(name, area);
        this.type = type;
        this.gender = gender;
        this.position = position;
        this.condition = condition;
    }

    public Moomin(JSONObject objJSON) {
        if (!objJSON.get("className").equals("MoominClasses.Moomin")) {
            throw new IllegalArgumentException("Object is not a MoominClasses.Moomin");
        }
        String name = "";
        Area area = null;
        TypeOfMoomin type = null;
        Gender gender = null;
        Position position = null;
        Condition condition = null;
        try {
            JSONObject curMoominData = (JSONObject) objJSON.get("data");
            name = (String) curMoominData.get("name");
            area = new Field((String) curMoominData.get("area"));
            type = Moomin.TypeOfMoomin.strToType((String) curMoominData.get("type"));
            gender = Moomin.Gender.strToGender((String) curMoominData.get("gender"));
            position = Moomin.Position.strToPosition((String) curMoominData.get("position"));
            condition = Moomin.Condition.strToCondition((String) curMoominData.get("condition"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        setName(name);
        setArea(area);
        setType(type);
        this.gender = gender;
        this.position = position;
        this.condition = condition;
    }

    public TypeOfMoomin getType() {
        return type;
    }

    public void setType(TypeOfMoomin type) {
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
        if (this.type != TypeOfMoomin.GoldMiner) {
            println(this + " can't mine because he isn't a " + TypeOfMoomin.GoldMiner + "!!!");
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

    @Override
    public JSONObject toJSON() {
        JSONObject curJSONObject = new JSONObject();
        JSONObject curMoominJSONData = new JSONObject();
        curMoominJSONData.put("name", getName());
        curMoominJSONData.put("area", getArea().toString());
        curMoominJSONData.put("type", getType().toString());
        curMoominJSONData.put("gender", getGender().toString());
        curMoominJSONData.put("position", getPosition().toString());
        curMoominJSONData.put("condition", getCondition().toString());
        curJSONObject.put("className", "MoominClasses.Moomin");
        curJSONObject.put("data", curMoominJSONData);

        return curJSONObject;
    }

    public void lieNear(Thing thing) {
        setPosition(Position.LIE);
        println(getName() + " lie near " + thing.getName() + ".");
    }

    public void see() {
        if (this.getPosition() == Position.LIE) {
            StringBuilder str = new StringBuilder(getName() + " see ");
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Moomin)) return false;
        if (!super.equals(o)) return false;
        Moomin moomin = (Moomin) o;
        return getType() == moomin.getType() &&
                getGender() == moomin.getGender() &&
                getPosition() == moomin.getPosition() &&
                getCondition() == moomin.getCondition();
    }

    @Override
    public int hashCode() {
        //TODO
        return 0;
    }

    @Override
    public int compareTo(Moomin that) {
        return this.getName().compareTo(that.getName());
    }

    public static enum Condition {
        NORMAL {
            @Override
            public String toString() {
                return "Normal";
            }
        },
        SLEEP {
            @Override
            public String toString() {
                return "Sleep";
            }
        };

        public static Condition strToCondition(String str) {
            switch (str) {
                case "Normal":
                    return NORMAL;
                case "Sleep":
                    return SLEEP;
                default:
                    throw new IllegalArgumentException(str + " - is not a correct condition of MoominClasses.Moomin");
            }
        }
    }

    public static enum Gender {
        MALE {
            @Override
            public String toString() {
                return "Male";
            }
        },
        FEMALE {
            @Override
            public String toString() {
                return "Female";
            }
        },
        ELSE {
            @Override
            public String toString() {
                return "Else";
            }
        };

        @Override
        public String toString() {
            return super.toString();
        }

        public static Gender strToGender(String str) {
            switch (str) {
                case "Male":
                    return MALE;
                case "Female":
                    return FEMALE;
                case "Else":
                    return ELSE;
                default:
                    throw new IllegalArgumentException(str + " - is not a correct gender of MoominClasses.Moomin");
            }
        }
    }

    public static enum Position {
        STAND {
            @Override
            public String toString() {
                return "Stand";
            }
        },
        LIE {
            @Override
            public String toString() {
                return "Lie";
            }
        },
        SIT {
            @Override
            public String toString() {
                return "Sit";
            }
        };

        public static Position strToPosition(String str) {
            switch (str) {
                case "Stand":
                    return STAND;
                case "Lie":
                    return LIE;
                case "Sit":
                    return SIT;
                default:
                    throw new IllegalArgumentException(str + " - is not a correct position of MoominClasses.Moomin");
            }
        }
    }

    public static enum TypeOfMoomin {
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
        CommonMoomin {
            @Override
            public String toString() {
                return "Common Moomin";
            }
        },
        CommonPerson {
            @Override
            public String toString() {
                return "Common Person";
            }
        };

        public static TypeOfMoomin strToType(String str) {
            switch (str) {
                case "Common Moomin":
                    return CommonMoomin;
                case "Picker":
                    return Picker;
                case "Gold Miner":
                    return GoldMiner;
                case "Common Person":
                    return CommonPerson;
                default:
                    throw new IllegalArgumentException(str + " - is not a correct type of MoominClasses.Moomin");
            }
        }
    }
}