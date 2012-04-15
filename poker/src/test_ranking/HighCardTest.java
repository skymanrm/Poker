package test_ranking;

import org.junit.Test;

import card.Card;
import card.HandRanking;

public class HighCardTest {

	@Test
	public void testHighCard() {
		AllHandRankingTests.testCards(HandRanking.HIGH_CARD, 
				Card.ACE_CLUBS,
				Card.KING_CLUBS,
				Card.JACK_CLUBS,
				Card.TEN_CLUBS,
				Card.NINE_DIAMONDS);
	}

}
