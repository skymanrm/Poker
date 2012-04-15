package test_ranking;

import org.junit.Test;

import card.Card;
import card.HandRanking;

public class FlushTest {

	@Test
	public void testFlush() {
		AllHandRankingTests.testCards(HandRanking.FLUSH, 
				Card.ACE_CLUBS,
				Card.KING_CLUBS,
				Card.JACK_CLUBS,
				Card.TEN_CLUBS,
				Card.NINE_CLUBS);
	}

}
