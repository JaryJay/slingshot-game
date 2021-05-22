package logic;

import state.ImmutableGameState;
import state.MutableGameState;
import util.LimitedQueue;

public class GameData {

	private LimitedQueue<ImmutableGameState> pastStates;
	private MutableGameState currentState;
	private int currentScreen = 1;

	public LimitedQueue<ImmutableGameState> getPastStates() {
		return pastStates;
	}

	public MutableGameState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(MutableGameState currentState) {
		this.currentState = currentState;
	}

	public int getCurrentScreen() {
		return currentScreen;
	}

	public void setCurrentScreen(int currentScreen) {
		this.currentScreen = currentScreen;
	}

}
