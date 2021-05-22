package display;

import actor.GameActor;
import processing.core.PApplet;

public class Camera extends GameActor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4781424307948838094L;

	public Camera(long id) {
		super(id);
	}

	@Override
	public void display(PApplet p) {
		// Do nothing because camera should not be displayed.
	}

	@Override
	public GameActor copy() {
		Camera c = new Camera(id);
		c.setPosition(position.copy());
		c.setVelocity(velocity.copy());
		return c;
	}

}
