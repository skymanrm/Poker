package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import card.HandPlayer.HandStatus;

public class Hand {

	private final Table table;
	private final List<HandPlayer> playersInHand;
	//private final List<Round> rounds;
	private final Stack<Card> cards;
	private final List<Card> communityCards;
	private final GameType gameType;
	private final int buttonSeat;
	private boolean endConditionMet;
	private int roundIndex;
	private int potValue;
	
	public Hand(GameType gameType, Table table, List<TablePlayer> tablePlayers){
		this.table = table;
		playersInHand = new ArrayList<HandPlayer>();
		this.initPlayersInHand(tablePlayers);
		
		Collections.sort(playersInHand);
		cards = Card.newDeck();
		communityCards = new ArrayList<Card>();
		//rounds = new ArrayList<Round>();
		this.gameType = gameType;
		this.setEndConditionMet(false);
		this.roundIndex = 0;
		this.buttonSeat = table.getButtonSeat();
		this.potValue = 0;
	}

	public int getPotValue(){
		return potValue;
	}
	
	public void increasePotValue(int amount){
		potValue+=amount;
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

//	public List<Round> getRounds() {
//		return rounds;
//	}

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
		s+="\n";
		for(HandPlayer player: getPlayersInHand()){
			s+=player.toString();
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
}
