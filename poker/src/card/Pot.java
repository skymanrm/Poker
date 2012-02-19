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
		setTotalValue(0);
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
	
	public int getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}
	
}
