package moominException;

public class EmptyGroup extends IllegalArgumentException {
    public EmptyGroup(String message) {
        super(message);
    }
}