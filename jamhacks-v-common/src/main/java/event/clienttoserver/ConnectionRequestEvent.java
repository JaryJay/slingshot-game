package event.clienttoserver;

public class ConnectionRequestEvent extends ClientToServerGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1097642274316407671L;

	public ConnectionRequestEvent() {
	}

	public ConnectionRequestEvent(long id, long timeSent) {
		super(id, timeSent);
	}

}
