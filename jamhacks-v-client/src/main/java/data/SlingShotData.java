package data;

import state.ImmutableGameState;
import state.MutableGameState;
import util.LimitedQueue;

public class SlingShotData extends GameData {

	private LimitedQueue<ImmutableGameState> pastStates;
	private MutableGameState currentState;
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

}
