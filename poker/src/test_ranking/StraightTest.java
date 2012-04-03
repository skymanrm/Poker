package test_ranking;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import card.Card;
import card.HandRanking;
import card.PlayerHand;

public class StraightTest {

	@Test
	public void testStraight() {
		PlayerHand ph = new PlayerHand();
		List<Card> cards = new ArrayList<Card>();
		cards.add(Card.QUEEN_CLUBS);
		cards.add(Card.JACK_DIAMONDS);
		cards.add(Card.NINE_SPADES);
		cards.add(Card.TEN_HEARTS);
		cards.add(Card.EIGHT_DIAMONDS);
		ph.getBestHand(cards, new ArrayList<Card>());
		assertEquals("Result",HandRanking.STRAIGHT,ph.getHandRanking());
	}

}
