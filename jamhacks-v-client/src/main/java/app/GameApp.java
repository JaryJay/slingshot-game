package app;

import java.util.LinkedList;
import java.util.Queue;

import event.clienttoserver.ClientToServerGameEvent;
import event.input.AbstractGameInputEvent;
import util.LimitedQueue;

public class GameApp {

	public static void main(String[] args) {
		Queue<AbstractGameInputEvent> inputBuffer = new LinkedList<>();
//		DatagramSocket socket = null;
//		InetAddress destinationAddress = null;
//		ClientEventSender sender = null;
//		ClientEventReceiver receiver = null;
//
//		try {
//			socket = new DatagramSocket(8080, InetAddress.getLocalHost());
//			System.out.println("Starting client at " + socket.getLocalAddress() + ", port = " + socket.getLocalPort());
//			destinationAddress = InetAddress.getByName("72.140.156.47");
//			sender = new ClientEventSender(socket, destinationAddress);
//			receiver = new ClientEventReceiver(socket, new ClientSideEventHandler());
//			new Thread(sender).start();
//			new Thread(receiver).start();
//			ClientToServerGameEvent event = new ConnectionRequestEvent(234, 123);
//			sender.getCtsEventBuffer().add(event);
//			Thread.sleep(10000);
//			sender.close();
//			receiver.close();
//		} catch (SocketException e) {
//			e.printStackTrace();
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		LimitedQueue<ClientToServerGameEvent> ctsEventBuffer = new LimitedQueue<>(50);
//		ClientEventSender clientEventSender = new ClientEventSender(null, null, ctsEventBuffer)

		GameSketch sketch = new GameSketch(960, 720, inputBuffer);
		sketch.run();
	}

}
