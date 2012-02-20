package card;

import java.util.ArrayList;
import java.util.List;

import player.HandPlayer;


public class Pot {

	private final List<Pot> sidePots;
	private List<HandPlayer> eligiblePlayers;
	private int totalValue;
	
	public Pot(){
		sidePots = new ArrayList<Pot>();
		eligiblePlayers = new ArrayList<HandPlayer>();
		totalValue = 0;
	}

	public Pot(List<HandPlayer> eligiblePlayers, int totalValue){
		sidePots = new ArrayList<Pot>();
		this.eligiblePlayers = eligiblePlayers;
		this.totalValue = totalValue;
	}
	
	public void addSidePot(HandPlayer player, List<HandPlayer> players){
		int amountCanWin = totalValue;
		int allInAmount = player.getAmountCommittedToRound();
		
		for(HandPlayer otherPlayer: players){
			if(otherPlayer!=player){
				int amountCommitted = otherPlayer.getAmountCommittedToRound();
				if(amountCommitted < allInAmount){
					amountCanWin+=amountCommitted;
				}
				else{
					amountCanWin+=allInAmount;
				}
			}
		}
		sidePots.add(new Pot(eligiblePlayers, amountCanWin));
		totalValue=0;
		eligiblePlayers.remove(player);
	}
	public List<Pot> getSidePots() {
		return sidePots;
	}

	public List<HandPlayer> getEligiblePlayers() {
		return eligiblePlayers;
	}
	
	public void setEligiblePlayers(List<HandPlayer> eligiblePlayers) {
		this.eligiblePlayers = eligiblePlayers;
	}
	
	public void removePlayerFromPot(HandPlayer player){
		this.eligiblePlayers.remove(player);
		for(Pot pot: sidePots){
			pot.removePlayerFromPot(player);
		}
	}
	
	public int getTotalValue() {
		return totalValue;
	}

	public void addToTotalValue(int amount) {
		totalValue += amount;
	}
}
