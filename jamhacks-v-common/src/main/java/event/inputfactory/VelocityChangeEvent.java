package event.inputfactory;

import event.clienttoserver.ClientToServerGameEvent;
import math.Vector2f;

public class VelocityChangeEvent extends ClientToServerGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4322031362243678381L;
	private Vector2f velocityVector;

	public VelocityChangeEvent(float velocityX, float velocityY) {
		velocityVector = new Vector2f(velocityX, velocityY);
	}

	public Vector2f GetMoveVector() {
		return (this.velocityVector);
	}
}
