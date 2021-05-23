package logic;

import java.awt.event.KeyEvent;
import java.util.Queue;

import context.GameContext;
import data.GameData;
import data.LoadingScreenData;
import event.clienttoserver.ClientToServerGameEvent;
import event.input.AbstractGameInputEvent;
import event.input.KeyPressedEvent;
import event.servertoclient.ServerToClientGameEvent;

public class LoadingScreenLogic extends GameLogic {
	private LoadingScreenData mainScreenData;

	public LoadingScreenLogic(GameData data, Queue<AbstractGameInputEvent> inputBuffer,
			Queue<ClientToServerGameEvent> ctsEventBuffer, Queue<ServerToClientGameEvent> stcEventBuffer) {
		super(data, inputBuffer, ctsEventBuffer, stcEventBuffer);
		mainScreenData = ((LoadingScreenData) data);
	}

	@Override
	protected ClientToServerGameEvent handleInputEvent(AbstractGameInputEvent inputEvent) {
		if (inputEvent instanceof KeyPressedEvent) {
			KeyPressedEvent event = (KeyPressedEvent) inputEvent;
			if (event.getKeyCode() == 10 && mainScreenData.getTextInput().length() > 0) {
				String username = mainScreenData.getTextInput();
				mainScreenData.setTextInput("");
				GameContext loadingScreenContext = new GameContext(null, null, mainScreenData);
				getContext().getWrapper().transition(loadingScreenContext);
			} else if (event.getKeyCode() == KeyEvent.VK_BACK_SPACE && mainScreenData.getTextInput().length() >= 1) {
				mainScreenData.setTextInput(
						mainScreenData.getTextInput().substring(0, mainScreenData.getTextInput().length() - 1));

			} else if (event.getKeyCode() >= 32 && mainScreenData.getTextInput().length() <= 20) {
				String character = Character.toString((char) event.getKeyCode());
				mainScreenData.setTextInput(mainScreenData.getTextInput() + character);
			}
		}
		return null;
	}

}
