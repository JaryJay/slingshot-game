package event.clienttoserver;

import event.GameEvent;

public abstract class ClientToServerGameEvent extends GameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7441044370220405637L;

	public ClientToServerGameEvent() {
	}

	public ClientToServerGameEvent(long id, long timeSent) {
		super(id, timeSent);
	}

	@Override
	public String getDescription() {
		return "A client to server game event.";
	}

}
