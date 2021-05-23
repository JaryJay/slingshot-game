package event.servertoclient;

import java.util.List;

import event.input.GameInputFrame;

public class STCInputForwardEvent extends STCTestGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4580817513926767274L;
	private List<GameInputFrame> inputFrames;

	public STCInputForwardEvent(List<GameInputFrame> inputFrames) {
		this.inputFrames = inputFrames;
	}

	public List<GameInputFrame> getInputEvents() {
		return inputFrames;
	}

}
