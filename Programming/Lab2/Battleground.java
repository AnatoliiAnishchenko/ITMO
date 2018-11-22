import ru.ifmo.se.pokemon.*;
import static java.lang.Math.*;

public class Battleground {
    public static void main(String[] args) {
        Battle battle = new Battle();
        battle.addAlly(new Togedemaru("Гурин", 13));
		battle.addAlly(new Clauncher("Шайхотаров", 17));
		battle.addAlly(new Clawitzer("Анищенко", 15));
		battle.addFoe(new Cyndaquil("Письмак", 15));
		battle.addFoe(new Quilava("Гаврилов", 17));
		battle.addFoe(new Typhlosion("Перминов", 13));
		battle.go();
	}
}
