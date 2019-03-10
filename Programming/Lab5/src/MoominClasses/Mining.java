package MoominClasses;

import MoominClasses.GoldPiece;
import MoominClasses.Item;

import java.util.ArrayList;

public interface Mining {
    void mine(Item item);

    ArrayList<GoldPiece> getGoldPieces();
}