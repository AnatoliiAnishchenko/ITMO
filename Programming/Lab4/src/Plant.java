public class Plant extends Creature{
    Height height;
    public static enum Height {
        HIGH {
            @Override
            public String toString() {
                return "high";
            }
        },
        LOW {
            @Override
            public String toString() {
                return "low";
            }
        }
    }

    public Plant(String name){
        super(name);
        this.height = Height.LOW;
    }

    public Plant(String name, Height height){
        super(name);
        this.height = height;
    }

    public void grow(){
        System.out.printf("%1$s grow on the %2$s.\n", this.getName(), this.getArea().getName());
    }

    public Height getHeight() {
        return height;
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

    public void wobble(){
        System.out.printf("%1$s wobble. \n", this.getName());
    }
}