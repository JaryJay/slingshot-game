package network;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Queue;

import event.clienttoserver.ClientToServerGameEvent;
import event.clienttoserver.ConnectionRequestEvent;
import event.servertoclient.ServerToClientGameEvent;

public class ClientNetworking implements Runnable {

	private ClientEventSender sender = null;
	private ClientEventReceiver receiver = null;

	public ClientNetworking(Queue<ClientToServerGameEvent> eventBuffer, Queue<ServerToClientGameEvent> stcEventBuffer) {
		DatagramSocket socket = null;
		InetAddress destinationAddress = null;

		try {
			socket = new DatagramSocket(8080, InetAddress.getLocalHost());
			System.out.println("Starting client at " + socket.getLocalAddress() + ", port = " + socket.getLocalPort());
			destinationAddress = InetAddress.getByName("72.140.156.47");
			sender = new ClientEventSender(socket, destinationAddress, eventBuffer);
			receiver = new ClientEventReceiver(socket, stcEventBuffer);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		new Thread(sender).start();
		new Thread(receiver).start();
		ClientToServerGameEvent event = new ConnectionRequestEvent(234, 123);
		sender.getCtsEventBuffer().add(event);
	}

	public ClientEventSender getSender() {
		return sender;
	}

	public ClientEventReceiver getReceiver() {
		return receiver;
	}

}
