package util.id;

/**
 * Generates IDs. Use the static methods {@link #generateActorId()
 * generateActorId()} to generate a unique ID for an actor, and
 * {@link #generateEventId() generateEventId()} to generate a unique ID for an
 * event.
 * 
 * @author Jay
 *
 */
public final class IdGenerator {

	/**
	 * Stores the event id that would be returned in the next
	 * {@link #generateUserId() generateUserId()} call.
	 */
	private static long nextUserId = Long.MIN_VALUE;
	/**
	 * Stores the actor id that would be returned in the next
	 * {@link #generateActorId() generateActorId()} call.
	 */
	private static long nextActorId = Long.MIN_VALUE;
	/**
	 * Stores the event id that would be returned in the next
	 * {@link #generateEventId() generateEventId()} call.
	 */
	private static long nextEventId = Long.MIN_VALUE;

	/**
	 * Private constructor to prevent programmers from instantiating an IdGenerator.
	 */
	private IdGenerator() {
	}

	public static synchronized long generateUserId() {
		if (nextUserId == Long.MAX_VALUE) {
			long temp = nextUserId;
			resetUserId();
			return temp;
		}
		return nextUserId++;
	}

	/**
	 * Returns a unique actor ID, then increases {@link IdGenerator#nextActorId
	 * nextActorId} by 1.
	 * 
	 * @return the unique actor ID
	 */
	public static synchronized long generateActorId() {
		if (nextActorId == Long.MAX_VALUE) {
			long temp = nextActorId;
			resetActorId();
			return temp;
		}
		return nextActorId++;
	}

	/**
	 * Returns a unique event ID, then increases {@link IdGenerator#nextEventId
	 * nextEventId} by 1.
	 * 
	 * @return the unique event ID
	 */
	public static synchronized long generateEventId() {
		if (nextEventId == Long.MAX_VALUE) {
			long temp = nextEventId;
			resetEventId();
			return temp;
		}
		return nextEventId++;
	}

	/**
	 * Resets {@link IdGenerator#nextUserId nextUserId} to Long.MIN_VALUE.
	 */
	public static synchronized void resetUserId() {
		nextUserId = Long.MIN_VALUE;
	}

	/**
	 * Resets {@link IdGenerator#nextActorId nextActorId} to Long.MIN_VALUE.
	 */
	public static synchronized void resetActorId() {
		nextActorId = Long.MIN_VALUE;
	}

	/**
	 * Resets {@link IdGenerator#nextEventId nextEventId} to Long.MIN_VALUE.
	 */
	public static synchronized void resetEventId() {
		nextEventId = Long.MIN_VALUE;
	}

}
