package round;

import java.util.ArrayList;
import java.util.List;

import card.Hand;

import player.HandPlayer;



public abstract class Round<T> {
	protected int currentPosition;
	protected final List<T> actions;
	protected boolean complete;
	protected final List<HandPlayer> handPlayers;
	protected HandPlayer activePlayer;
	
	public Round(int startingPosition, List<HandPlayer> handPlayers) {
		this.currentPosition = startingPosition;
		this.actions = new ArrayList<T>();
		this.complete = false;
		this.handPlayers = handPlayers;
		this.activePlayer = handPlayers.get(startingPosition);
	}

	public abstract void evaluateAction(T action);
	public abstract HandPlayer getNextPlayer();
	public abstract T getAction();
	
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
	
	public List<HandPlayer> getHandPlayers() {
		return handPlayers;
	}
	
	public Hand getHand(){
		return getHandPlayers().get(0).getHand();
	}
	
	public HandPlayer getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(HandPlayer activePlayer) {
		this.activePlayer = activePlayer;
	}

}
