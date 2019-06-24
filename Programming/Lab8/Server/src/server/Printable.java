package server;

public interface Printable {
    default void print(String str) {
        ClientThread.getManager().print(str);
    }

    default void println(String str) {
        print(str);
        print("\n");
    }
}
