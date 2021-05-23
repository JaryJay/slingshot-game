package state;

import actor.GameActor;
import event.input.GameInputFrame;

public class GameStateExtrapolator {

	public static GameState getNextState(GameState state, GameInputFrame inputFrame) {
		GameState next = new GameState(state.frameNumber + 1, state.getMap(), state.getIdToActors());
		for (GameActor actor : state.getIdToActors().values()) {
			actor.update();
		}
		return next;
	}

}
