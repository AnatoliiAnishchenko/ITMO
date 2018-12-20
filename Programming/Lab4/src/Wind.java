public class Wind extends Thing{
    public Wind(String name){
        super(name);
    }

    public Wind(){
        super("Wind");
    }

    public void blow(){
        boolean hasPlants = false;
        for (Thing thing : getArea().getThings()) {
            if(thing.getClass() == Plant.class) {
                hasPlants = true;
                break;
            }
        }
        if (hasPlants){
            System.out.printf("In the stems of plants growing at %2$s, rustling and whistling %1$s. \n",
                    this.getName(), getArea().getName());
        } else {
            System.out.printf("%1$s is blowing in %2$s. \n", this.getName(), getArea().getName());
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