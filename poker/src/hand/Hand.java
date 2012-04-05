package hand;

import java.util.List;

public class Hand {
	private final List<Player> players;
	private long potValue;
	private Round activeRound;
	
	public Hand(List<Player> players){
		this.players = players;
		this.potValue = 0;
		setActiveRound(new Round(this,players));
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public Player getPlayer(int index){
		return players.get(index);
	}
	
	public long getPotValue(){
		return potValue;
	}
	
	public void addToPot(long amount){
		potValue+=amount;
	}

	public Round getActiveRound() {
		return activeRound;
	}

	public void setActiveRound(Round activeRound) {
		this.activeRound = activeRound;
	}
}
