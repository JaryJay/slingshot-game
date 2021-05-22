package event.servertoclient;

import event.GameEvent;

public abstract class ServerToClientGameEvent extends GameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1461276273996295430L;
	private boolean acknowledged;

	public ServerToClientGameEvent() {
	}

	public ServerToClientGameEvent(long id, long timeSent) {
		super(id, timeSent);
	}

	@Override
	public String getDescription() {
		return "A server to client game event.";
	}

	public boolean isAcknowledged() {
		return acknowledged;
	}

	public void setAcknowledged(boolean acknowledged) {
		this.acknowledged = acknowledged;
	}

}
