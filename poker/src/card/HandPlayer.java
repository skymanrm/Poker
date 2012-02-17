package card;

import java.util.ArrayList;
import java.util.List;

public class HandPlayer implements Comparable<HandPlayer>{
	
	private final Hand hand;
	private final TablePlayer tablePlayer;

	private final List<Card> cards;
	private final int relativeSeat;
	private HandStatus handStatus;
	private boolean acted;
	private int amountCommittedToRound;
	
	public enum HandStatus {
	    FOLDED, ALL_IN, PLAYING, SAT_OUT
	}
	
	public HandPlayer(TablePlayer tablePlayer, Hand hand, HandStatus handStatus) {
		this.tablePlayer = tablePlayer;
		this.hand = hand;
		this.cards = new ArrayList<Card>();
		this.relativeSeat = determineRelativeSeat();
		this.setHandStatus(handStatus);
		this.acted = false;
		this.amountCommittedToRound = 0;
	}
	
	private int determineRelativeSeat(){
		int buttonSeat = tablePlayer.getTable().getButtonSeat();
		int maxSeats = tablePlayer.getTable().getMaxSeats();
		int absoluteSeat = tablePlayer.getAbsoluteSeat();
		return ((maxSeats - buttonSeat) + absoluteSeat) % maxSeats;
	}
	
	
	public TablePlayer getTablePlayer() {
		return tablePlayer;
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
		String s = tablePlayer.getPlayer().getName();
		s+="\n"+handStatus.toString();
		s+="\nRelative Seat: "+relativeSeat;
		s+="\nHole Cards:\n";
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
	
	public int getTableBankroll(){
		return getTablePlayer().getTableBankroll();
	}
	
	public void addToPot(int amount){
		getTablePlayer().decreaseTableBankroll(amount);
		hand.increasePotValue(amount);
		amountCommittedToRound+=amount;
	}

	public int getAmountCommitedToRound() {
		return amountCommittedToRound;
	}
}
