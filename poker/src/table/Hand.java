package table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import player.HandPlayer;
import player.TablePlayer;
import round.DealingRound;
import round.HoldemManager;
import round.Round;
import round.RoundManager;
import card.Card;
import card.Visibility;

public class Hand {

	private final Table table;
	private final List<HandPlayer> playersInHand;
	private final List<Round<?>> rounds;
	private final Stack<Card> cards;
	private final List<Card> communityCards;
	private final GameType gameType;
	private final int buttonSeat;
	private boolean endConditionMet;
	private int roundIndex;
	private HandPlayer lastRaiser;
	private RoundManager roundManager;
	private final Pot pot;
	
	public Hand(GameType gameType, Table table, List<TablePlayer> tablePlayers){
		this.table = table;
		playersInHand = new ArrayList<HandPlayer>();
		this.initPlayersInHand(tablePlayers);
		
		Collections.sort(playersInHand);
		cards = Card.newDeck();
		communityCards = new ArrayList<Card>();
		rounds = new ArrayList<Round<?>>();
		this.gameType = gameType;
		this.setEndConditionMet(false);
		this.roundIndex = 0;
		this.buttonSeat = table.getButtonSeat();
		this.setLastRaiser(null);
		this.pot = new Pot(playersInHand);
		
		initRoundManager();
		runRounds();
	}
	
	private void initRoundManager(){
		if(this.gameType == GameType.HOLDEM){
			roundManager = new HoldemManager();
		}
	}
	
	private void runRounds() {
		while(!endConditionMet){
			Round<?> round = roundManager.getRoundForIndex(this, roundIndex);
			if(round==null){
				break;
			}
			DealingRound dealingRound = (DealingRound) round;
			System.out.println("\nRound Index: "+roundIndex+"\n");
			System.out.println(toString());
			while(!dealingRound.isComplete()){
				HandPlayer player = dealingRound.getActivePlayer();
				System.out.println(player.toString());
				dealingRound.evaluateAction(dealingRound.getAction());
				System.out.println(player.toString());
				System.out.println(dealingRound.toString());
			}
			rounds.add(dealingRound);
			roundIndex++;
		}
		endHand();
	}

	private void endHand() {
		//TODO test all ins
		for(Pot p: formPots()){
			List<HandPlayer> winners = findWinningPlayers(p.getEligiblePlayers());
			//TODO don't just disregard remainders
			int divisor = winners.size();
			for(HandPlayer player: winners){
				player.increaseTableBankroll(p.getTotalValue()/divisor);
				if(p.isContested()){
					System.out.println(player.victoryString());
				}
				else{
					System.out.println("Everyone Folded to "+player.getName());
				}
			}
			System.out.println(toString());
		}
	}
	
	private List<HandPlayer> findWinningPlayers(List<HandPlayer> eligiblePlayers) {
		List<HandPlayer> winners = new ArrayList<HandPlayer>();
		if(eligiblePlayers.size()==1){
			winners.add(eligiblePlayers.get(0));
		}
		else{
			List<FormedHand> hands = new ArrayList<FormedHand>();
			
			for(HandPlayer player: eligiblePlayers){
				hands.add(player.getFormedHand());
			}
			
			Collections.sort(hands);
			Collections.reverse(hands);
			FormedHand topHand = hands.get(0);
			winners.add(topHand.getPlayer());
			for(int i = 1; i<hands.size();i++){
				FormedHand hand = hands.get(i);
				if(topHand.compareTo(hand) == 0){
					winners.add(hand.getPlayer());
				}
				else{
					break;
				}
			}
		}
		return winners;
	}
	
	private List<Pot> formPots(){
		List<Pot> pots = new ArrayList<Pot>();
		pots.add(pot);
		pots.addAll(pot.getSidePots());
		return pots;
	}
	
	private void initPlayersInHand(List<TablePlayer> tablePlayers){
		for(TablePlayer player: tablePlayers){
			playersInHand.add(new HandPlayer(player, this, HandStatus.PLAYING));
		}
	}
	
	public List<HandPlayer> getPlayersInHand() {
		return playersInHand;
	}

	public GameType getGameType() {
		return gameType;
	}

	public List<Round<?>> getRounds() {
		return rounds;
	}

	public Stack<Card> getCards() {
		return cards;
	}

	public boolean isEndConditionMet() {
		return endConditionMet;
	}

	public void setEndConditionMet(boolean endConditionMet) {
		this.endConditionMet = endConditionMet;
	}

	public int getRoundIndex() {
		return roundIndex;
	}

	public void incrementRoundIndex() {
		this.roundIndex++;
	}
	
	public String toString(){
		String s ="\nCommunity Cards:";
		for(Card card : getCommunityCards()){
			s+=card.toString()+", ";
		}
		return s;
	}

	public List<Card> getCommunityCards() {
		return communityCards;
	}

	public int getButtonSeat() {
		return buttonSeat;
	}

	public Table getTable() {
		return table;
	}

	public HandPlayer getLastRaiser() {
		return lastRaiser;
	}

	public void setLastRaiser(HandPlayer lastRaiser) {
		this.lastRaiser = lastRaiser;
	}
	
	public void addCardToPlayer(Visibility visibility, HandPlayer player){
		Card card = cards.pop();
		card.setVisibility(visibility);
		player.addCardToHand(card);
	}
	
	public void addCommunityCard(){
		Card card = cards.pop();
		card.setVisibility(Visibility.COMMUNITY);
		communityCards.add(card);
	}
	public Pot getPot() {
		return pot;
	}
}
