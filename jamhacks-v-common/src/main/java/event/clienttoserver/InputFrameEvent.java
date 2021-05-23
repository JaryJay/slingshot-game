package event.clienttoserver;

import event.input.GameInputFrame;
import event.servertoclient.STCInputFrameEvent;

public class InputFrameEvent extends ClientToServerGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8637265818621317249L;
	private GameInputFrame inputFrame;
	private long frame;

	public long getFrame() {
		return frame;
	}

	public void setFrame(long frame) {
		this.frame = frame;
	}

	public GameInputFrame getInputFrame() {
		return inputFrame;
	}

	public void setInputFrame(GameInputFrame inputFrame) {
		this.inputFrame = inputFrame;
	}

	public STCInputFrameEvent toSTCEvent() {
		STCInputFrameEvent event = new STCInputFrameEvent(getTimeSent());
		event.setInputFrame(inputFrame);
		event.setFrame(frame);
		return event;
	}

	@Override
	public String getDescription() {
		String desc = "Input frame:\n";
		for (ClientToServerGameEvent event : inputFrame.getEvents()) {
			desc = desc + event.getDescription() + '\n';
		}
		return desc;
	}

}
