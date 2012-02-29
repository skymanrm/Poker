package round;

import java.util.ArrayList;
import java.util.List;


import player.HandPlayer;
import player.HandPlayer.HandStatus;
import table.Hand;

public abstract class Round<T> {
	protected int currentPosition;
	protected final List<T> actions;
	protected boolean complete;
	protected final List<HandPlayer> handPlayers;
	protected HandPlayer activePlayer;
	protected String roundTitle;
	
	public Round(int startingPosition, List<HandPlayer> handPlayers) {
		
		this.actions = new ArrayList<T>();
		this.complete = false;
		this.handPlayers = handPlayers;
		this.roundTitle = "Round";
		//setNextPlayer increments startingPosition
		this.currentPosition = startingPosition-1;
		//Sets Active Player
		setNextPlayer();
	}

	public abstract void evaluateAction(T action);
	public abstract T getAction();
	
	public void setNextPlayer(){
		int count = 0;
		while(count<10){
			currentPosition++;
			currentPosition%=handPlayers.size();
			HandPlayer player = handPlayers.get(currentPosition);
			if(player.getHandStatus()==HandStatus.PLAYING){
				this.setActivePlayer(player);
				break;
			}
		}
	}
	
	public String toString(){
		String s = roundTitle+"\n";
		s+="Current Position: "+currentPosition;
		s+="\tCompete: "+complete;
		s+="\tActive Player: "+activePlayer.getName();
		return s;
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
