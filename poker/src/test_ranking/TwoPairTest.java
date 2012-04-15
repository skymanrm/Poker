package test_ranking;

import org.junit.Test;

import card.Card;
import card.HandRanking;

public class TwoPairTest {

	@Test
	public void testTwoPair() {
		AllHandRankingTests.testCards(HandRanking.TWO_PAIR, 
				Card.ACE_CLUBS,
				Card.ACE_DIAMONDS,
				Card.TEN_DIAMONDS,
				Card.TEN_CLUBS,
				Card.NINE_CLUBS);
	}

}
