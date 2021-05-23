package event.servertoclient;

import state.GameState;

public class ConnectionAcceptanceEvent extends ServerToClientGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8108701449003594930L;
	private long userId;
	private GameState state;
	private long nextEventId;
	private long nextActorId;
	private long id;

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
		return "Connection accepted";
	}

	public long getNextEventId() {
		return nextEventId;
	}

	public void setNextEventId(long nextEventId) {
		this.nextEventId = nextEventId;
	}

	public long getNextActorId() {
		return nextActorId;
	}

	public void setNextActorId(long nextActorId) {
		this.nextActorId = nextActorId;
	}

	public void setFrameNumber(long id) {
		this.id = id;
	}
	
	public long getFrameNumber() {
		return id;
	}

}
