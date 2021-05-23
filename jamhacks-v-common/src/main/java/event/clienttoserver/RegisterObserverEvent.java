package event.clienttoserver;

public class RegisterObserverEvent extends ClientToServerGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7651524839883748927L;

	@Override
	public String getDescription() {
		return "Register a new server observer";
	}
}
