public interface Movable {
    Area getArea() throws EmptyGroup;

    void moveToArea(Area area);

    void setArea(Area area);
}