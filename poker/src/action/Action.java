package action;

import player.HandPlayer;
import round.Round;

public abstract class Action {
	
	private final long time;
	private final Round<?> round;
	private final HandPlayer handPlayer; 
	
	public Action(Round<?> round, HandPlayer handPlayer) {
		this.time = System.currentTimeMillis();
		this.round = round;
		this.handPlayer = handPlayer;
	}

	public long getTime() {
		return time;
	}

	public Round<?> getRound() {
		return round;
	}

	public HandPlayer getHandPlayer() {
		return handPlayer;
	}
}
