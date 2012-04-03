package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import card.Card;
import card.HandRanking;
import card.PlayerHand;

public class FullHouseTest {

	@Test
	public void testFullHouse() {
		PlayerHand ph = new PlayerHand();
		List<Card> cards = new ArrayList<Card>();
		cards.add(Card.ACE_SPADES);
		cards.add(Card.ACE_DIAMONDS);
		cards.add(Card.ACE_CLUBS);
		cards.add(Card.TEN_HEARTS);
		cards.add(Card.TEN_SPADES);
		ph.getBestHand(cards, new ArrayList<Card>());
		assertEquals("Result",HandRanking.FULL_HOUSE,ph.getHandRanking());
	}

}
