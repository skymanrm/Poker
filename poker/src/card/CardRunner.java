package card;

import java.util.ArrayList;
import java.util.List;

public class CardRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PlayerHand ph = new PlayerHand();
		List<Card> cards = new ArrayList<Card>();
		cards.add(Card.ACE_SPADES);
		cards.add(Card.KING_SPADES);
		cards.add(Card.QUEEN_SPADES);
		cards.add(Card.JACK_SPADES);
		cards.add(Card.TEN_SPADES);
		ph.getBestHand(cards, new ArrayList<Card>());
	}

}
