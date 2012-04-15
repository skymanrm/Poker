package test_ranking;

import org.junit.Test;

import card.Card;
import card.HandRanking;

public class FourOfAKindTest {

	@Test
	public void testFourOfAKind2() {
		AllHandRankingTests.testCards(HandRanking.FOUR_OF_A_KIND, 
				Card.ACE_CLUBS,
				Card.ACE_SPADES,
				Card.TEN_CLUBS,
				Card.TEN_DIAMONDS,
				Card.TEN_HEARTS,
				Card.TEN_SPADES);
	}
}
