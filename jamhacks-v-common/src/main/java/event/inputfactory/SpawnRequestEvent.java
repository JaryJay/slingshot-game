package event.inputfactory;

import actor.Player;
import event.clienttoserver.ClientToServerGameEvent;

public class SpawnRequestEvent extends ClientToServerGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8447844140299508935L;
	private long originalPlayerId;
	private Player newPlayer;

	public SpawnRequestEvent() {
	}

	public SpawnRequestEvent(long id, long timeSent) {
		super(id, timeSent);
	}

	public long getOriginalPlayerId() {
		return originalPlayerId;
	}

	public void setOriginalPlayerId(long originalPlayerId) {
		this.originalPlayerId = originalPlayerId;
	}

	public Player getNewPlayer() {
		return newPlayer;
	}

	public void setNewPlayer(Player newPlayer) {
		this.newPlayer = newPlayer;
	}

}
