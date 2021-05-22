package event.input;

import math.Vector2f;

public class MouseReleasedGameInputEvent extends AbstractGameInputEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1101104179299256313L;
	private Vector2f MousePos = new Vector2f(0f, 0f);

	public MouseReleasedGameInputEvent(float MouseX, float MouseY) {
		this.MousePos.x = MouseX;
		this.MousePos.y = MouseY;
	}

	public Vector2f GetMousePos() {
		return (this.MousePos);
	}

}
