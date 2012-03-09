package round;

import java.util.ArrayList;
import java.util.List;

import player.HandPlayer;
import table.Hand;
import table.HandStatus;

public abstract class Round<T> {
	
	protected int currentPosition;
	protected final List<T> actions;
	protected boolean complete;
	protected HandPlayer activePlayer;
	protected final Hand hand;
	protected final int numberOfPlayers;
	protected final List<HandPlayer> handPlayers;
	
	public Round(Hand hand,int startingPosition, List<HandPlayer> handPlayers) {
		this.hand = hand;
		this.actions = new ArrayList<T>();
		this.complete = false;
		this.numberOfPlayers = handPlayers.size();
		this.handPlayers = handPlayers;
		//setNextPlayer increments startingPosition
		this.currentPosition = startingPosition-1;
		//Sets Active Player
		setNextPlayer();
	}

	public abstract void evaluateAction(T action);
	public abstract T getAction();
	protected abstract void finishRound();
	
	public void setNextPlayer(){
		int count = 0;
		while(count<numberOfPlayers){
			currentPosition++;
			currentPosition%=numberOfPlayers;
			HandPlayer player = handPlayers.get(currentPosition);
			if(player.getHandStatus()==HandStatus.PLAYING){
				this.setActivePlayer(player);
				return;
			}
			count++;
		}
		complete = true;
	}
	
	public String toString(){
		String s = "Current Position: "+currentPosition;
		s+="\tCompete: "+complete;
		s+="\tActive Player: "+activePlayer.getName();
		return s;
	}
	
	protected void checkAllPlayersHaveActed(){
		for(HandPlayer player : getHandPlayers()){
			if(!player.hasActed()){
				complete = false;
				return;
			}
		}
		complete = true;
	}
	
	protected void checkIfComplete() {
		int counter = findHowManyPlayersPlaying();
		if(counter==1){
			setComplete(true);
			hand.setEndConditionMet(true);
			finishRound();
		}
		checkAllPlayersHaveActed();
		if(complete){
			finishRound();
		}
	}
	
	protected int findHowManyPlayersPlaying(){
		int counter = 0;
		for(HandPlayer player: handPlayers){
			if(player.getHandStatus() == HandStatus.PLAYING || player.getHandStatus() == HandStatus.ALL_IN){
				counter++;
			}
		}
		return counter;
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
	
	public List<HandPlayer> getHandPlayers() {
		return handPlayers;
	}
	
	public HandPlayer getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(HandPlayer activePlayer) {
		this.activePlayer = activePlayer;
	}
}
