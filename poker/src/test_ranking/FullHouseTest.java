package test_ranking;

import org.junit.Test;

import card.Card;
import card.HandRanking;

public class FullHouseTest {

	@Test
	public void testFullHouse() {
		AllHandRankingTests.testCards(HandRanking.FULL_HOUSE, 
				Card.ACE_CLUBS,
				Card.ACE_DIAMONDS,
				Card.ACE_HEARTS,
				Card.TEN_CLUBS,
				Card.TEN_DIAMONDS);
	}

}
