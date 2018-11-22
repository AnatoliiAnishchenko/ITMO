import ru.ifmo.se.pokemon.*;

public class Cyndaquil extends Pokemon {
	public Cyndaquil(String name, int lvl) {
		super(name, lvl);
		setStats(39, 52, 43, 60, 50, 65);
		setType(Type.FIRE);
		setMove(new FireBlast(), new PoisonJab());
	}
}

class FireBlast extends SpecialMove {
	public FireBlast() {
		super(Type.FIRE, 110, 0.85);
	}

	protected void applyOppEffects(Pokemon p) {
		if (Math.random() < 0.1) {
			Effect.burn(p);
		}
	}
}

class PoisonJab extends PhysicalMove {
	public PoisonJab() {
		super(Type.POISON, 80, 1.0);
	}
	
	protected void applyOppEffects(Pokemon p) {
		if (Math.random() < 0.3) {
			Effect.poison(p);
		}
	}
}