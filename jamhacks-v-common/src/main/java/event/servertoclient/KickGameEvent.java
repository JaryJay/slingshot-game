package event.servertoclient;

public class KickGameEvent extends ServerToClientGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5141797877138765619L;

	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		if (reason.length() < 256) {
			this.reason = reason;
		} else {
			throw new IllegalArgumentException("String \"" + reason + "\" is too long.");
		}
	}

}
