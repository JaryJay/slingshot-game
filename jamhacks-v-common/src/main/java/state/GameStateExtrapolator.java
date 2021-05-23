package state;

import actor.GameActor;
import event.input.GameInputFrame;

public class GameStateExtrapolator {

	public static ImmutableGameState getNextState(GameState state, GameInputFrame inputFrame) {
		ImmutableGameState next = new ImmutableGameState(state.frameNumber + 1, state.getIdToActors());
		for (GameActor actor : state.getIdToActors().values()) {
			actor.update();
		}
		return next;
	}

}
