package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Queue;

import event.GameEventSerializer;
import event.servertoclient.ServerToClientGameEvent;

public class ClientEventReceiver implements Runnable {

	private DatagramSocket socket;
	private boolean ended;
	private Queue<ServerToClientGameEvent> stcEventBuffer;

	public ClientEventReceiver(DatagramSocket socket, Queue<ServerToClientGameEvent> stcEventBuffer) {
		this.socket = socket;
		this.stcEventBuffer = stcEventBuffer;
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
//			System.out.println("[Message received]: " + event.getDescription() + " ID: " + event.getId());
//			ClientToServerGameEvent response = eventHandler.handle(event);
//			if (response != null) {
//				ctsEventBuffer.add(response);
//			}
			stcEventBuffer.add(event);
		}
	}

	public void close() {
		ended = true;
	}

}
