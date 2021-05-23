package data;

import event.input.GameInputFrame;
import math.Vector2f;
import state.GameState;
import util.LimitedQueue;

public class SlingShotData extends GameData {

	private LimitedQueue<GameState> pastStates;
	private GameState currentState;
	private GameInputFrame currentInputFrame;
	private String username;
	private long userId;
	private long playerId;
	private boolean aimingShot;
	private Vector2f mousePosOnClick;
	private long lastShot = 0;

	public SlingShotData(long userId) {
		pastStates = new LimitedQueue<>(50);
		this.userId = userId;
	}

	public LimitedQueue<GameState> getPastStates() {
		return pastStates;
	}

	public GameState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(GameState currentState) {
		this.currentState = currentState;
	}

	public GameInputFrame getCurrentInputFrame() {
		return currentInputFrame;
	}

	public void setCurrentInputFrame(GameInputFrame currentInputFrame) {
		this.currentInputFrame = currentInputFrame;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public boolean isAimingShot() {
		return aimingShot;
	}

	public void setAimingShot(boolean aimingShot) {
		this.aimingShot = aimingShot;
	}

	public Vector2f getMousePosOnClick() {
		return mousePosOnClick;
	}

	public void setMousePosOnClick(Vector2f mousePosOnClick) {
		this.mousePosOnClick = mousePosOnClick;
	}

	public long getLastShot() {
		return lastShot;
	}

	public void setLastShot(long lastShot) {
		this.lastShot = lastShot;
	}

}
