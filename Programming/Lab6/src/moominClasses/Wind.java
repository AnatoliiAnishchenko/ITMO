package moominClasses;

public class Wind extends Thing{
    public Wind(String name){
        super(name);
    }

    public Wind(){
        super("moominClasses.Wind");
    }

    public void blow(){
        boolean hasPlants = false;
        for (Thing thing : getField().getThings()) {
            if(thing.getClass() == Plant.class) {
                hasPlants = true;
                break;
            }
        }
        if (hasPlants){
            println("In the stems of plants growing at " + getField().getName() + ", rustling and whistling "
                    + getName() + ".");
        } else {
            System.out.printf(getName() + " is blowing in " + getField().getName() + ".");
        }
    }

    @Override
    public int hashCode() {
        //TODO
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        //TODO
        return super.equals(obj);
    }

    @Override
    public String toString() {
        //TODO
        return super.toString();
    }
}