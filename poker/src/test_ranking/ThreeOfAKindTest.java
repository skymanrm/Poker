package test_ranking;

import org.junit.Test;

import card.Card;
import card.HandRanking;

public class ThreeOfAKindTest {

	@Test
	public void testThreeOfAKind() {
		AllHandRankingTests.testCards(HandRanking.THREE_OF_A_KIND, 
				Card.ACE_CLUBS,
				Card.ACE_DIAMONDS,
				Card.ACE_HEARTS,
				Card.TEN_CLUBS,
				Card.NINE_CLUBS);
	}

}
