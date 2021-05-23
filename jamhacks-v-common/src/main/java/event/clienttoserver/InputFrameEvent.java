package event.clienttoserver;

import event.input.GameInputFrame;

public class InputFrameEvent extends ClientToServerGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7713758653827479136L;
	private GameInputFrame inputFrame;

	public InputFrameEvent() {
	}

	public InputFrameEvent(long id, long timeSent) {
		super(id, timeSent);
	}

	public GameInputFrame getInputFrame() {
		return inputFrame;
	}

	public void setInputFrame(GameInputFrame inputFrame) {
		this.inputFrame = inputFrame;
	}

}
