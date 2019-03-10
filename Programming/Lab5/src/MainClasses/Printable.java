package MainClasses;

public interface Printable {
    default void print(String str) {
        Main.getManager().print(str);
    }

    default void println(String str) {
        print(str);
        print("\n");
    }
}
