import ru.ifmo.se.pokemon.*;
import static java.lang.Math.*;

public class Battleground {
    public static void main(String[] args) {
        Battle battle = new Battle();
        battle.addAlly(new Togedemaru("�����", 13));
		battle.addAlly(new Clauncher("����������", 17));
		battle.addAlly(new Clawitzer("��������", 15));
		battle.addFoe(new Cyndaquil("�������", 15));
		battle.addFoe(new Quilava("��������", 17));
		battle.addFoe(new Typhlosion("��������", 13));
		battle.go();
	}
}
