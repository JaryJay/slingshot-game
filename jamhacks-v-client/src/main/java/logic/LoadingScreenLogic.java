package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import actor.GameActor;
import context.GameContext;
import data.GameData;
import data.LoadingScreenData;
import data.SlingShotData;
import event.clienttoserver.ClientToServerGameEvent;
import event.input.AbstractGameInputEvent;
import event.servertoclient.ConnectionAcceptanceEvent;
import event.servertoclient.STCActorInfoEvent;
import event.servertoclient.STCObstacleInfoEvent;
import event.servertoclient.ServerToClientGameEvent;
import map.GameMap;
import map.GameObstacle;
import network.ClientNetworking;
import processing.core.PApplet;
import state.GameState;
import util.id.IdGenerator;
import visuals.SlingShotVisuals;

public class LoadingScreenLogic extends GameLogic {

	private LoadingScreenData loadingScreenData;

	public LoadingScreenLogic(GameData data, Queue<AbstractGameInputEvent> inputBuffer, Queue<ClientToServerGameEvent> ctsEventBuffer, Queue<ServerToClientGameEvent> stcEventBuffer) {
		super(data, inputBuffer, ctsEventBuffer, stcEventBuffer);
		this.loadingScreenData = (LoadingScreenData) data;
	}

	@Override
	public void init(PApplet p) {
		super.init(p);
		ClientNetworking clientNetworking = new ClientNetworking(ctsEventBuffer, stcEventBuffer);
		new Thread(clientNetworking).start();
	}

	@Override
	public void update() {
		super.update();
		Map<Long, GameActor> actorIdToActors = loadingScreenData.getActorIdToActors();
		List<GameObstacle> obstacles = loadingScreenData.getObstacles();
		long[] frameNumberUserIdActorIdEventId = loadingScreenData.getFrameNumberUserIdActorIdEventId();
		System.out.println(actorIdToActors + " " + obstacles + " " + frameNumberUserIdActorIdEventId);
		if (actorIdToActors != null && obstacles != null && frameNumberUserIdActorIdEventId != null) {
			SlingShotData slingShotData = new SlingShotData(frameNumberUserIdActorIdEventId[1]);
			GameState state = new GameState(frameNumberUserIdActorIdEventId[1], new GameMap(obstacles), actorIdToActors);
			slingShotData.getPastStates().add(state);
			slingShotData.setCurrentState(state);
			slingShotData.setUsername(loadingScreenData.getUsername());
			IdGenerator.setNextActorId(frameNumberUserIdActorIdEventId[2]);
			IdGenerator.setNextEventId(frameNumberUserIdActorIdEventId[3]);
			SlingShotLogic slingShotLogic = new SlingShotLogic(slingShotData, inputBuffer, ctsEventBuffer, stcEventBuffer);
			SlingShotVisuals slingShotVisuals = new SlingShotVisuals(slingShotData);
			slingShotData.setUserId(frameNumberUserIdActorIdEventId[1]);
			getContext().getWrapper().transition(new GameContext(slingShotLogic, slingShotVisuals, slingShotData));
		}
	}

	@Override
	protected void handleSTCGameEvent(ServerToClientGameEvent event) {
		System.out.println("Handling event:  " + event.getDescription());
		if (event instanceof ConnectionAcceptanceEvent) {
			ConnectionAcceptanceEvent connectionAcceptanceEvent = (ConnectionAcceptanceEvent) event;
			loadingScreenData.setFrameNumberUserIdActorIdEventId(connectionAcceptanceEvent.getFrameNumber(), connectionAcceptanceEvent.getUserId(), connectionAcceptanceEvent.getNextActorId(), connectionAcceptanceEvent.getNextEventId());
			System.out.println(loadingScreenData.getFrameNumberUserIdActorIdEventId());
		} else if (event instanceof STCActorInfoEvent) {
			HashMap<Long, GameActor> actorIdToActors = new HashMap<>();
			for (GameActor actor : ((STCActorInfoEvent) event).getActors()) {
				actorIdToActors.put(actor.getId(), actor);
			}
			loadingScreenData.setActorIdToActors(actorIdToActors);
			System.out.println(loadingScreenData.getActorIdToActors());
		} else if (event instanceof STCObstacleInfoEvent) {
			GameObstacle obstacle = ((STCObstacleInfoEvent) event).getObstacle();
			if (loadingScreenData.getObstacles() == null) {
				List<GameObstacle> obstacleList = new ArrayList<>();
				loadingScreenData.setObstacles(obstacleList);
			}
			loadingScreenData.getObstacles().add(obstacle);
			System.out.println(loadingScreenData.getObstacles());
		} else {
			System.out.println("unhandled: " + event.getDescription());
		}
	}

}
