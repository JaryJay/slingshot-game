package event.servertoclient;

import event.clienttoserver.ClientToServerGameEvent;
import event.input.GameInputFrame;

public class STCInputFrameEvent extends ServerToClientGameEvent {

	private GameInputFrame inputFrame;
	private long frame;

	public STCInputFrameEvent() {
	}

	public long getFrame() {
		return frame;
	}

	public void setFrame(long frame) {
		this.frame = frame;
	}

	public STCInputFrameEvent(long id, long timeSent) {
		super(id, timeSent);
	}

	public GameInputFrame getInputFrame() {
		return inputFrame;
	}

	public void setInputFrame(GameInputFrame inputFrame) {
		this.inputFrame = inputFrame;
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
