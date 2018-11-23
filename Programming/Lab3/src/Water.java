import java.util.Objects;

abstract class Water implements Destructing, Constants {
    protected String name;

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof Water)) return false;
        Water water = (Water) that;
        return Objects.equals(name, water.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
