package event.servertoclient;

public class ConnectionAcceptanceEvent extends ServerToClientGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8108701449003594930L;
	private long userId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String getDescription() {
		return "Connection accepted";
	}

}
