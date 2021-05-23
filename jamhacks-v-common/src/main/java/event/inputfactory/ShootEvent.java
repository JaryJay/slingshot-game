package event.inputfactory;

import math.Vector2f;

public class ShootEvent extends SophisticatedInputEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -738888129010321761L;
	public Vector2f aimVector;
	public float strength;

	public ShootEvent(Vector2f aimVector, float strength) {
		this.aimVector = aimVector;
		this.strength = strength;
	}

	public Vector2f getAimVector() {
		return this.aimVector;
	}

	public float getStrength() {
		return this.strength;
	}

}
