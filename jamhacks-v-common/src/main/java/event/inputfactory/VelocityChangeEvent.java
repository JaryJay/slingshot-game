package event.inputfactory;

import event.clienttoserver.ClientToServerGameEvent;
import math.Vector2f;

public class VelocityChangeEvent extends ClientToServerGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4322031362243678381L;
	Vector2f velocityVector;

	public VelocityChangeEvent(float MoveX, float MoveY) {
		velocityVector.x = MoveX;
		velocityVector.y = MoveY;
	}

	public Vector2f GetMoveVector() {
		return (this.velocityVector);
	}
}
