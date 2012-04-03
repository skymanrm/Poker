package test_ranking;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import card.Card;
import card.HandRanking;
import card.PlayerHand;

public class ThreeOfAKindTest {

	@Test
	public void testThreeOfAKind() {
		PlayerHand ph = new PlayerHand();
		List<Card> cards = new ArrayList<Card>();
		cards.add(Card.ACE_SPADES);
		cards.add(Card.ACE_DIAMONDS);
		cards.add(Card.ACE_CLUBS);
		cards.add(Card.SIX_CLUBS);
		cards.add(Card.TEN_SPADES);
		ph.getBestHand(cards, new ArrayList<Card>());
		assertEquals("Result",HandRanking.THREE_OF_A_KIND,ph.getHandRanking());
	}

}
