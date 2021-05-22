package event.input;

public class KeyPressedGameInputEvent extends AbstractGameInputEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6614791829054665219L;
	private int keyCode;

	public KeyPressedGameInputEvent(int keyCode) {
		this.keyCode = keyCode;
	}

	public int getKeyCode() {
		return keyCode;
	}

}
