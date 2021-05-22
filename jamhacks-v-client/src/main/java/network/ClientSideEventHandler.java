package network;

import java.util.Optional;

import event.servertoclient.STCTestGameEvent;
import event.servertoclient.ServerToClientGameEvent;
import event.servertoclient.ConnectionAcceptanceEvent;

public class ClientSideEventHandler {

	public Optional<byte[]> handle(ServerToClientGameEvent event) {
		if (event instanceof ConnectionAcceptanceEvent) {
//			timer.getState().getIdToActors().put
			return Optional.empty();
		} else if (event instanceof STCTestGameEvent) {
			System.out.println("[Message received]: " + event.getDescription());
			return Optional.empty();
		}
		return Optional.empty();
	}

}
