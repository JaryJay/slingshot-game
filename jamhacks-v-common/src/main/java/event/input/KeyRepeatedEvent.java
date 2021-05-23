package event.input;

public class KeyRepeatedEvent extends AbstractGameInputEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4698549053645251178L;
	public int keyCode;

	public KeyRepeatedEvent(int keyCode) {
		this.keyCode = keyCode;
	}

	public int getKeyCode() {
		return keyCode;
	}

}
