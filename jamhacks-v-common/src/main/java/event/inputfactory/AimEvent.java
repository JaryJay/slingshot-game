package event.inputfactory;

import event.clienttoserver.ClientToServerGameEvent;
import math.Vector2f;

public class AimEvent extends ClientToServerGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6196191869943475614L;
	private Vector2f mousePosOnClick;
	private Vector2f currentMousePos;

	public AimEvent(Vector2f mousePosOnClick, Vector2f currentMousePos) {
		this.mousePosOnClick = mousePosOnClick;
		this.currentMousePos = currentMousePos;

	}

	public Vector2f getMousePosOnClick() {
		return mousePosOnClick;
	}

	public Vector2f getCurrentMousePos() {
		return currentMousePos;
	}

}
