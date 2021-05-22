package network;

import event.servertoclient.ServerToClientGameEvent;

public class ServerToClientResponse {

	private ClientDetails details;
	private ServerToClientGameEvent event;

	public ServerToClientResponse(ClientDetails details, ServerToClientGameEvent event) {
		this.details = details;
		this.event = event;
	}

	public ClientDetails getDetails() {
		return details;
	}

	public ServerToClientGameEvent getEvent() {
		return event;
	}

}
