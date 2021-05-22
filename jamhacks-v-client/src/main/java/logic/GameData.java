package logic;

import state.ImmutableGameState;
import state.MutableGameState;
import util.LimitedQueue;

public class GameData {

	private LimitedQueue<ImmutableGameState> pastStates;
	private MutableGameState currentState;

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
