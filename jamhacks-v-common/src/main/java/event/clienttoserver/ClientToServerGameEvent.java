package event.clienttoserver;

import event.GameEvent;

public abstract class ClientToServerGameEvent extends GameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2919174582596358248L;
	private boolean acknowledged;

	public ClientToServerGameEvent() {
	}

	public ClientToServerGameEvent(long id, long timeSent) {
		super(id, timeSent);
	}

	@Override
	public String getDescription() {
		return "A client to server game event.";
	}

	public boolean isAcknowledged() {
		return acknowledged;
	}

	public void setAcknowledged(boolean acknowledged) {
		this.acknowledged = acknowledged;
	}

}
