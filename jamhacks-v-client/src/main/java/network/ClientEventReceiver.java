package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Queue;

import context.GameContextWrapper;
import data.SlingShotData;
import event.GameEventSerializer;
import event.clienttoserver.ClientToServerGameEvent;
import event.inputfactory.VelocityChangeEvent;
import event.servertoclient.STCInputFrameEvent;
import event.servertoclient.ServerToClientGameEvent;

public class ClientEventReceiver implements Runnable {

	private DatagramSocket socket;
	private boolean ended;
	private Queue<ServerToClientGameEvent> stcEventBuffer;
	GameContextWrapper wrapper;

	public ClientEventReceiver(DatagramSocket socket, Queue<ServerToClientGameEvent> stcEventBuffer, GameContextWrapper wrapper) {
		this.socket = socket;
		this.stcEventBuffer = stcEventBuffer;
		this.wrapper = wrapper;
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
			System.out.println("[Message received]: " + event.getDescription() + " ID: " + event.getId());
			if (event instanceof STCInputFrameEvent) {
				STCInputFrameEvent i = (STCInputFrameEvent) event;
				ClientToServerGameEvent clientToServerGameEvent = i.getInputFrame().getEvents().get(0);
				if (clientToServerGameEvent instanceof VelocityChangeEvent) {
					VelocityChangeEvent j = (VelocityChangeEvent) clientToServerGameEvent;
					((SlingShotData) wrapper.getContext().getData()).x += j.GetMoveVector().x;
					((SlingShotData) wrapper.getContext().getData()).y += j.GetMoveVector().y;
				}
			}
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
