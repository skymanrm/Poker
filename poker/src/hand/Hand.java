package hand;

import java.util.List;

import action.Action;
import action.BetAction;
import action.BetType;

public class Hand {
	private final List<Player> players;
	private Player activePlayer;
	
	public Hand(List<Player> players){
		this.players = players;
	}
	
	@SuppressWarnings("unused")
	public Player getNextPlayer(){
		int index = (activePlayer == null) ? -1 : players.indexOf(activePlayer);
		int size = players.size();
		for(int i =0 ; i<size; i++){
			index = (index + 1) % size;
			Player player = players.get(index);
			if(!player.isAllIn())
				activePlayer = player;
				return player;
		}
		return null;
	}
	
	public void evaluateAction(Action action){
		if(action instanceof BetAction){
			evaluateBet((BetAction) action);
		}
	}
	
	private void evaluateBet(BetAction betAction){
		if(betAction.getBetType()==BetType.Fold){
			foldPlayer(betAction.getPlayer());
		}
	}
	
	private void foldPlayer(Player player) {
		
	}

	public List<Player> getPlayers() {
		return players;
	}
	
	public Player getPlayer(int index){
		return players.get(index);
	}
}
