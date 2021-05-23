package event.inputfactory;

import math.Vector2f;

public class VelocityChangeEvent extends SophisticatedInputEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1435672838885949213L;
	private Vector2f velocityVector;

	public VelocityChangeEvent(float velocityX, float velocityY) {
		velocityVector = new Vector2f(velocityX, velocityY);
	}

	public Vector2f GetMoveVector() {
		return (this.velocityVector);
	}

	@Override
	public String getDescription() {
		return "A change of velocity to " + velocityVector;
	}

}
