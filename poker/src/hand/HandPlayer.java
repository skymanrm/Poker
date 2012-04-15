package hand;

import java.util.ArrayList;
import java.util.List;

import card.Card;
import card.CardVisibility;
import card.DealtCard;

public class HandPlayer {
	private boolean allIn;
	private final List<DealtCard> cards;
	
	public HandPlayer(){
		this.allIn = false;
		this.cards = new ArrayList<DealtCard>();
	}
	
	public boolean isAllIn() {
		return allIn;
	}

	public void setAllIn(boolean allIn) {
		this.allIn = allIn;
	}
	
	public void addCard(Card card, CardVisibility visibility){
		cards.add(new DealtCard(card,visibility));
	}
	
	public List<DealtCard> getCards(){
		return cards;
	}
}
