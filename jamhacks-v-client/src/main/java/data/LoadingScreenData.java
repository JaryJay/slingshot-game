package data;

import java.util.List;
import java.util.Map;

import actor.GameActor;
import map.GameObstacle;

public class LoadingScreenData extends GameData {

	private String username = "Unnamed";

	private long[] frameNumberUserIdActorIdEventId;
	private Map<Long, GameActor> actorIdToActors;
	private List<GameObstacle> obstacles;

	public String getUsername() {
		return username;
	}

	public void setTextInput(String textInput) {
		this.username = textInput;
	}

	public long[] getFrameNumberUserIdActorIdEventId() {
		return frameNumberUserIdActorIdEventId;
	}

	public void setFrameNumberUserIdActorIdEventId(long frameNumber, long userId, long actorId, long eventId) {
		this.frameNumberUserIdActorIdEventId[0] = frameNumber;
		this.frameNumberUserIdActorIdEventId[1] = userId;
		this.frameNumberUserIdActorIdEventId[2] = actorId;
		this.frameNumberUserIdActorIdEventId[3] = eventId;
	}

	public Map<Long, GameActor> getActorIdToActors() {
		return actorIdToActors;
	}

	public void setActorIdToActors(Map<Long, GameActor> actorIdToActors) {
		this.actorIdToActors = actorIdToActors;
	}

	public List<GameObstacle> getObstacles() {
		return obstacles;
	}

	public void setObstacles(List<GameObstacle> obstacles) {
		this.obstacles = obstacles;
	}

}
