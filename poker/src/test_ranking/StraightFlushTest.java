package test_ranking;

import org.junit.Test;

import card.Card;
import card.HandRanking;

public class StraightFlushTest {

	@Test
	public void testRoyalFlush() {
		AllHandRankingTests.testCards(HandRanking.STRAIGHT_FLUSH, 
				Card.ACE_CLUBS,
				Card.KING_CLUBS,
				Card.JACK_CLUBS,
				Card.TEN_CLUBS,
				Card.QUEEN_CLUBS);
	}
}
