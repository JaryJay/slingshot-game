package state;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import actor.GameActor;
import util.id.IdGenerator;

public class ImmutableGameState extends GameState {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7880600854440812129L;
	protected ImmutableMap<Long, GameActor> idToActors;

	public ImmutableGameState(long frameNumber, Map<Long, GameActor> idToActors) {
		this(IdGenerator.generateEventId(), frameNumber, idToActors);
	}

	public ImmutableGameState(long id, long frameNumber, Map<Long, GameActor> idToActors) {
		super();
		this.stateId = id;
		this.frameNumber = frameNumber;
		idToActors = ImmutableMap.copyOf(idToActors);
	}

	@Override
	public ImmutableGameState getNextState() {
		ImmutableGameState next = new ImmutableGameState(this.frameNumber + 1, idToActors);
		for (GameActor actor : idToActors.values()) {
			actor.update();
		}
		return next;
	}

	public MutableGameState mutable() {
		MutableGameState mutableGameState = new MutableGameState();
		mutableGameState.stateId = stateId;
		for (GameActor actor : idToActors.values()) {
			mutableGameState.idToActors.put(actor.getId(), actor);
		}
		return mutableGameState;
	}

	@Override
	public ImmutableMap<Long, GameActor> getIdToActors() {
		return idToActors;
	}

}
