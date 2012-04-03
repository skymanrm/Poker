package card;

import java.util.Collections;
import java.util.Stack;

public class Deck {

	//Can't Initialize
	private Deck(){}
	
	public static Stack<Card> getShuffledDeck(){
		Stack<Card> deckCards = getDeck();
		Collections.shuffle(deckCards);
		return deckCards;
	}
	
	private static Stack<Card> getDeck(){
		Stack<Card> deckCards = new Stack<Card>();
		for(Card card: Card.values()){
			deckCards.add(card);
		}
		return deckCards;
	}
	

}
