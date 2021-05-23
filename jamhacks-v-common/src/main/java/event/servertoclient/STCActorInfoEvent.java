package event.servertoclient;

import actor.GameActor;

public class STCActorInfoEvent extends ServerToClientGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7978461141336201562L;
	private GameActor[] actors;

	public STCActorInfoEvent(GameActor[] actors) {
		super();
		this.actors = actors;
	}

	public GameActor[] getActors() {
		return actors;
	}

}
