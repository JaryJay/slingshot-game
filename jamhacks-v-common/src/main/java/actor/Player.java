package actor;

import colour.Colour;
import math.Vector2f;
import processing.core.PApplet;

public class Player extends GameActor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4476557853000946229L;
	private Vector2f aimDirection;

	public Player() {
		position = new Vector2f(300, 300);
		velocity = new Vector2f(0, 0);
		aimDirection = new Vector2f(0, 1);
	}

	public Player(long id) {
		super(id);
		position = new Vector2f(300, 300);
		velocity = new Vector2f(1, 0);
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

	public Vector2f getAimDirection() {
		return aimDirection;
	}

	public void setAimDirection(Vector2f aimDirection) {
		this.aimDirection = aimDirection;
	}

}
