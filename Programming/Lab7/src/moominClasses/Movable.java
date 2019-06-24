package moominClasses;

import moominException.EmptyGroup;

public interface Movable {
    Field getField() throws EmptyGroup;

    void moveToField(Field field);

    void setField(Field field);
}