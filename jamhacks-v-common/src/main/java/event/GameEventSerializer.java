package event;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import event.servertoclient.ConnectionAcceptanceEvent;

public class GameEventSerializer {

	private GameEventSerializer() {
	}

	/**
	 * Serializes a GameEvent into a byte[].
	 * 
	 * @param event the event to serialize
	 * @return the byte[] containing the data of the event.
	 */
	public static byte[] serialize(GameEvent event) {
		int size = event instanceof ConnectionAcceptanceEvent ? 1024 : 512;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream(size); ObjectOutputStream out = new ObjectOutputStream(baos)) {
			out.writeObject(event);
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Could not serialize GameEvent with description \"" + event.getDescription() + "\".");
	}

}
