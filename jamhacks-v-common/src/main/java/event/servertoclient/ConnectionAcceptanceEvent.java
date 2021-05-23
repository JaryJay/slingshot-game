package event.servertoclient;

import state.GameState;

public class ConnectionAcceptanceEvent extends ServerToClientGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8108701449003594930L;
	private long userId;
	private long nextEventId;
	private long nextActorId;
	private long id;
	private int numObstacles;

	public int getNumObstacles() {
		return numObstacles;
	}

	public void setNumObstacles(int numObstacles) {
		this.numObstacles = numObstacles;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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
