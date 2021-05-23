package event.input;

import math.Vector2f;

public class MouseDraggedGameInputEvent extends AbstractGameInputEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2974936284291314833L;
	private Vector2f currentMousePos = new Vector2f(0f, 0f);

	public MouseDraggedGameInputEvent(float MouseX, float MouseY) {
		this.currentMousePos.x = MouseX;
		this.currentMousePos.y = MouseY;
	}

	public Vector2f GetCurrentMousePos() {
		return (currentMousePos);
	}

}
