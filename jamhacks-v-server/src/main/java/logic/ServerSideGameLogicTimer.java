package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import actor.Player;
import event.clienttoserver.CTSTestGameEvent;
import event.clienttoserver.ClientToServerGameEvent;
import event.clienttoserver.ConnectionRequestEvent;
import event.clienttoserver.InputFrameEvent;
import event.clienttoserver.RegisterObserverEvent;
import event.input.GameInputFrame;
import event.servertoclient.ConnectionAcceptanceEvent;
import event.servertoclient.PlayerJoinedEvent;
import event.servertoclient.STCInputForwardEvent;
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

	private volatile LimitedQueue<GameState> states = null;
	private LimitedQueue<GameInputFrame> inputFrames;
	private List<ClientDetails> clientDetails;
	private Queue<ClientToServerRequest> requests;
	private Queue<ServerToClientResponse> responses;
	private int earliestDirtyFrame = 0;
	private boolean isDone;

	public ServerSideGameLogicTimer(Queue<ClientToServerRequest> requests, Queue<ServerToClientResponse> responses) {
		super(10, new TimeAccumulator());
		this.requests = requests;
		this.responses = responses;
		clientDetails = new ArrayList<>();
		states = new LimitedQueue<>(50);
		// Creates a limited hash map
//		frameNumberToInputFrames = new LinkedHashMap<>(50, 1.0f, true) {
//			private static final long serialVersionUID = -7875222597159324185L;
//
//			@Override
//			protected boolean removeEldestEntry(Map.Entry<Long, GameInputFrame> eldest) {
//				return size() > 50;
//			}
//		};
		inputFrames = new LimitedQueue<>(50);
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
		for (int i = earliestDirtyFrame; i < states.size() - 1; i++) {
			GameState state = states.get(i);
			System.out.println("Resimulating state " + state.getId() + "");
			states.set(i + 1, GameStateExtrapolator.getNextState(state, inputFrames.get(i)));
		}
		states.add(GameStateExtrapolator.getNextState(states.getLast(), inputFrames.getLast()));
		inputFrames.add(new GameInputFrame(inputFrames.getLast().getFrame() + 1));
		earliestDirtyFrame = states.size() - 1;
	}

	private void handle(ClientToServerRequest request) {
		ClientToServerGameEvent event = request.getEvent();
		if (event instanceof ConnectionRequestEvent) {
			long id = IdGenerator.generateUserId();
			Player player = new Player(IdGenerator.generateActorId());
			GameState state = states.peek();
			state.getActorIdToActors().put(id, player);
			ConnectionAcceptanceEvent response = new ConnectionAcceptanceEvent();
			response.setState(state);
			response.setUserId(id);
			responses.add(new ServerToClientResponse(request.getDetails(), response));
			PlayerJoinedEvent notifier = new PlayerJoinedEvent(player);
			for (ClientDetails details : clientDetails) {
				responses.add(new ServerToClientResponse(details, notifier));
			}
		} else if (event instanceof CTSTestGameEvent) {
			responses.add(new ServerToClientResponse(request.getDetails(), new STCTestGameEvent()));
		} else if (event instanceof RegisterObserverEvent) {
			ConnectionAcceptanceEvent response = new ConnectionAcceptanceEvent();
			response.setState(states.peek());
			response.setUserId(IdGenerator.generateUserId());
			responses.add(new ServerToClientResponse(request.getDetails(), response));
		} else if (event instanceof InputFrameEvent) {
			reconcileInputFrame((InputFrameEvent) event);
		}
	}

	private void reconcileInputFrame(InputFrameEvent event) {
		GameInputFrame inputFrame = event.getInputFrame();
		long eventFrameNumber = inputFrame.getFrame();

		long earliestRememberedFrame = inputFrames.getFirst().getFrame();
		if (eventFrameNumber >= earliestRememberedFrame) {
			int index = (int) (eventFrameNumber - earliestRememberedFrame);
			inputFrames.get(index).getEvents().addAll(inputFrame.getEvents());
			earliestRememberedFrame = Math.min(index, earliestRememberedFrame);
		}
		ArrayList<GameInputFrame> list = new ArrayList<>();
		list.addAll(inputFrames);
		for (ClientDetails details : clientDetails) {
			responses.add(new ServerToClientResponse(details, new STCInputForwardEvent(list)));
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
