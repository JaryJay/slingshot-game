package data;

import event.input.GameInputFrame;
import state.GameState;
import util.LimitedQueue;

public class SlingShotData extends GameData {

	private LimitedQueue<GameState> pastStates;
	private GameState currentState;
	private GameInputFrame currentInputFrame;
	private long userId;

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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
