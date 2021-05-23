package logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import actor.GameActor;
import actor.Player;
import event.clienttoserver.CTSTestGameEvent;
import event.clienttoserver.ClientToServerGameEvent;
import event.clienttoserver.ConnectionRequestEvent;
import event.clienttoserver.InputFrameEvent;
import event.clienttoserver.RegisterObserverEvent;
import event.input.GameInputFrame;
import event.servertoclient.ConnectionAcceptanceEvent;
import event.servertoclient.PlayerJoinedEvent;
import event.servertoclient.STCActorInfoEvent;
import event.servertoclient.STCObstacleInfoEvent;
import event.servertoclient.STCTestGameEvent;
import map.GameMap;
import map.GameObstacle;
import map.RectangularObstacle;
import math.Vector2f;
import network.ClientDetails;
import network.ClientToServerRequest;
import network.ServerToClientResponse;
import state.GameState;
import state.StateReconciliator;
import timer.TimeAccumulator;
import timer.TimestepTimer;
import util.LimitedQueue;
import util.id.IdGenerator;

/**
 * The timer that updates a server side {@link MutableGameState} a fixed number
 * of times a second.
 * 
 * @author Jay
 *
 */
public class ServerSideGameLogicTimer extends TimestepTimer {

	private volatile LimitedQueue<GameState> states;
	private LimitedQueue<GameInputFrame> inputFrames;
	private List<ClientDetails> clientDetails;
	private Queue<ClientToServerRequest> requests;
	private Queue<ServerToClientResponse> responses;
	private boolean isDone;

	private StateReconciliator stateReconciliator;

	public ServerSideGameLogicTimer(Queue<ClientToServerRequest> requests, Queue<ServerToClientResponse> responses) {
		super(10, new TimeAccumulator());
		this.requests = requests;
		this.responses = responses;
		clientDetails = new ArrayList<>();
		states = new LimitedQueue<>(50);
		inputFrames = new LimitedQueue<>(50);
		stateReconciliator = new StateReconciliator(states, inputFrames);
	}

	@Override
	protected void startActions() {
		GameMap map = new GameMap();
		map.getObstacles().add(new RectangularObstacle(new Vector2f(200, 200), new Vector2f(300, 100)));
		states.add(new GameState(0, map, new HashMap<>()));
		inputFrames.add(new GameInputFrame(0));
	}

	@Override
	protected void doUpdate() {
		if (!requests.isEmpty()) {
			ClientToServerRequest request = requests.poll();
			handle(request);
		}
		stateReconciliator.reconcile();
		stateReconciliator.simulateNextState();
	}

	private void handle(ClientToServerRequest request) {
		ClientToServerGameEvent event = request.getEvent();
		if (event instanceof ConnectionRequestEvent) {
			long id = IdGenerator.generateUserId();
			Player player = new Player(IdGenerator.generateActorId());
			GameState state = states.peek();
			state.getActorIdToActors().put(id, player);
			
			ConnectionAcceptanceEvent response = new ConnectionAcceptanceEvent();
			response.setUserId(id);
			response.setFrameNumber(state.getId());
			response.setNextActorId(IdGenerator.generateActorId());
			response.setNextEventId(IdGenerator.generateEventId());
			responses.add(new ServerToClientResponse(request.getDetails(), response));

			for (GameObstacle obstacle : state.getMap().getObstacles()) {
				STCObstacleInfoEvent obstacleInfo = new STCObstacleInfoEvent(obstacle);
				responses.add(new ServerToClientResponse(request.getDetails(), obstacleInfo));
			}
			
			Collection<GameActor> actors =state.getActorIdToActors().values();
			GameActor[] actorsArr  = new GameActor[actors.size()];
			int index = 0;
			for (GameActor actor : actors) {
				actorsArr[index] = actor;
				index++;
			}
			STCActorInfoEvent actorInfo = new STCActorInfoEvent(actorsArr);
			responses.add(new ServerToClientResponse(request.getDetails(), actorInfo));
			
			PlayerJoinedEvent notifier = new PlayerJoinedEvent(player);
			for (ClientDetails details : clientDetails) {
				responses.add(new ServerToClientResponse(details, notifier));
			}
		} else if (event instanceof CTSTestGameEvent) {
			responses.add(new ServerToClientResponse(request.getDetails(), new STCTestGameEvent()));
		} else if (event instanceof RegisterObserverEvent) {
			ConnectionAcceptanceEvent response = new ConnectionAcceptanceEvent();
			response.setUserId(IdGenerator.generateUserId());
			responses.add(new ServerToClientResponse(request.getDetails(), response));
		} else if (event instanceof InputFrameEvent) {
			InputFrameEvent inputFrameEvent = (InputFrameEvent) event;
			stateReconciliator.reloadInputFrame(inputFrameEvent.getInputFrame());
			for (ClientDetails details : clientDetails) {
				responses.add(new ServerToClientResponse(details, inputFrameEvent.toSTCEvent()));
			}
		}
	}

	public void end() {
		isDone = true;
	}

	public void addClientDetails(ClientDetails details) {
		if (!clientDetails.contains(details)) {
			clientDetails.add(details);
		}
	}

	public LimitedQueue<GameState> getStates() {
		return states;
	}

	@Override
	protected boolean endCondition() {
		return isDone;
	}

}
