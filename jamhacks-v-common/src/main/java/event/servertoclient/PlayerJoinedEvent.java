package event.servertoclient;

import actor.Player;

public class PlayerJoinedEvent extends ServerToClientGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 318482090128649585L;
	private Player newPlayer;

	public PlayerJoinedEvent(Player newPlayer) {
		this.newPlayer = newPlayer;
	}

	public PlayerJoinedEvent(long id, long timeSent) {
		super(id, timeSent);
	}

	public Player getNewPlayer() {
		return newPlayer;
	}

	@Override
	public String getDescription() {
		return "Player " + newPlayer.getId() + " joined.";
	}

}
