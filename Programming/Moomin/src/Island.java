import java.util.ArrayList;

public class Island extends Area {
    private ArrayList<Field> fields;

    public Island() {
        super();
        fields = new ArrayList<Field>();
    }

    public Island(String name) {
        super(name);
        fields = new ArrayList<Field>();
    }

    @Override
    public boolean equals(Object that) {
        return false;
        //TODO
    }

    @Override
    public int hashCode() {
        return 0;
        //TODO
    }
}
