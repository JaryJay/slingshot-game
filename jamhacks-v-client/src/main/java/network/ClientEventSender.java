package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Queue;

import event.GameEventSerializer;
import event.clienttoserver.ClientToServerGameEvent;

public class ClientEventSender implements Runnable {

	private boolean ended;
	private Queue<ClientToServerGameEvent> ctsEventBuffer;
	private DatagramSocket socket;
	private InetAddress destinationIp;

	public ClientEventSender(DatagramSocket socket, InetAddress destinationIp,
			Queue<ClientToServerGameEvent> eventList) {
		this.socket = socket;
		this.destinationIp = destinationIp;
		ended = false;
		ctsEventBuffer = eventList;
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

	public void addEvent(ClientToServerGameEvent event) {
		this.ctsEventBuffer.add(event);
	}

	public void close() {
		ended = true;
	}

}
