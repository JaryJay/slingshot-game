package state;

import java.util.Collection;

import actor.GameActor;
import event.clienttoserver.ClientToServerGameEvent;
import event.input.GameInputFrame;
import event.inputfactory.AimEvent;

public class GameStateExtrapolator {

	public static GameState getNextState(GameState state, Collection<GameInputFrame> inputFrames) {
		GameState next = new GameState(state.frameNumber + 1, state.getMap(), state.getIdToActors());
		for (GameInputFrame inputFrame : inputFrames) {
			for (ClientToServerGameEvent event : inputFrame.getEvents()) {
				if (event instanceof AimEvent) {

				}
			}
		}
		for (GameActor actor : state.getIdToActors().values()) {
			actor.update();
		}
		return next;
	}

}
