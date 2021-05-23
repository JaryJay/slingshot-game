package event.input;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import event.clienttoserver.ClientToServerGameEvent;

/**
 * A frame's worth of {@link AbstractGameInputEvent}s.
 * 
 * @author Jay
 *
 */
public class GameInputFrame implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6949799180917615452L;
	private List<ClientToServerGameEvent> events;

	/**
	 * Constructor that initialized the events list.
	 */
	public GameInputFrame() {
		events = new ArrayList<>();
	}

	/**
	 * Getter for the events that occured in this frame.
	 * 
	 * @return the events that occured in this frame
	 */
	public List<ClientToServerGameEvent> getEvents() {
		return events;
	}

}
