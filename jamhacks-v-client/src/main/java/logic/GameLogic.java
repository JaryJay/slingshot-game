package logic;

import java.util.Queue;

import context.ContextPart;
import data.GameData;
import event.clienttoserver.ClientToServerGameEvent;
import event.input.AbstractGameInputEvent;
import event.servertoclient.ServerToClientGameEvent;

public class GameLogic extends ContextPart {

	protected GameData data;
	protected Queue<AbstractGameInputEvent> inputBuffer;
	protected Queue<ClientToServerGameEvent> ctsEventBuffer;
	protected Queue<ServerToClientGameEvent> stcEventBuffer;

	public GameLogic(GameData data, Queue<AbstractGameInputEvent> inputBuffer, Queue<ClientToServerGameEvent> ctsEventBuffer, Queue<ServerToClientGameEvent> stcEventBuffer) {
		this.data = data;
		this.inputBuffer = inputBuffer;
		this.ctsEventBuffer = ctsEventBuffer;
		this.stcEventBuffer = stcEventBuffer;
	}

	public void update() {
		handleAllInputEvents();
		handleAllSTCEvents();
	}

	protected ClientToServerGameEvent handleInputEvent(AbstractGameInputEvent rawInputEvent) {
		return null;
	}

	protected void handleCTSGameEvent(ClientToServerGameEvent event) {
	}

	protected final void handleAllInputEvents() {
		while (!inputBuffer.isEmpty()) {
			AbstractGameInputEvent poll = inputBuffer.poll();
			ClientToServerGameEvent returnEvent = handleInputEvent(poll);
			handleCTSGameEvent(returnEvent);
		}
	}

	protected final void handleAllSTCEvents() {
		while (!stcEventBuffer.isEmpty()) {
			ServerToClientGameEvent poll = stcEventBuffer.poll();
			handleSTCGameEvent(poll);
		}
	}

	protected void handleSTCGameEvent(ServerToClientGameEvent poll) {
	}
}
