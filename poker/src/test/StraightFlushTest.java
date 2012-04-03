package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import card.Card;
import card.HandRanking;
import card.PlayerHand;

public class StraightFlushTest {

	@Test
	public void testRoyalFlush() {
		PlayerHand ph = new PlayerHand();
		List<Card> cards = new ArrayList<Card>();
		cards.add(Card.ACE_SPADES);
		cards.add(Card.KING_SPADES);
		cards.add(Card.QUEEN_SPADES);
		cards.add(Card.JACK_SPADES);
		cards.add(Card.TEN_SPADES);
		ph.getBestHand(cards, new ArrayList<Card>());
		assertEquals("Result",HandRanking.STRAIGHT_FLUSH,ph.getHandRanking());
	}

	@Test
	public void testStraightFlush() {
		PlayerHand ph = new PlayerHand();
		List<Card> cards = new ArrayList<Card>();
		cards.add(Card.NINE_CLUBS);
		cards.add(Card.EIGHT_CLUBS);
		cards.add(Card.SEVEN_CLUBS);
		cards.add(Card.SIX_CLUBS);
		cards.add(Card.FIVE_CLUBS);
		ph.getBestHand(cards, new ArrayList<Card>());
		assertEquals("Result",HandRanking.STRAIGHT_FLUSH,ph.getHandRanking());
	}
	
	@Test
	public void testStraightFlush2() {
		PlayerHand ph = new PlayerHand();
		List<Card> cards = new ArrayList<Card>();
		cards.add(Card.FIVE_CLUBS);
		cards.add(Card.EIGHT_CLUBS);
		cards.add(Card.SEVEN_CLUBS);
		cards.add(Card.SIX_CLUBS);
		cards.add(Card.NINE_CLUBS);
		ph.getBestHand(cards, new ArrayList<Card>());
		assertEquals("Result",HandRanking.STRAIGHT_FLUSH,ph.getHandRanking());
	}
	
	@Test
	public void testStraightFlush3() {
		PlayerHand ph = new PlayerHand();
		List<Card> cards = new ArrayList<Card>();
		cards.add(Card.TEN_CLUBS);
		cards.add(Card.FIVE_CLUBS);
		cards.add(Card.EIGHT_CLUBS);
		cards.add(Card.SEVEN_CLUBS);
		cards.add(Card.SEVEN_DIAMONDS);
		cards.add(Card.SIX_CLUBS);
		cards.add(Card.NINE_CLUBS);
		ph.getBestHand(cards, new ArrayList<Card>());
		assertEquals("Result",HandRanking.STRAIGHT_FLUSH,ph.getHandRanking());
	}
	
	@Test
	public void testStraightFlushWheel() {
		PlayerHand ph = new PlayerHand();
		List<Card> cards = new ArrayList<Card>();
		cards.add(Card.FIVE_CLUBS);
		cards.add(Card.FOUR_CLUBS);
		cards.add(Card.THREE_CLUBS);
		cards.add(Card.TWO_CLUBS);
		cards.add(Card.ACE_CLUBS);
		ph.getBestHand(cards, new ArrayList<Card>());
		assertEquals("Result",HandRanking.STRAIGHT_FLUSH,ph.getHandRanking());
	}
}
