package event.clienttoserver;

public class RegisterObserverEvent extends ClientToServerGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6567880124949370113L;

	public RegisterObserverEvent() {
	}

	public RegisterObserverEvent(long id, long timeSent) {
		super(id, timeSent);
	}

	@Override
	public String getDescription() {
		return "Register a new server observer";
	}
}
