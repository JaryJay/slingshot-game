package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import event.GameEventSerializer;
import event.clienttoserver.ClientToServerGameEvent;
import event.servertoclient.ServerToClientGameEvent;
import util.LimitedQueue;

public class ClientEventReceiver implements Runnable {

	private DatagramSocket socket;
	private ClientSideEventHandler eventHandler;
	private boolean ended;
	private LimitedQueue<ClientToServerGameEvent> ctsEventBuffer;

	public ClientEventReceiver(DatagramSocket socket, ClientSideEventHandler eventHandler,
			LimitedQueue<ClientToServerGameEvent> ctsEventBuffer) {
		this.socket = socket;
		this.eventHandler = eventHandler;
		this.ctsEventBuffer = ctsEventBuffer;
	}

	@Override
	public void run() {
		try {
			socket.setSoTimeout(100);
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		while (!ended) {
			byte[] buf = new byte[1024];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			try {
				socket.receive(packet);
			} catch (SocketTimeoutException e) {
				Thread.yield();
				continue;
			} catch (IOException e) {
				e.printStackTrace();
			}
			ServerToClientGameEvent event = (ServerToClientGameEvent) GameEventSerializer.deserialize(packet.getData());
			eventHandler.handle(event);
			System.out.println("[Message received]: " + event.getDescription() + " ID: " + event.getId());
		}
	}

	public void close() {
		ended = true;
	}

}
