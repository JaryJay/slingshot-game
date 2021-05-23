package event.clienttoserver;

public class CTSAcknowledgmentEvent extends ClientToServerGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7485441579655596801L;
	private long idOfAcknowledgedEvent;

	public CTSAcknowledgmentEvent(long idOfAcknowledgedEvent) {
		this.idOfAcknowledgedEvent = idOfAcknowledgedEvent;
	}

	public long getIdOfAcknowledgedEvent() {
		return idOfAcknowledgedEvent;
	}

}
