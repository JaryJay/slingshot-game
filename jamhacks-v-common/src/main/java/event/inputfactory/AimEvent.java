package event.inputfactory;

import math.Vector2f;

public class AimEvent extends SophisticatedInputEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8175453979435633346L;
	private Vector2f aimDirection;

	public AimEvent(Vector2f aimDirection) {
		this.aimDirection = aimDirection;
	}

	public Vector2f getAimDirection() {
		return aimDirection;
	}

	@Override
	public String getDescription() {
		return "Aimed at " + aimDirection;
	}

}
