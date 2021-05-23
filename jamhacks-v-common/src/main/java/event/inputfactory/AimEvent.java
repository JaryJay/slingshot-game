package event.inputfactory;

import event.clienttoserver.ClientToServerGameEvent;
import math.Vector2f;

public class AimEvent extends ClientToServerGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6196191869943475614L;
	public Vector2f aimVector;

	public AimEvent(Vector2f aimVector) {
		this.aimVector = aimVector;
	}

	public Vector2f getAimVector() {
		return aimVector;
	}

}
