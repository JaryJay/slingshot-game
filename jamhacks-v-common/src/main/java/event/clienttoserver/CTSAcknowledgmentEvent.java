package event.clienttoserver;

public class CTSAcknowledgmentEvent extends ClientToServerGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 190283183396561037L;
	private long idOfAcknowledgedEvent;

	public CTSAcknowledgmentEvent(long idOfAcknowledgedEvent) {
		this.idOfAcknowledgedEvent = idOfAcknowledgedEvent;
	}

	public CTSAcknowledgmentEvent(long idOfAcknowledgedEvent, long id, long timeSent) {
		super(id, timeSent);
		this.idOfAcknowledgedEvent = idOfAcknowledgedEvent;
	}

	public CTSAcknowledgmentEvent(long id, long timeSent) {
		super(id, timeSent);
	}

	public long getIdOfAcknowledgedEvent() {
		return idOfAcknowledgedEvent;
	}

}
