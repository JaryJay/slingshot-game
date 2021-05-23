package logic;

import java.util.Queue;

import context.GameContext;
import data.GameData;
import data.LoadingScreenData;
import data.SlingShotData;
import event.clienttoserver.ClientToServerGameEvent;
import event.input.AbstractGameInputEvent;
import event.servertoclient.ConnectionAcceptanceEvent;
import event.servertoclient.ServerToClientGameEvent;
import network.ClientNetworking;
import processing.core.PApplet;
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
	protected void handleSTCGameEvent(ServerToClientGameEvent poll) {
		if (poll instanceof ConnectionAcceptanceEvent) {
			ConnectionAcceptanceEvent event = (ConnectionAcceptanceEvent) poll;
			SlingShotData slingShotData = new SlingShotData(event.getUserId());
			slingShotData.getPastStates().add(event.getState());
			slingShotData.setCurrentState(event.getState());
			slingShotData.setUsername(loadingScreenData.getUsername());
			IdGenerator.setNextEventId(event.getNextEventId());
			IdGenerator.setNextActorId(event.getNextActorId());
			System.out.println(inputBuffer);
			SlingShotLogic slingShotLogic = new SlingShotLogic(slingShotData, inputBuffer, ctsEventBuffer, stcEventBuffer);
			SlingShotVisuals slingShotVisuals = new SlingShotVisuals(slingShotData);
			slingShotData.setUserId(event.getUserId());
			getContext().getWrapper().transition(new GameContext(slingShotLogic, slingShotVisuals, slingShotData));
		}
	}

}
