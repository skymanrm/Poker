package test_ranking;

import org.junit.Test;

import card.Card;
import card.HandRanking;

public class StraightTest {

	@Test
	public void testStraight() {
		AllHandRankingTests.testCards(HandRanking.STRAIGHT, 
				Card.ACE_CLUBS,
				Card.KING_CLUBS,
				Card.JACK_CLUBS,
				Card.TEN_CLUBS,
				Card.QUEEN_DIAMONDS);
	}

}
