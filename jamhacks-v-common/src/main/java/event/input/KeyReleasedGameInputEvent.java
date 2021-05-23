package event.input;

public class KeyReleasedGameInputEvent extends AbstractGameInputEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5103923425595460953L;
	public int keyCode;

	public KeyReleasedGameInputEvent(int keyCode) {
		this.keyCode = keyCode;
	}

	public int getKeyCode() {
		return this.keyCode;
	}
}
