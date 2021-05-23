package event.input;

import math.Vector2f;

public class MousePressedEvent extends AbstractGameInputEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8006935436728608420L;
	private Vector2f MousePos = new Vector2f(0f, 0f);

	public MousePressedEvent(float MouseX, float MouseY) {
		this.MousePos.x = MouseX;
		this.MousePos.y = MouseY;
	}

	public Vector2f GetMousePos() {
		return (this.MousePos);
	}
}
