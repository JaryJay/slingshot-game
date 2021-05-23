package event.clienttoserver;

public class CTSTestGameEvent extends ClientToServerGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6399282862171381278L;

	@Override
	public String getDescription() {
		return "A test event sent from the client to the server. ID: " + getId();
	}

}
