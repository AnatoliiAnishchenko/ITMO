public class Plant extends Thing {
    private Height height;

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

    public Plant() {
        super();
        this.height = Height.LOW;
    }

    public Plant(String name) {
        super(name);
        this.height = Height.LOW;
    }

    public Plant(String name, Area area) {
        super(name, area);
        this.height = Height.LOW;
    }

    public Plant(String name, Height height) {
        super(name);
        this.height = height;
    }

    public Plant(String name, Area area, Height height) {
        super(name, area);
        this.height = height;
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public void grow() {
        println(getName() + " grow on the " + getArea().getName() + ".");
    }

    public void wobble() {
        println(getName() + " wobble.");
    }

    @Override
    public int hashCode() {
        //TODO
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        //TODO
        return false;
    }

    @Override
    public String toString() {
        //TODO
        return "";
    }
}