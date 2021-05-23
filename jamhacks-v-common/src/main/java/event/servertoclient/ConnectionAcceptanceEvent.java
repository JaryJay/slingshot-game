package event.servertoclient;

import state.GameState;

public class ConnectionAcceptanceEvent extends ServerToClientGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8108701449003594930L;
	private long userId;
	private GameState state;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	@Override
	public String getDescription() {
		return "An acknowledgement to a StartingStateRequestEvent, and contains the starting state.";
	}

}
