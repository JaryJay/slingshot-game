package event.inputfactory;

import math.Vector2f;

public class VelocityChangeEvent {

	Vector2f velocityVector;

	public VelocityChangeEvent(float MoveX, float MoveY) {
		velocityVector.x = MoveX;
		velocityVector.y = MoveY;
	}

	public Vector2f GetMoveVector() {
		return (this.velocityVector);
	}
}
