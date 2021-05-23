package event.servertoclient;

public class STCTestGameEvent extends ServerToClientGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6968150136482652792L;

	@Override
	public String getDescription() {
		return "A test event sent from the server to the client. ID: " + getId();
	}

}
