package hand;
import java.util.List;

import action.Action;
import action.BetAction;

public class Round {
	private final List<Player> players;
	private Player activePlayer;
	private long amountToCall;
	private final Hand hand;
	
	public Round(Hand hand, List<Player> players){
		this.hand = hand;
		this.players = players;
		this.amountToCall = 0;
		getNextPlayer();
	}

	public Player getNextPlayer(){
		int index = (activePlayer == null) ? -1 : players.indexOf(activePlayer);
		int size = players.size();
		for(int i =0 ; i<size; i++){
			index = (index + 1) % size;
			Player player = players.get(index);
			if(!player.isAllIn()){
				activePlayer = player;
				return player;
			}
		}
		return null;
	}
	
	public void evaluateAction(Action action){
		if(action.getPlayer()!=activePlayer){
			throw new IllegalArgumentException("Player is trying to act when it's not his/her turn.");
		}
		if(action instanceof BetAction){
			evaluateBet((BetAction) action);
		}
	}
	
	private void evaluateBet(BetAction betAction){
		Player player = betAction.getPlayer();
		long amount = betAction.getAmount();
		
		switch(betAction.getBetType()){
		case FOLD: players.remove(player); break;
		case CALL: player.addToPot(hand,amount); break;
		case CHECK: break;
		case BET: betPlayer(player,amount); break;
		case RAISE: raisePlayer(player,amount); break;
		}
		player.setHasActed(true);
		getNextPlayer();
	}
	
	private void resetHasActedForNonactivePlayers(){
		for(Player player: players)
			player.setHasActed(false);
	}
	private void raisePlayer(Player player, long amount) {
		long total = amount+amountToCall;
		player.addToPot(hand, total);
		amountToCall = total;
		resetHasActedForNonactivePlayers();
	}
	
	private void betPlayer(Player player, long amount) {
		player.addToPot(hand, amount);
		amountToCall = amount;
		resetHasActedForNonactivePlayers();
	}
	
	public Player getActivePlayer(){
		return activePlayer;
	}
	
	public long getAmountToCall(){
		return amountToCall;
	}
	public List<Player> getPlayers() {
		return players;
	}

	public Hand getHand() {
		return hand;
	}
}
