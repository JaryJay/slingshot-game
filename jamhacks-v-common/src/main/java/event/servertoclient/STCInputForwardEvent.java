package event.servertoclient;

import event.input.AbstractGameInputEvent;

public class STCInputForwardEvent extends STCTestGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -522191320092073170L;
	private AbstractGameInputEvent inputEvent;

	public STCInputForwardEvent(AbstractGameInputEvent inputEvent) {
		this.inputEvent = inputEvent;
	}

	public AbstractGameInputEvent getInputEvent() {
		return inputEvent;
	}

}
