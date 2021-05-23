package event;

import java.io.Serializable;
import java.lang.reflect.Constructor;

import util.id.HasId;
import util.id.IdGenerator;

/**
 * An event that can be used for various purposes. To use, it is recommended to
 * extend this class with a concrete implementation.
 * 
 * @author Jay
 *
 */
public abstract class GameEvent implements HasId, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3444985417139104855L;
	/**
	 * The id to be returned in getId().
	 */
	protected long id;
	protected long timeSent;

	public GameEvent() {
		id = IdGenerator.generateEventId();
		timeSent = System.currentTimeMillis();
	}

	/**
	 * Returns a description of this event in the form of a String. It is highly
	 * recommended to override this method when extending {@link GameEvent}.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return "No description.";
	}

	public long getTimeSent() {
		return timeSent;
	}

	public void setTimeSent(long timeSent) {
		this.timeSent = timeSent;
	}

	@Override
	public long getId() {
		return id;
	}

	public static final <T extends GameEvent> T generateEvent(Class<T> eventClass) {
		try {
			Constructor<T> constructor = eventClass.getDeclaredConstructor(long.class, long.class);
			T event = constructor.newInstance(IdGenerator.generateEventId(), System.currentTimeMillis());
			return event;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Could not generate an event of type " + eventClass.getSimpleName());
	}

}
