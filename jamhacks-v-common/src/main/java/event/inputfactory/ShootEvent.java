package event.inputfactory;

import event.clienttoserver.ClientToServerGameEvent;
import math.Vector2f;

public class ShootEvent extends ClientToServerGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -738888129010321761L;
	public Vector2f aimVector;

	public ShootEvent(Vector2f aimVector) {
		this.aimVector = aimVector;
	}

	public Vector2f getAimVector() {
		return aimVector;
	}

}
