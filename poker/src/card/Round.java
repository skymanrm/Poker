package card;

import java.util.ArrayList;
import java.util.List;

public abstract class Round<T> {
	protected int currentPosition;
	protected final List<T> actions;
	protected boolean complete;
	protected final List<HandPlayer> handPlayers;
	
	public Round(int startingPosition, List<HandPlayer> handPlayers) {
		this.currentPosition = startingPosition;
		this.actions = new ArrayList<T>();
		this.complete = false;
		this.handPlayers = handPlayers;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}

	public void addAction(T action){
		evaluateAction(action);
		actions.add(action);
	}
	
	public List<T> getActions() {
		return actions;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	
	public abstract void evaluateAction(T action);

	public List<HandPlayer> getHandPlayers() {
		return handPlayers;
	}
}
