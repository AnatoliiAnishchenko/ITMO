package MoominClasses;

import MoominException.EmptyGroup;

public interface Movable {
    Field getField() throws EmptyGroup;

    void moveToField(Field field);

    void setField(Field field);
}