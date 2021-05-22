package server.asyncrequesthandler;

import network.ClientToServerRequest;
import network.ServerToClientResponse;

public interface AsyncRequestHandler {

	public boolean hasResponse();

	public void queueRequest(ClientToServerRequest request);

	public ServerToClientResponse pollResponse();

}
