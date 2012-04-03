package hand;

import java.util.List;

public class Hand {
	private final List<Player> players;
	
	public Hand(List<Player> players){
		this.players = players;
	}

	public List<Player> getPlayers() {
		return players;
	}
	
}
