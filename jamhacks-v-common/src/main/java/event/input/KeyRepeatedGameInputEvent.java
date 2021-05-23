package event.input;

public class KeyRepeatedGameInputEvent extends AbstractGameInputEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4698549053645251178L;
	public int keyCode;

	public KeyRepeatedGameInputEvent(int keyCode) {
		this.keyCode = keyCode;
	}

	public int getKeyCode() {
		return keyCode;
	}

}
