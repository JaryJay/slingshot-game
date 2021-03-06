package network;

import event.clienttoserver.CTSAcknowledgmentEvent;
import event.clienttoserver.ClientToServerGameEvent;
import event.servertoclient.STCInputForwardEvent;
import event.servertoclient.ServerToClientGameEvent;

public class ClientSideEventHandler {

	public ClientToServerGameEvent handle(ServerToClientGameEvent event) {
		if (event instanceof STCInputForwardEvent) {
			return new CTSAcknowledgmentEvent(event.getId());
		}
		return null;
	}

}
