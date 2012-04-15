package test_ranking;

import org.junit.Test;

import card.Card;
import card.HandRanking;

public class PairTest {

	@Test
	public void testPair() {
		AllHandRankingTests.testCards(HandRanking.PAIR, 
				Card.ACE_CLUBS,
				Card.ACE_DIAMONDS,
				Card.JACK_CLUBS,
				Card.TEN_CLUBS,
				Card.NINE_CLUBS);
	}

}
