package data;

import event.input.GameInputFrame;
import state.ImmutableGameState;
import state.MutableGameState;
import util.LimitedQueue;

public class SlingShotData extends GameData {

	private LimitedQueue<ImmutableGameState> pastStates;
	private MutableGameState currentState;
	private GameInputFrame currentInputFrame;
	private long userId;

	public SlingShotData(long userId) {
		pastStates = new LimitedQueue<>(50);
		this.userId = userId;
	}

	public LimitedQueue<ImmutableGameState> getPastStates() {
		return pastStates;
	}

	public MutableGameState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(MutableGameState currentState) {
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
