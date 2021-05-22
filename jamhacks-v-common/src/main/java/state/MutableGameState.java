package state;

import java.util.HashMap;
import java.util.Map;

import actor.GameActor;
import util.id.IdGenerator;

/**
 * The state of the game at a certain point in time.
 * 
 * @author Jay
 *
 */
public class MutableGameState extends GameState {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2349496802538210691L;
	protected Map<Long, GameActor> idToActors;

	/**
	 * Constructor that initializes {@link MutableGameState#idToActors idToActors}
	 * and {@link MutableGameState#stateId id}. Note: GameState uses an event ID,
	 * not an actor ID.
	 */
	public MutableGameState() {
		idToActors = new HashMap<>();
		stateId = IdGenerator.generateEventId();
	}

	@Override
	public MutableGameState getNextState() {
		MutableGameState next = new MutableGameState();
		for (GameActor actor : idToActors.values()) {
			next.idToActors.put(actor.getId(), actor.copy());
		}
		for (GameActor actor : idToActors.values()) {
			actor.update();
		}
		next.frameNumber = this.frameNumber + 1;
		return next;
	}

	public ImmutableGameState immutable() {
		return new ImmutableGameState(stateId, idToActors);
	}

	@Override
	public Map<Long, GameActor> getIdToActors() {
		return idToActors;
	}

	public void update() {
		for (GameActor actor : idToActors.values()) {
			actor.update();
		}
	}

	public void setId(long id) {
		this.stateId = id;
	}

}
