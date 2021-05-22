package server.asyncrequesthandler;

import java.util.LinkedList;
import java.util.Queue;

import logic.ServerSideGameLogicTimer;
import network.ClientToServerRequest;
import network.ServerToClientResponse;
import util.LimitedQueue;

public class GameServer implements AsyncRequestHandler, Runnable {

	private Queue<ClientToServerRequest> requests;
	private Queue<ServerToClientResponse> responses;
	private ServerSideGameLogicTimer gameLogicTimer;

	public GameServer() {
		requests = new LimitedQueue<>(100);
		responses = new LinkedList<>();
		gameLogicTimer = new ServerSideGameLogicTimer(requests, responses);
	}

	@Override
	public void run() {
		new Thread(gameLogicTimer).start();
	}

	@Override
	public void queueRequest(ClientToServerRequest request) {
		// check if the request is already in requests
		if (!requests.contains(request)) {
			requests.add(request);
		}
		gameLogicTimer.addClientDetails(request.getDetails());
		System.out.println("[Message received from " + request.getDetails().getAddress().getHostAddress() + "]: " + request.getEvent().getDescription() + " Event ID: " + request.getEvent().getId());
	}

	@Override
	public boolean hasResponse() {
		return !responses.isEmpty();
	}

	@Override
	public ServerToClientResponse pollResponse() {
		return responses.poll();
	}

}
