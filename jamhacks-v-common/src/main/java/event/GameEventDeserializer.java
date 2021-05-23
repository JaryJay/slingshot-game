package event;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GameEventDeserializer {

	private GameEventDeserializer() {
	}

	/**
	 * Deserializes a byte[] into a GameEvent.
	 * 
	 * @param eventData the byte[] to deserialize
	 * @return the GameEvent
	 */
	public static GameEvent deserialize(byte[] eventData) {
		try (ByteArrayInputStream bais = new ByteArrayInputStream(eventData); ObjectInputStream in = new ObjectInputStream(bais)) {
			GameEvent event = (GameEvent) in.readObject();
			return event;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Could not deserialize byte array " + eventData + " into a GameEvent.");
	}

}
