package event.servertoclient;

import event.GameEvent;

public abstract class ServerToClientGameEvent extends GameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1461276273996295430L;
	private boolean acknowledged;

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
