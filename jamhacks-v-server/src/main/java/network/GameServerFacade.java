package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import event.GameEventDeserializer;
import event.GameEventSerializer;
import event.clienttoserver.ClientToServerGameEvent;
import event.servertoclient.ServerToClientGameEvent;
import server.asyncrequesthandler.AsyncRequestHandler;

public class GameServerFacade implements Runnable {

	private DatagramSocket socket;
	private AsyncRequestHandler requesthandler;
	protected boolean running = false;

	public GameServerFacade(AsyncRequestHandler requesthandler) {
		this.requesthandler = requesthandler;
	}

	public void init() {
		try {
			socket = new DatagramSocket(4445, InetAddress.getLocalHost());
			System.out.println("Server started at " + socket.getLocalAddress() + ", port = " + socket.getLocalPort() + ".");
		} catch (SocketException | UnknownHostException e) {
			System.err.println("Server failed to start.");
			e.printStackTrace();
		}
	}

	public void end() {
		running = false;
		socket.close();
	}

	@Override
	public void run() {
		running = true;
		byte[] buffer = new byte[1024];
		new Thread(() -> {
			while (running) {
				DatagramPacket packet = receivePacket(buffer);
				ClientToServerGameEvent event = deserializePacket(packet);
				ClientToServerRequest request = createRequest(packet, event);
				requesthandler.queueRequest(request);
				Thread.yield();
			}
		}).start();
		new Thread(() -> {
			while (running) {
				if (requesthandler.hasResponse()) {
					ServerToClientResponse response = requesthandler.pollResponse();
					byte[] serializeResponse = serializeResponse(response.getEvent());
					sendPacket(response.getDetails(), serializeResponse);
					System.out.println("[Message sent to " + response.getDetails().getAddress().getHostAddress() + "]: " + response.getEvent().getDescription());
					System.out.println("Packet Size: " + serializeResponse + " bytes");
				}
				Thread.yield();
			}
		}).start();
	}

	private ClientToServerRequest createRequest(DatagramPacket packet, ClientToServerGameEvent event) {
		InetAddress address = packet.getAddress();
		int port = packet.getPort();
		ClientDetails clientDetails = new ClientDetails(address, port);
		return new ClientToServerRequest(clientDetails, event);
	}

	private byte[] serializeResponse(ServerToClientGameEvent response) {
		return GameEventSerializer.serialize(response);
	}

	private void sendPacket(ClientDetails details, byte[] data) {
		InetAddress address = details.getAddress();
		int port = details.getPort();
		DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private DatagramPacket receivePacket(byte[] buffer) {
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		try {
			socket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return packet;
	}

	private ClientToServerGameEvent deserializePacket(DatagramPacket packet) {
		return (ClientToServerGameEvent) GameEventDeserializer.deserialize(packet.getData());
	}

}
