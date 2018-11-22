import ru.ifmo.se.pokemon.*;

public class Quilava extends Cyndaquil {
	public Quilava(String name, int lvl) {
		super(name, lvl);
		setStats(58, 64, 58, 80, 65, 80);
		setType(Type.FIRE);
		setMove(new FireBlast(), new PoisonJab(), new VitalThrow());
	}
}

class VitalThrow extends PhysicalMove {
	public VitalThrow() {
		super(Type.FIGHTING, 70, 1.0, -1, 1);
	}
	
	@Override
    protected boolean checkAccuracy(Pokemon att, Pokemon def) {
        return true;
    }
}