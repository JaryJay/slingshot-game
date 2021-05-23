package event.input;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import event.inputfactory.SophisticatedInputEvent;

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
	private List<SophisticatedInputEvent> events;
	private long frame;

	/**
	 * Constructor that initialized the events list.
	 */
	public GameInputFrame(long frame) {
		this.frame = frame;
		events = new ArrayList<>();
	}

	public long getFrame() {
		return frame;
	}

	/**
	 * Getter for the events that occured in this frame.
	 * 
	 * @return the events that occured in this frame
	 */
	public List<SophisticatedInputEvent> getEvents() {
		return events;
	}

}
