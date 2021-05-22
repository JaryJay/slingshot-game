package server.asyncrequesthandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import event.clienttoserver.RegisterObserverEvent;
import network.ClientToServerRequest;
import network.ServerToClientResponse;

public class ServerObserverNotifierDecorator extends GameServerDecorator implements Runnable {

	private List<ObserverSocketManager> observers;
	private ServerSocket serverSocket;

	public ServerObserverNotifierDecorator(AsyncRequestHandler server) {
		super(server);
		observers = new ArrayList<>();
		try {
			serverSocket = new ServerSocket(4445);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("Started listening for observers.");
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				System.out.println("Observer connecting from " + socket.getRemoteSocketAddress() + ". Creating observer socket manager.");
				ObserverSocketManager socketManager = new ObserverSocketManager(socket);
				observers.add(socketManager);
				Thread thread = new Thread(socketManager);
				thread.setPriority(Thread.MIN_PRIORITY);
				thread.start();
			} catch (IOException e) {
				System.out.println("I/O error: " + e);
			}
		}
	}

	@Override
	protected void doQueueRequest(ClientToServerRequest request) {
		if (request.getEvent() instanceof RegisterObserverEvent) {
		}
		for (ObserverSocketManager observer : observers) {
			sendEventMessage(observer, request.getEvent().getDescription());
		}
	}

	@Override
	protected void doPollResponse(ServerToClientResponse response) {
		for (ObserverSocketManager observer : observers) {
			sendEventMessage(observer, response.getEvent().getDescription());
			System.out.println("[Message sent]: sent " + response.getEvent().getDescription() + " to an observer.");
		}
	}

	private void sendEventMessage(ObserverSocketManager observer, String message) {
		observer.sendAsync(message);
	}

}
