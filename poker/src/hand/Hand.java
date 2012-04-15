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
import card.PlayerHand;

public class Hand {
	private final List<Player> players;
	private long potValue;
	private Round activeRound;
	private int roundIndex;
	private final Stack<Card> deck;
	private final List<DealtCard> communityCards;
	private final Map<Player,HandPlayer> handStates;
	
	public Hand(List<Player> players){
		this.players = players;
		this.potValue = 0;
		this.roundIndex = 0;
		deck = Deck.getShuffledDeck();
		communityCards = new ArrayList<DealtCard>();
		handStates = new HashMap<Player,HandPlayer>();
		for(Player player: players)
			handStates.put(player, new HandPlayer());
	}
	
	private PlayerHand getPlayerHand(Player player){
		HandPlayer handPlayer = handStates.get(player);
		List<DealtCard> holeCards = handPlayer.getCards();
		return PlayerHand.getPlayerHandWithDealtCards(holeCards, communityCards);
	}
	public void payOut(){
		List<Player> winners = new ArrayList<Player>();
		PlayerHand winningHand = null;
		for(Player player: players){
			PlayerHand playerHand = getPlayerHand(player);
			if(winners.isEmpty()){
				winners.add(player);
				winningHand = playerHand;
			}
			else{
				int compare = playerHand.compareTo(winningHand);
				if(compare>0){
					winners.clear();
					winners.add(player);
					winningHand = playerHand;
				}
				else if(compare==0){
					winners.add(player);
				}
			}
		}
		//TODO Figure out remainder
		for(Player player: winners){
			player.increaseBankroll(potValue/winners.size());
			PlayerHand playerHand = getPlayerHand(player);
			System.out.println(player.getName()+" wins with "+playerHand.getFormattedName());
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

	public Map<Player, HandPlayer> getHandStates() {
		return handStates;
	}
	
	public boolean startNextRound() {
		return startNextRound(new Round(this));
	}
	
	public boolean startNextRound(Round round){
		this.activeRound = round;
		switch(roundIndex){
		case 0: 
			for(int i=0;i<2;i++){
				for(Player player: players){
					HandPlayer handState = handStates.get(player);
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
