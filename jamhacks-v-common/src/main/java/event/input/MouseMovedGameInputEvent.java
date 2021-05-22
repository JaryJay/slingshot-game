package event.input;

import math.Vector2f;

public class MouseMovedGameInputEvent extends AbstractGameInputEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2974936284291314833L;
	private Vector2f MovedMousePos = new Vector2f(0f, 0f);

	public MouseMovedGameInputEvent(float MouseX, float MouseY) {
		this.MovedMousePos.x = MouseX;
		this.MovedMousePos.y = MouseY;
	}

	public Vector2f GetChangedMousePos() {
		return (MovedMousePos);
	}

}
