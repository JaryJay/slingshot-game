package state;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import actor.GameActor;
import util.id.HasId;
import util.id.IdGenerator;
import util.update.Updatable;

/**
 * The state of the game at a certain point in time.
 * 
 * @author Jay
 *
 */
public class GameState implements HasId, Updatable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3971481281469830855L;
	private Map<Long, GameActor> idToActors;
	private long id;
	private long time;

	/**
	 * Constructor that initializes {@link GameState#idToActors idToActors} and
	 * {@link GameState#id id}. Note: GameState uses an event ID, not an actor ID.
	 */
	public GameState() {
		idToActors = new HashMap<>();
		id = IdGenerator.generateEventId();
	}

	public GameState getNextState() {
		GameState next = new GameState();
		for (Long key : idToActors.keySet()) {
			next.idToActors.put(Long.valueOf(key), idToActors.get(key).copy());
		}
		next.update();
		return next;
	}

	public Map<Long, GameActor> getIdToActors() {
		return idToActors;
	}

	@Override
	public void update() {
		for (GameActor actor : idToActors.values()) {
			actor.update();
		}
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

}
