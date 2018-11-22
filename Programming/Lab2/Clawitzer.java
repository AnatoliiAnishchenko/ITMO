import ru.ifmo.se.pokemon.*;

public class Clawitzer extends Clauncher {
	public Clawitzer(String name, int lvl) {
		super(name, lvl);
		setStats(71, 73, 88, 120, 89, 59);
		setType(Type.WATER);
		setMove(new Scald(), new DoubleTeam(), new Rest(), new AncientPower());
	}
}

class AncientPower extends SpecialMove {
	public AncientPower() {
		super(Type.ROCK, 60, 1.0);
	}

	protected void applySelfEffects(Pokemon p) {
		if (Math.random() < 0.1) {
			p.setMod(Stat.ATTACK, 1);
			p.setMod(Stat.DEFENSE, 1);
			p.setMod(Stat.SPECIAL_ATTACK, 1);
			p.setMod(Stat.SPECIAL_DEFENSE, 1);
			p.setMod(Stat.SPEED, 1);
		}
	}
}
