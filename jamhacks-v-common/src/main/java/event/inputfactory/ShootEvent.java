package event.inputfactory;

import event.clienttoserver.ClientToServerGameEvent;
import math.Vector2f;

public class ShootEvent extends ClientToServerGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2811947063934319456L;
	public Vector2f aimVector;

	public ShootEvent(Vector2f aimVector) {

	}

}
