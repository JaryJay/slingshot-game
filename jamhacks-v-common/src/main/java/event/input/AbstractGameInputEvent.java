package event.input;

import java.io.Serializable;

/**
 * A client's input wrapped up in an event.
 * 
 * @author Jay
 *
 */
public abstract class AbstractGameInputEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4373161987058360811L;
	private long timeMillis;

	/**
	 * Constructor that initialized the timeMillis variable
	 */
	public AbstractGameInputEvent() {
		timeMillis = System.currentTimeMillis();
	}

	/**
	 * Gets the time when the event was created.
	 * 
	 * @return the time of creation
	 */
	public long getTimeMillis() {
		return timeMillis;
	}

}
