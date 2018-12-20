public interface Movable {
    public Area getArea() throws EmptyGroup;
    public void moveToArea(Area area);
    public void setArea(Area area);
}