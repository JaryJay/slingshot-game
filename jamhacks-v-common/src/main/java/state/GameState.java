package state;

import java.io.Serializable;
import java.util.Map;

import actor.GameActor;
import util.id.HasId;

public abstract class GameState implements HasId, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1729139781321986641L;
	protected long stateId;
	protected long frameNumber;

	public abstract GameState getNextState();

	public abstract Map<Long, GameActor> getIdToActors();

	@Override
	public long getId() {
		return stateId;
	}

	public long getFrameNumber() {
		return frameNumber;
	}

}
