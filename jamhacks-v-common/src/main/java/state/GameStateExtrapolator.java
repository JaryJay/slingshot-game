package state;

import java.util.Map;

import actor.GameActor;
import actor.Player;
import actor.Projectile;
import event.input.GameInputFrame;
import event.inputfactory.AimEvent;
import event.inputfactory.ShootEvent;
import event.inputfactory.SophisticatedInputEvent;
import event.inputfactory.SpawnRequestEvent;
import event.inputfactory.VelocityChangeEvent;

public class GameStateExtrapolator {

	private GameStateExtrapolator() {
	}

	public static GameState getNextState(GameState state, GameInputFrame inputFrame) {
		GameState next = new GameState(state.frameNumber + 1, state.getMap(), state.getActorIdToActors());
		for (SophisticatedInputEvent event : inputFrame.getEvents()) {
			Map<Long, GameActor> actorIdToActors = next.getActorIdToActors();
			Player actor = (Player) actorIdToActors.get(event.getPlayerId());
			if (event instanceof AimEvent) {
				AimEvent aimEvent = (AimEvent) event;
				actor.setAimDirection(aimEvent.getAimDirection());
			} else if (event instanceof ShootEvent) {
				Projectile projectile = new Projectile();
				projectile.setPosition(actor.getPosition());
				projectile.setVelocity(actor.getAimDirection());
				actor.getAimDirection().set(0, 0);
				actorIdToActors.put(projectile.getId(), projectile);
			} else if (event instanceof SpawnRequestEvent) {
				SpawnRequestEvent spawnRequestEvent = (SpawnRequestEvent) event;
				actorIdToActors.remove(spawnRequestEvent.getOriginalPlayerId());
				actorIdToActors.put(spawnRequestEvent.getPlayerId(), spawnRequestEvent.getNewPlayer());
			} else if (event instanceof VelocityChangeEvent) {
				VelocityChangeEvent velocityChangeEvent = (VelocityChangeEvent) event;
				actor.setVelocity(velocityChangeEvent.getMoveVector());
			} else {
				System.err.println("Input even not handled when getting next state of state " + state.getId());
			}
		}
		for (GameActor actor : state.getActorIdToActors().values()) {
			actor.update();
		}
		return next;
	}

}
