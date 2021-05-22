package actor;

import colour.Colour;
import processing.core.PApplet;

public class Projectile extends GameActor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -477220136653140555L;

	public Projectile() {
	}

	protected Projectile(long id) {
		super(id);
	}

	@Override
	public void display(PApplet p) {
		Colour.fill(colour, p);
		p.ellipse(position.x, position.y, hitboxRadius, hitboxRadius);
	}

	@Override
	public GameActor copy() {
		Projectile copy = new Projectile(id);
		copy.setPosition(position.copy());
		copy.setVelocity(velocity.copy());
		return copy;
	}

}
