package moominException;

public class ImpossibleDirection extends IllegalArgumentException {
    public ImpossibleDirection(String message) {
        super(message);
    }
}