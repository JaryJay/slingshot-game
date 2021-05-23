package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import actor.Player;
import event.GameEvent;
import event.clienttoserver.CTSTestGameEvent;
import event.clienttoserver.ClientToServerGameEvent;
import event.clienttoserver.ConnectionRequestEvent;
import event.clienttoserver.RegisterObserverEvent;
import event.servertoclient.ConnectionAcceptanceEvent;
import event.servertoclient.PlayerJoinedEvent;
import event.servertoclient.STCTestGameEvent;
import map.GameMap;
import map.RectangularObstacle;
import math.Vector2f;
import network.ClientDetails;
import network.ClientToServerRequest;
import network.ServerToClientResponse;
import state.GameState;
import util.id.IdGenerator;

/**
 * The timer that updates a server side {@link MutableGameState} a fixed number
 * of times a second.
 * 
 * @author Jay
 *
 */
public class ServerSideGameLogicTimer implements Runnable {

	private volatile GameState state = null;
	private List<ClientDetails> clientDetails;
	private Queue<ClientToServerRequest> requests;
	private Queue<ServerToClientResponse> responses;

	private boolean isDone = false;

	private long framesElapsed = 1;
	private float targetFrameRate = 10f;
	private float targetFrameTime = 1000.0f / targetFrameRate;
	private float accumulator = 0;

	private long currentTime;

	public ServerSideGameLogicTimer(Queue<ClientToServerRequest> requests, Queue<ServerToClientResponse> responses) {
		this.requests = requests;
		this.responses = responses;
		clientDetails = new ArrayList<>();
		GameMap map = new GameMap();
		map.getObstacles().add(new RectangularObstacle(new Vector2f(200, 200), new Vector2f(300, 100)));
		state = new GameState(0, map, new HashMap<>());
	}

	@Override
	public void run() {
		currentTime = System.currentTimeMillis();
		while (!isDone) {
			// Conditional updates if time is up
			fixedTimeStepUpdate();
			// Yielding thread in case other threads need a chance to shine
			Thread.yield();
		}
	}

	private void fixedTimeStepUpdate() {
		long newTime = System.currentTimeMillis();
		long frameTime = newTime - currentTime;

		// The following if check is to make sure we don't fall into the spiral of death
		if (frameTime >= 1000) {
			frameTime = 1000;
		}
		currentTime = newTime;
		accumulator += frameTime;

		// Updating as many times as needed to make up for any lag
		while (accumulator >= targetFrameTime) {
//			state.update(); // TODO
			if (!requests.isEmpty()) {
				ClientToServerRequest request = requests.poll();
				handle(request);
			}
			accumulator -= targetFrameTime;
			framesElapsed++;
		}
	}

	private void handle(ClientToServerRequest request) {
		ClientToServerGameEvent event = request.getEvent();
		if (event instanceof ConnectionRequestEvent) {
			long id = IdGenerator.generateUserId();
			Player player = new Player(IdGenerator.generateActorId());
			state.getIdToActors().put(id, player);
			ConnectionAcceptanceEvent response = GameEvent.generateEvent(ConnectionAcceptanceEvent.class);
			response.setState(state);
			response.setUserId(id);
			responses.add(new ServerToClientResponse(request.getDetails(), response));
			PlayerJoinedEvent notifier = new PlayerJoinedEvent(player);
			for (ClientDetails details : clientDetails) {
				responses.add(new ServerToClientResponse(details, notifier));
			}
		} else if (event instanceof CTSTestGameEvent) {
			responses.add(new ServerToClientResponse(request.getDetails(), GameEvent.generateEvent(STCTestGameEvent.class)));
		} else if (event instanceof RegisterObserverEvent) {
			ConnectionAcceptanceEvent response = GameEvent.generateEvent(ConnectionAcceptanceEvent.class);
			response.setState(state);
			response.setUserId(IdGenerator.generateUserId());
			responses.add(new ServerToClientResponse(request.getDetails(), response));
		}
	}

	public void addClientDetails(ClientDetails details) {
		if (!clientDetails.contains(details)) {
			clientDetails.add(details);
		}
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public float getAlpha() {
		return accumulator / targetFrameTime;
	}

	public void end() {
		isDone = true;
	}

	public long getFramesElapsed() {
		return framesElapsed;
	}

}
