import ru.ifmo.se.pokemon.*;

public class Typhlosion extends Quilava {
	public Typhlosion(String name, int lvl) {
		super(name, lvl);
		setStats(78, 84, 78, 109, 85, 100);
		setType(Type.FIRE);
		setMove(new FireBlast(), new PoisonJab(), new VitalThrow(), new DoubleTeam());
	}
}
