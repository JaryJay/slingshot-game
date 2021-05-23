package event.inputfactory;

import event.clienttoserver.ClientToServerGameEvent;

public class SophisticatedInputEvent extends ClientToServerGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6799981306338521044L;

	private long playerId;

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

}
