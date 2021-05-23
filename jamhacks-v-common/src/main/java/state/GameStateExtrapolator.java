package state;

import java.util.List;
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
import map.GameObstacle;
import map.RectangularObstacle;
import math.Vector2f;

public class GameStateExtrapolator {

	private GameStateExtrapolator() {
	}

	public static GameState getNextState(GameState state, GameInputFrame inputFrame) {
		Map<Long, GameActor> actorIdToActors = state.getActorIdToActors();
		GameState next = new GameState(state.frameNumber + 1, state.getMap(), actorIdToActors);

		handleEvents(state, inputFrame, actorIdToActors);

		for (GameActor actor : actorIdToActors.values()) {
			actor.update();

			List<GameObstacle> obstacles = state.getMap().getObstacles();

			if (actor instanceof Player) {
				Player player = (Player) actor;
				for (GameObstacle obstacle : obstacles) {
					if (obstacle instanceof RectangularObstacle) {
						handlePlayerRectCollision(player, obstacle);
					}
				}
			} else if (actor instanceof Projectile) {
				Projectile projectile = (Projectile) actor;
				for (GameObstacle obstacle : obstacles) {
					if (obstacle instanceof RectangularObstacle) {
						handleProjectileRectCollision(projectile, obstacle);
					}
				}
			}

		}
		return next;
	}

	private static void handleProjectileRectCollision(Projectile projectile, GameObstacle obstacle) {
		Vector2f pPos = projectile.getPosition();
		Vector2f obstaclePos = obstacle.getPosition();
		float clampedX = Math.min(Math.max(pPos.x, obstaclePos.x), obstaclePos.x + obstacle.getDimensions().x);
		float clampedY = Math.min(Math.max(pPos.y, obstaclePos.y), obstaclePos.y + obstacle.getDimensions().y);

		Vector2f obstacleTestPoint = new Vector2f(clampedX, clampedY);

		Vector2f testPointToProjectilePos = pPos.copy().sub(obstacleTestPoint);

		if (projectile.getHitboxRadius() * projectile.getHitboxRadius() >= testPointToProjectilePos.lengthSqaured()) {
			testPointToProjectilePos.setLength(projectile.getHitboxRadius());
			projectile.setPosition(testPointToProjectilePos.add(obstacleTestPoint));

			projectile.setVelocity(projectile.getVelocity().reflect(testPointToProjectilePos));
		}
	}

	private static void handlePlayerRectCollision(Player player, GameObstacle obstacle) {
		Vector2f pPos = player.getPosition();
		Vector2f obstaclePos = obstacle.getPosition();
		float clampedX = Math.min(Math.max(pPos.x, obstaclePos.x), obstaclePos.x + obstacle.getDimensions().x);
		float clampedY = Math.min(Math.max(pPos.y, obstaclePos.y), obstaclePos.y + obstacle.getDimensions().y);

		Vector2f obstacleTestPoint = new Vector2f(clampedX, clampedY);

		Vector2f testPointToPlayerPos = pPos.copy().sub(obstacleTestPoint);

		if (player.getHitboxRadius() * player.getHitboxRadius() >= testPointToPlayerPos.lengthSqaured()) {
			testPointToPlayerPos.setLength(player.getHitboxRadius());
			player.setPosition(testPointToPlayerPos.add(obstacleTestPoint));
		}
	}

	private static void handleEvents(GameState state, GameInputFrame inputFrame, Map<Long, GameActor> actorIdToActors) {
		for (SophisticatedInputEvent event : inputFrame.getEvents()) {
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
	}

}
