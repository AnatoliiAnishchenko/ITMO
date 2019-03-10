import java.util.Objects;

class GoldPiece extends Item implements Comparable<GoldPiece> {
    private double size; // size is the double in range of 0 to 10
    private static int ind = 1;

    GoldPiece() {
        super("piece" + ind++ + " of gold");
        this.size = Math.random() * 10;
    }

    private String sizeToString() {
        StringBuilder res = new StringBuilder();
        if (this.size <= 0) {
            //TODO errprintln
            println("Size of " + getName() + " isn't correct!!!");
        } else if (this.size <= 2.5) {
            res.append("small");
        } else if (this.size <= 5.0) {
            res.append("medium");
        } else if (this.size <= 7.5) {
            res.append("large");
        } else if (this.size <= 10.0) {
            res.append("huge");
        } else {
            //TODO errprintln
            println("Size of " + getName() + " isn't correct!!!");
        }
        return res.toString();
    }

    @Override
    public void breakDown() {
    }

    @Override
    public String toString() {
        return sizeToString() + " " + getName();
    }

    @Override
    public int compareTo(GoldPiece that) {
        return Math.abs(this.size - that.size) < EPS ? 0 : ((this.size - that.size) >= EPS ? 1 : -1);
    }

    @Override
    public boolean equals(Object that) {
        //TODO
        if (this == that) return true;
        if (!(that instanceof GoldPiece)) return false;
        if (!super.equals(that)) return false;
        GoldPiece goldPiece = (GoldPiece) that;
        return Double.compare(goldPiece.size, size) == 0;
    }

    @Override
    public int hashCode() {
        //TODO
        return Objects.hash(super.hashCode(), size);
    }
}