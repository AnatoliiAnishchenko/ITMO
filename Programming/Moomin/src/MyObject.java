import java.util.Objects;

public abstract class MyObject {
    private String name;

    public MyObject() {
        name = null;
    }

    public MyObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void print(String str) {
        Main.getManager().print(str);
    }

    public void println(String str) {
        print(str + "\n");
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof MyObject)) return false;
        MyObject myObject = (MyObject) that;
        return Objects.equals(name, myObject.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}