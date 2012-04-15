package hand;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import action.Action;
import action.BetAction;

public class Round {
	
	private final List<Player> players;
	private Player activePlayer;
	private long amountToCall;
	private final Hand hand;
	private final Map<Player,RoundState> roundStates;
	private final Map<Player,HandPlayer> handStates;
	
	public Round(Hand hand){
		this.hand = hand;
		//this will mutate the hand's players (this is desired)
		this.players = hand.getPlayers();
		this.amountToCall = 0;
		this.handStates = hand.getHandStates();
		roundStates = new HashMap<Player,RoundState>();
		for(Player player: players)
			roundStates.put(player, new RoundState());
	}

	public Player getNextPlayer(){
		int index = (activePlayer == null) ? -1 : players.indexOf(activePlayer);
		int size = players.size();
		if(isKillable())
			return null;
		for(int i =0 ; i<size; i++){
			index = (index + 1) % size;
			Player player = players.get(index);
			RoundState roundState = roundStates.get(player);
			HandPlayer handState = handStates.get(player);
			if(!handState.isAllIn() && !roundState.hasActed()){
				activePlayer = player;
				return player;
			}
		}
		activePlayer = null;
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
		case CALL: callPlayer(player); break;
		case CHECK: break;
		case BET: betPlayer(player,amount); break;
		case RAISE: raisePlayer(player,amount); break;
		}
		//Should not get called if someone folded
		RoundState roundState = roundStates.get(player);
		roundState.setHasActed(true);
		getNextPlayer();
	}
	
	private void resetHasActedForPlayers(){
		for(RoundState roundState: roundStates.values())
			roundState.setHasActed(false);
	}
	
	private void callPlayer(Player player){
		RoundState roundState = roundStates.get(player);
		long total = amountToCall - roundState.getAmountCommittedToRound();
		player.addToPot(hand,roundStates.get(player),total);
	}
	
	private void raisePlayer(Player player, long amount) {
		long total = amount+amountToCall;
		player.addToPot(hand,roundStates.get(player), total);
		amountToCall = total;
		resetHasActedForPlayers();
	}
	
	private void betPlayer(Player player, long amount) {
		if(amountToCall!=0)
			throw new IllegalArgumentException("Can't be of BetType : BET if there is already an amount to call");
		player.addToPot(hand,roundStates.get(player), amount);
		amountToCall = amount;
		resetHasActedForPlayers();
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
	
	public boolean isKillable(){
		return players.size()==1; 
	}
	
	public RoundState getRoundStateForPlayer(Player player){
		return roundStates.get(player);
	}
}
