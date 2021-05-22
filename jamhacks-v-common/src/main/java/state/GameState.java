package state;

import java.io.Serializable;
import java.util.Map;

import actor.GameActor;
import map.GameMap;
import util.id.HasId;

public abstract class GameState implements HasId, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5501750926373642318L;
	protected long stateId;
	protected long frameNumber;
	protected GameMap map;

	public abstract GameState getNextState();

	public abstract Map<Long, GameActor> getIdToActors();

	@Override
	public long getId() {
		return stateId;
	}

	public long getFrameNumber() {
		return frameNumber;
	}

	public GameMap getMap() {
		return map;
	}

}
