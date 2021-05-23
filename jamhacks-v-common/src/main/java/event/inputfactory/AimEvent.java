package event.inputfactory;

import math.Vector2f;

public class AimEvent extends SophisticatedInputEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8800924315352743393L;
	private Vector2f aimDirection;

	public AimEvent(Vector2f aimDirection) {
		this.aimDirection = aimDirection;
	}

	public Vector2f getAimDirection() {
		return aimDirection;
	}

}
