package hand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import card.Card;
import card.CardVisibility;
import card.DealtCard;
import card.Deck;

public class Hand {
	private final List<Player> players;
	private long potValue;
	private Round activeRound;
	private int roundIndex;
	private final Stack<Card> deck;
	private final List<DealtCard> communityCards;
	private final Map<Player,HandState> handStates;
	
	public Hand(List<Player> players){
		this.players = players;
		this.potValue = 0;
		this.roundIndex = 0;
		deck = Deck.getShuffledDeck();
		communityCards = new ArrayList<DealtCard>();
		handStates = new HashMap<Player,HandState>();
		for(Player player: players)
			handStates.put(player, new HandState());
		startNextRound();
	}
	
	public void payOut(){
		if(players.size()==1){
			Player winner = players.get(0);
			winner.increaseBankroll(potValue);
			potValue = 0;
		}
		else{
			List<Player> winners = new ArrayList<Player>();
			for(Player player:players){
				
			}
		}
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

	private void addCommunityCard(){
		DealtCard card = new DealtCard(deck.pop(),CardVisibility.COMMUNITY);
		communityCards.add(card);
	}
	
	public List<DealtCard> getCommunityCards() {
		return communityCards;
	}

	public Map<Player, HandState> getHandStates() {
		return handStates;
	}
	
	public boolean startNextRound() {
		this.activeRound = new Round(this);
		switch(roundIndex){
		case 0: 
			for(int i=0;i<2;i++){
				for(Player player: players){
					HandState handState = handStates.get(player);
					handState.addCard(deck.pop(), CardVisibility.PRIVATE);
				}
			}
			break;
		case 1:
			addCommunityCard();
			addCommunityCard();
			addCommunityCard();
			break;
		case 2:
			addCommunityCard();
			break;
		case 3:
			addCommunityCard();
			break;
		default:
			return false;
		}
		roundIndex++;
		return true;
	}
}
