import java.util.ArrayList;

public class MainStory {
    public static void main(String[] args) {
        Sea sea = new Sea("BlackSea");

        Person snork = new Person("Snork", TypeOfPerson.Picker);

        Knife knife = new Knife();
        snork.pickUp(knife);

        Band band = new Band();
        snork.mine(band);
        snork.changeTypeOfPerson(TypeOfPerson.GoldMiner);
        snork.mine(band);
        snork.mine(band);
        snork.mine(band);

        System.out.println(snork + " have:");
        for (Item item : snork.getItems()) {
            System.out.println("\t" + item);
        }

        ArrayList<GoldPiece> goldPieces = snork.getGoldPieces();
        GoldPiece gp0 = goldPieces.get(0);
        GoldPiece gp1 = goldPieces.get(1);
        int compareResult = gp0.compareTo(gp1);
        if (compareResult == 0) {
            System.out.println(gp0 + " have the same size as a " + gp1);
        } else if (compareResult < 0) {
            System.out.println(gp0 + " is smaller than " + gp1);
        } else {
            System.out.println(gp0 + " is bigger than " + gp1);
        }

        Ship ship = new Ship("BlackPearl");
        while (!ship.isBroken()) {
            sea.destruct(ship);
        }

        Person sniff = new Person("Sniff", TypeOfPerson.Picker);

        Clothes belt = new Clothes("belt");
        sea.destruct(belt);
        sniff.wear(belt);
    }
}