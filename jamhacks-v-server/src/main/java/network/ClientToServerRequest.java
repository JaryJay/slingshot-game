package network;

import event.clienttoserver.ClientToServerGameEvent;

public class ClientToServerRequest {

	private ClientDetails details;
	private ClientToServerGameEvent event;

	public ClientToServerRequest(ClientDetails details, ClientToServerGameEvent event) {
		this.details = details;
		this.event = event;
	}

	public ClientDetails getDetails() {
		return details;
	}

	public ClientToServerGameEvent getEvent() {
		return event;
	}

}
