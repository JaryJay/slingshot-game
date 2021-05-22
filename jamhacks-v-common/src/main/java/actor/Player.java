package actor;

import colour.Colour;
import processing.core.PApplet;

public class Player extends GameActor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7270791731913100896L;

	public Player() {
	}

	public Player(long id) {
		super(id);
	}

	@Override
	public void display(PApplet p) {
		Colour.fill(colour, p);
		p.ellipse(position.x, position.y, hitboxRadius, hitboxRadius);
	}

	@Override
	public GameActor copy() {
		Player ccopy = new Player(id);
		ccopy.setPosition(position.copy());
		ccopy.setVelocity(velocity.copy());
		return ccopy;
	}

}
