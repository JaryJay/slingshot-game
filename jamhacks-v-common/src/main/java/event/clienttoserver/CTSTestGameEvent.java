package event.clienttoserver;

public class CTSTestGameEvent extends ClientToServerGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3850987639201790107L;

	public CTSTestGameEvent() {
	}

	public CTSTestGameEvent(long id, long timeSent) {
		super(id, timeSent);
	}

	@Override
	public String getDescription() {
		return "A test event sent from the client to the server. ID: " + getId();
	}

}
