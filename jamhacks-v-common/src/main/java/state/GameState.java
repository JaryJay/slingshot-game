package state;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import actor.GameActor;
import map.GameMap;
import util.id.HasId;

public class GameState implements HasId, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2068336288448314922L;
	protected long frameNumber;
	protected GameMap map;
	private Map<Long, GameActor> actorIdToActors;

	public GameState(long frameNumber, GameMap map, Map<Long, GameActor> idToActors) {
		this.frameNumber = frameNumber;
		this.actorIdToActors = idToActors;

	}

	public Map<Long, GameActor> getActorIdToActors() {
		Set<Long> keySet = actorIdToActors.keySet();
		HashMap<Long, GameActor> hashMap = new HashMap<>();

		for (Long l : keySet) {
			hashMap.put(l, actorIdToActors.get(l).copy());
		}

		return hashMap;
	}

	@Override
	public long getId() {
		return frameNumber;
	}

	public GameMap getMap() {
		return map;
	}

}
