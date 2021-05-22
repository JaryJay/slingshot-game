package event.servertoclient;

import state.MutableGameState;

public class ConnectionAcceptanceEvent extends ServerToClientGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8108701449003594930L;
	private long userId;
	private MutableGameState state;

	public ConnectionAcceptanceEvent(MutableGameState state) {
		this.state = state;
	}

	public ConnectionAcceptanceEvent(long id, long timeSent) {
		super(id, timeSent);
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public MutableGameState getState() {
		return state;
	}

	public void setState(MutableGameState state) {
		this.state = state;
	}

	@Override
	public String getDescription() {
		return "An acknowledgement to a StartingStateRequestEvent, and contains the starting state.";
	}

}
