package state;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import actor.GameActor;
import map.GameMap;
import util.id.HasId;

public class GameState implements HasId, Serializable {

	private static final long serialVersionUID = 1449850847595123981L;

	protected long frameNumber;
	protected GameMap map;
	private Map<Long, GameActor> idToActors;

	public GameState(long frameNumber, GameMap map, Map<Long, GameActor> idToActors) {
		this.frameNumber = frameNumber;
		this.idToActors = idToActors;

	}

	public Map<Long, GameActor> getIdToActors() {
		return new HashMap<>(idToActors);
	}

	@Override
	public long getId() {
		return frameNumber;
	}

	public GameMap getMap() {
		return map;
	}

}
