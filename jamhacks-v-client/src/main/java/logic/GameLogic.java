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

	public GameLogic(GameData data, Queue<AbstractGameInputEvent> inputBuffer,
			Queue<ClientToServerGameEvent> ctsEventBuffer, Queue<ServerToClientGameEvent> stcEventBuffer) {
		this.data = data;
		this.inputBuffer = inputBuffer;
		this.ctsEventBuffer = ctsEventBuffer;
		this.stcEventBuffer = stcEventBuffer;
	}

	public void update() {
		handleAllInputEvents();
	}

	protected ClientToServerGameEvent handleInputEvent(AbstractGameInputEvent inputEvent) {
		return null;
	}

	protected void handleAllInputEvents() {
		while (!inputBuffer.isEmpty()) {
			AbstractGameInputEvent poll = inputBuffer.poll();
			ClientToServerGameEvent returnEvent = handleInputEvent(poll);
			if (returnEvent != null) {
				ctsEventBuffer.add(returnEvent);
			}
		}
	}

	protected void handleAllSTCEvents() {
		while (!stcEventBuffer.isEmpty()) {
			ServerToClientGameEvent poll = stcEventBuffer.poll();
			handleSTCEvent(poll);
		}
	}

	protected void handleSTCEvent(ServerToClientGameEvent poll) {
	}
}
