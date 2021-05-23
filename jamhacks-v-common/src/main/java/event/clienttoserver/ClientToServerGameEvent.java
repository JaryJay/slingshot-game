package event.clienttoserver;

import event.GameEvent;

public abstract class ClientToServerGameEvent extends GameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1762350623235198097L;
	private boolean acknowledged;

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
