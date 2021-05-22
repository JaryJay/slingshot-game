package server.asyncrequesthandler;

import network.ClientToServerRequest;
import network.ServerToClientResponse;

public abstract class GameServerDecorator implements AsyncRequestHandler {

	private AsyncRequestHandler server;

	public GameServerDecorator(AsyncRequestHandler server) {
		this.server = server;
	}

	@Override
	public final void queueRequest(ClientToServerRequest request) {
		doQueueRequest(request);
		server.queueRequest(request);
	}

	protected abstract void doQueueRequest(ClientToServerRequest request);

	@Override
	public final ServerToClientResponse pollResponse() {
		ServerToClientResponse response = server.pollResponse();
		doPollResponse(response);
		return response;
	}

	protected abstract void doPollResponse(ServerToClientResponse pollResponse);

	@Override
	public final boolean hasResponse() {
		return server.hasResponse();
	}

}
