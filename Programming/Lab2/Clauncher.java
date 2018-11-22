import ru.ifmo.se.pokemon.*;

public class Clauncher extends Pokemon {
	public Clauncher(String name, int lvl) {
		super(name, lvl);
		setStats(50, 53, 62, 58, 63, 44);
		setType(Type.WATER);
		setMove(new Scald(), new DoubleTeam(), new Rest());
	}
}

class Scald extends SpecialMove {
	public Scald() {
		super(Type.WATER, 80, 1.0);
	}

	protected void applyOppEffects(Pokemon p) {
		if (Math.random() < 0.3) {
			Effect.burn(p);
		}
	}
}

class DoubleTeam extends StatusMove {
	public DoubleTeam() {
		super(Type.NORMAL, 0, 1.0);
	}

	@Override
	protected boolean checkAccuracy(Pokemon att, Pokemon def) {
		return true;
	}

	protected void applySelfEffects(Pokemon p) {
		p.setMod(Stat.EVASION, 1);
	}
}

class Rest extends StatusMove {
	public Rest() {
		super(Type.PSYCHIC, 0, 1.0);
	}

	@Override
	protected boolean checkAccuracy(Pokemon att, Pokemon def) {
		return true;
	}

	protected void applySelfEffects(Pokemon p) {
		p.setMod(Stat.HP, (int) (p.getHP() - p.getStat(Stat.HP)));
		Effect e = new Effect().turns(2).condition(Status.SLEEP);
		p.setCondition(e);
	}
}