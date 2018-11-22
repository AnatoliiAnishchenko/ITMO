import java.util.ArrayList;

public interface Mining {
    boolean mine(Item item);
    ArrayList<GoldPiece> getGoldPieces();
}
