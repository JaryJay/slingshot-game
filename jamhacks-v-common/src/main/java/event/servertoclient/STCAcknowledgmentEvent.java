package event.servertoclient;

public class STCAcknowledgmentEvent extends ServerToClientGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6008413868499436723L;
	private long idOfAcknowledgedEvent;

	public STCAcknowledgmentEvent(long idOfAcknowledgedEvent) {
		this.idOfAcknowledgedEvent = idOfAcknowledgedEvent;
	}

	public STCAcknowledgmentEvent(long id, long timeSent) {
		super(id, timeSent);
	}

	public long getIdOfAcknowledgedEvent() {
		return idOfAcknowledgedEvent;
	}

}
