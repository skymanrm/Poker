package card;

public abstract class Action {
	
	private final long time;
	private final Round<?> round;
	private final HandPlayer handPlayer; 
	
	public Action(long time, Round<?> round, HandPlayer handPlayer) {
		this.time = time;
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
