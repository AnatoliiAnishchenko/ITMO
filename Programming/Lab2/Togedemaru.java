import ru.ifmo.se.pokemon.*;

public class Togedemaru extends Pokemon {
	public Togedemaru(String name, int lvl) {
		super(name, lvl);
		setStats(65, 98, 63, 40, 73, 96);
		setType(Type.ELECTRIC, Type.STEEL);
		setMove(new Pound(), new FeatherDance(), new DoubleHit(), new RockTomb());
	}
}

class Pound extends PhysicalMove {
	public Pound() {
		super(Type.NORMAL, 40, 1.0);
	}
}

class FeatherDance extends StatusMove {
	public FeatherDance() {
		super(Type.FLYING, 0, 1.0);
	}
	
	protected void applyOppEffects(Pokemon p) {
		p.setMod(Stat.ATTACK, -2);
	}
}

class DoubleHit extends PhysicalMove {
	public DoubleHit() {
		super(Type.NORMAL, 35, 0.9, 0, 2);
	}
}

class RockTomb extends PhysicalMove {
	public RockTomb() {
		super(Type.ROCK, 60, 0.95);
	}
	
	protected void applyOppEffects(Pokemon p) {
		p.setMod(Stat.SPEED, -1);
	}
}