package logic;

import java.awt.event.KeyEvent;
import java.util.Queue;

import context.GameContext;
import data.GameData;
import data.LoadingScreenData;
import data.SlingShotData;
import event.clienttoserver.ClientToServerGameEvent;
import event.input.AbstractGameInputEvent;
import event.input.KeyPressedGameInputEvent;
import event.servertoclient.ConnectionAcceptanceEvent;
import event.servertoclient.ServerToClientGameEvent;
import network.ClientNetworking;
import processing.core.PApplet;
import visuals.LoadingScreenVisuals;
import visuals.SlingShotVisuals;

public class LoadingScreenLogic extends GameLogic {

	private LoadingScreenData mainScreenData;

	public LoadingScreenLogic(GameData data, Queue<AbstractGameInputEvent> inputBuffer, Queue<ClientToServerGameEvent> ctsEventBuffer, Queue<ServerToClientGameEvent> stcEventBuffer) {
		super(data, inputBuffer, ctsEventBuffer, stcEventBuffer);
		this.mainScreenData = (LoadingScreenData) data;
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
	}

	@Override
	protected ClientToServerGameEvent handleInputEvent(AbstractGameInputEvent inputEvent) {
		if (inputEvent instanceof KeyPressedGameInputEvent) {
			KeyPressedGameInputEvent event = (KeyPressedGameInputEvent) inputEvent;
			if (event.getKeyCode() == 10 && mainScreenData.getTextInput().length() > 0) {
				GameData loadingScreenData = new GameData();
				LoadingScreenLogic loadingScreenLogic = new LoadingScreenLogic(loadingScreenData, inputBuffer, ctsEventBuffer, stcEventBuffer);
				LoadingScreenVisuals loadingScreenVisuals = new LoadingScreenVisuals(loadingScreenData);
				GameContext loadingScreenContext = new GameContext(loadingScreenLogic, loadingScreenVisuals, mainScreenData);
				String username = mainScreenData.getTextInput();
				mainScreenData.setTextInput("");
				getContext().getWrapper().transition(loadingScreenContext);
			} else if (event.getKeyCode() == KeyEvent.VK_BACK_SPACE && mainScreenData.getTextInput().length() >= 1) {
				mainScreenData.setTextInput(mainScreenData.getTextInput().substring(0, mainScreenData.getTextInput().length() - 1));

			} else if (event.getKeyCode() >= 32 && mainScreenData.getTextInput().length() <= 20) {
				String character = Character.toString((char) event.getKeyCode());
				mainScreenData.setTextInput(mainScreenData.getTextInput() + character);
			}
		}
		return null;

	}

	@Override
	protected void handleSTCEvent(ServerToClientGameEvent poll) {
		if (poll instanceof ConnectionAcceptanceEvent) {
			ConnectionAcceptanceEvent event = (ConnectionAcceptanceEvent) poll;
			SlingShotData slingShotData = new SlingShotData(event.getId());
			slingShotData.getPastStates().add(event.getState().immutable());
			slingShotData.setCurrentState(event.getState());
			System.out.println(inputBuffer);
			SlingShotLogic slingShotLogic = new SlingShotLogic(slingShotData, inputBuffer, ctsEventBuffer, stcEventBuffer);
			SlingShotVisuals slingShotVisuals = new SlingShotVisuals(slingShotData);
			getContext().getWrapper().transition(new GameContext(slingShotLogic, slingShotVisuals, slingShotData));
		}
	}

}
