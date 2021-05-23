package event.clienttoserver;

public class ConnectionRequestEvent extends ClientToServerGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6373971977571598672L;

	@Override
	public String getDescription() {
		return "Requesting a ConnectionAcceptanceEvent.";
	}

}
