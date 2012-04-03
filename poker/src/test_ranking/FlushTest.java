package test_ranking;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import card.Card;
import card.HandRanking;
import card.PlayerHand;

public class FlushTest {

	@Test
	public void testFlush() {
		PlayerHand ph = new PlayerHand();
		List<Card> cards = new ArrayList<Card>();
		cards.add(Card.ACE_SPADES);
		cards.add(Card.JACK_SPADES);
		cards.add(Card.TEN_SPADES);
		cards.add(Card.NINE_SPADES);
		cards.add(Card.EIGHT_SPADES);
		ph.getBestHand(cards, new ArrayList<Card>());
		assertEquals("Result",HandRanking.FLUSH,ph.getHandRanking());
	}

}
