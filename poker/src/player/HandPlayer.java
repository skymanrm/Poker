package player;

import java.util.ArrayList;
import java.util.List;

import table.FormedHand;
import table.Hand;
import table.HandStatus;

import card.Card;


public class HandPlayer extends TablePlayer implements Comparable<HandPlayer>{
	
	private final Hand hand;
	
	private final List<Card> cards;
	private final int relativeSeat;
	private HandStatus handStatus;
	private boolean acted;
	private int amountCommittedToRound;
	private boolean canRaise;
	
	private FormedHand formedHand;
	
	public HandPlayer(TablePlayer tablePlayer, Hand hand, HandStatus handStatus) {
		super(tablePlayer);
		this.hand = hand;
		this.cards = new ArrayList<Card>();
		this.relativeSeat = determineRelativeSeat();
		this.setHandStatus(handStatus);
		this.acted = false;
		this.setCanRaise(false);
		this.amountCommittedToRound = 0;
		this.formedHand = null;
	}
	
	private int determineRelativeSeat(){
		int buttonSeat = getTable().getButtonSeat();
		int maxSeats = getTable().getMaxSeats();
		int absoluteSeat = getAbsoluteSeat();
		return ((maxSeats - buttonSeat) + absoluteSeat) % maxSeats;
	}
	
	public Hand getHand() {
		return hand;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void addCardToHand(Card card){
		cards.add(card);
	}
	public int getRelativeSeat() {
		return relativeSeat;
	}

	public HandStatus getHandStatus() {
		return handStatus;
	}

	public void setHandStatus(HandStatus handStatus) {
		this.handStatus = handStatus;
	}

	@Override
	public int compareTo(HandPlayer o) {
		if(relativeSeat > o.relativeSeat){
			return 1;
		}
		else{
			return -1;
		}
	}
	
	public String toString(){
		String s = super.toString();
		s+="\t\tHole Cards: ";
		for(Card card : cards){
			s+=card.toString()+", ";
		}
		return s;
	}

	public boolean hasActed() {
		return acted;
	}

	public void setActed(boolean acted) {
		this.acted = acted;
	}
	
	public void addToPot(int amount){
		decreaseTableBankroll(amount);
		hand.getPot().addToTotalValue(amount);
		amountCommittedToRound+=amount;
	}

	public boolean canRaise() {
		return canRaise;
	}

	public void setCanRaise(boolean canRaise) {
		this.canRaise = canRaise;
	}
	public int getAmountCommittedToRound() {
		return amountCommittedToRound;
	}
	public void resetForNewRound(){
		amountCommittedToRound = 0;
		if(handStatus == HandStatus.PLAYING){
			acted = false;
			canRaise = true;
		}
	}

	public FormedHand getFormedHand() {
		if(formedHand == null){
			formedHand = new FormedHand(this, hand.getCommunityCards());
		}
		return formedHand;
	}
	
	public String victoryString(){
		return getName()+" wins with a "+formedHand.getDisplayName();
	}
}
