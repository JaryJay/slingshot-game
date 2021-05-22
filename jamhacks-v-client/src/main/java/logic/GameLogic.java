package logic;

import state.MutableGameState;

public class GameLogic {
	private GameData data;

	public GameLogic(GameData data) {
		this.data = data;
	}

	public void update() {
		MutableGameState currentState = data.getCurrentState();
		data.getPastStates().add(currentState.immutable());
		data.setCurrentState(currentState.getNextState());
	}
}
