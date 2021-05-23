package app;

import java.util.LinkedList;
import java.util.Queue;

import context.GameContext;
import context.GameContextWrapper;
import data.GameData;
import data.MainScreenData;
import event.clienttoserver.ClientToServerGameEvent;
import event.input.AbstractGameInputEvent;
import event.servertoclient.ServerToClientGameEvent;
import logic.GameLogic;
import logic.GameLogicTimer;
import logic.MainScreenLogic;
import logic.TimeAccumulator;
import util.LimitedQueue;
import visuals.GameVisuals;
import visuals.MainScreenVisuals;

public class GameApp {

	public static void main(String[] args) throws InterruptedException {
		Queue<AbstractGameInputEvent> inputBuffer = new LinkedList<>();
		Queue<ClientToServerGameEvent> ctsEventBuffer = new LimitedQueue<>(50);
		Queue<ServerToClientGameEvent> stcEventBuffer = new LimitedQueue<>(50);

		GameData data = new MainScreenData();
		GameLogic logic = new MainScreenLogic(data, inputBuffer, ctsEventBuffer, stcEventBuffer);
		GameVisuals visuals = new MainScreenVisuals(data);

		GameContext context = new GameContext(logic, visuals, data);
		GameContextWrapper wrapper = new GameContextWrapper(context);
		GameSketch sketch = new GameSketch(960, 720, inputBuffer, wrapper);

		GameLogicTimer timer = new GameLogicTimer(wrapper, new TimeAccumulator());
		sketch.run();

		new Thread(timer).start();
//		ClientNetworking networking = new ClientNetworking(ctsEventBuffer, stcEventBuffer);
//		networking.run();
	}

}
