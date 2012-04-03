package test_ranking;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import card.Card;
import card.HandRanking;
import card.PlayerHand;

public class HighCardTest {

	@Test
	public void testHighCard() {
		PlayerHand ph = new PlayerHand();
		List<Card> cards = new ArrayList<Card>();
		cards.add(Card.ACE_SPADES);
		cards.add(Card.KING_CLUBS);
		cards.add(Card.JACK_CLUBS);
		cards.add(Card.TEN_HEARTS);
		cards.add(Card.NINE_DIAMONDS);
		ph.getBestHand(cards, new ArrayList<Card>());
		assertEquals("Result",HandRanking.HIGH_CARD,ph.getHandRanking());
	}

}
