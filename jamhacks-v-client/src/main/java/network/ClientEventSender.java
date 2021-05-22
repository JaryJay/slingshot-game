package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Queue;

import event.GameEventSerializer;
import event.clienttoserver.ClientToServerGameEvent;
import util.LimitedQueue;

public class ClientEventSender implements Runnable {

	private boolean ended;
	private LimitedQueue<ClientToServerGameEvent> ctsEventBuffer;
	private DatagramSocket socket;
	private InetAddress destinationIp;

	public ClientEventSender(DatagramSocket socket, InetAddress destinationIp) {
		this.socket = socket;
		this.destinationIp = destinationIp;
		ended = false;
		ctsEventBuffer = new LimitedQueue<>(50);
	}

	@Override
	public void run() {
		while (!ended) {
			if (ctsEventBuffer.isEmpty()) {
				Thread.yield();
				continue;
			}
			ClientToServerGameEvent event = ctsEventBuffer.poll();
			byte[] buf = GameEventSerializer.serialize(event);

			DatagramPacket packet = new DatagramPacket(buf, buf.length, destinationIp, 4445);

			try {
				socket.send(packet);
				System.out.println("[Message sent]: " + event.getDescription());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Queue<ClientToServerGameEvent> getCtsEventBuffer() {
		return ctsEventBuffer;
	}

	public void close() {
		ended = true;
	}

}
