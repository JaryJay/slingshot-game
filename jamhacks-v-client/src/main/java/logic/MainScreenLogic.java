package logic;

import java.awt.event.KeyEvent;
import java.util.Queue;

import context.GameContext;
import data.GameData;
import data.LoadingScreenData;
import data.MainScreenData;
import event.clienttoserver.ClientToServerGameEvent;
import event.input.AbstractGameInputEvent;
import event.input.KeyPressedGameInputEvent;
import event.inputfactory.SophisticatedInputEvent;
import event.servertoclient.ServerToClientGameEvent;
import visuals.GameVisuals;
import visuals.LoadingScreenVisuals;

public class MainScreenLogic extends GameLogic {
	private MainScreenData mainScreenData;

	public MainScreenLogic(GameData data, Queue<AbstractGameInputEvent> inputBuffer, Queue<ClientToServerGameEvent> ctsEventBuffer, Queue<ServerToClientGameEvent> stcEventBuffer) {
		super(data, inputBuffer, ctsEventBuffer, stcEventBuffer);
		mainScreenData = ((MainScreenData) data);
	}

	@Override
	protected SophisticatedInputEvent handleInputEvent(AbstractGameInputEvent inputEvent) {
		if (inputEvent instanceof KeyPressedGameInputEvent) {
			KeyPressedGameInputEvent event = (KeyPressedGameInputEvent) inputEvent;
			if (event.getKeyCode() == 10 && mainScreenData.getTextInput().length() > 0) {
				String username = mainScreenData.getTextInput();
				mainScreenData.setTextInput("");
				GameData data = new LoadingScreenData();
				GameLogic logic = new LoadingScreenLogic(data, inputBuffer, ctsEventBuffer, stcEventBuffer);
				GameVisuals visuals = new LoadingScreenVisuals(data);
				GameContext loadingScreenContext = new GameContext(logic, visuals, data);
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

}
