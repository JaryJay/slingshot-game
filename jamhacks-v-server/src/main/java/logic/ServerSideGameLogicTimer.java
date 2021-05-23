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
import event.clienttoserver.InputFrameEvent;
import event.clienttoserver.RegisterObserverEvent;
import event.input.GameInputFrame;
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
import state.GameStateExtrapolator;
import timer.TimeAccumulator;
import util.LimitedQueue;
import util.id.IdGenerator;

/**
 * The timer that updates a server side {@link MutableGameState} a fixed number
 * of times a second.
 * 
 * @author Jay
 *
 */
public class ServerSideGameLogicTimer extends LogicTimer {

	private volatile LimitedQueue<GameState> states = null;
	private LimitedQueue<GameInputFrame> inputFrames;
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
		super(new TimeAccumulator());
		this.requests = requests;
		this.responses = responses;
		clientDetails = new ArrayList<>();
		GameMap map = new GameMap();
		map.getObstacles().add(new RectangularObstacle(new Vector2f(200, 200), new Vector2f(300, 100)));
		states = new LimitedQueue<>(50);
		inputFrames = new LimitedQueue<>(50);
		states.add(new GameState(0, map, new HashMap<>()));
	}

	@Override
	protected void doUpdate() {
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
			states.add(GameStateExtrapolator.getNextState(states.peek(), inputFrames.peek()));
			accumulator -= targetFrameTime;
			framesElapsed++;
		}
	}

	private void handle(ClientToServerRequest request) {
		ClientToServerGameEvent event = request.getEvent();
		if (event instanceof ConnectionRequestEvent) {
			long id = IdGenerator.generateUserId();
			Player player = new Player(IdGenerator.generateActorId());
			GameState state = states.peek();
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
			response.setState(states.peek());
			response.setUserId(IdGenerator.generateUserId());
			responses.add(new ServerToClientResponse(request.getDetails(), response));
		} else if (event instanceof InputFrameEvent) {
			inputFrames.add(((InputFrameEvent) event).getInputFrame());
			for (ClientDetails details : clientDetails) {
				responses.add(new ServerToClientResponse(details, ((InputFrameEvent) event).toSTCEvent()));
			}
		}
	}

	public void addClientDetails(ClientDetails details) {
		if (!clientDetails.contains(details)) {
			clientDetails.add(details);
		}
	}

	public LimitedQueue<GameState> getStates() {
		return states;
	}

	public float getAlpha() {
		return accumulator / targetFrameTime;
	}

	@Override
	public void end() {
		isDone = true;
	}

	@Override
	public long getFramesElapsed() {
		return framesElapsed;
	}

}
