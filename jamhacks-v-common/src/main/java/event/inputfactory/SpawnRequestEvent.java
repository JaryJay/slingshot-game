package event.inputfactory;

import actor.Player;

public class SpawnRequestEvent extends SophisticatedInputEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8447844140299508935L;
	private long originalPlayerId;
	private Player newPlayer;

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

	@Override
	public String getDescription() {
		return "Spawn request at " + newPlayer.getPosition().toString();
	}

}
