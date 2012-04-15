package test_ranking;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import card.Card;
import card.PlayerHand;

public class ComparingHandsTest {

	@Test
	public void testTwoFlushes() {
		List<Card> cards1 = AllHandRankingTests.getCards( 
				Card.ACE_CLUBS,
				Card.KING_CLUBS,
				Card.JACK_CLUBS,
				Card.TEN_CLUBS,
				Card.NINE_CLUBS);
		PlayerHand hand1 = new PlayerHand(cards1,new ArrayList<Card>());
		List<Card> cards2 = AllHandRankingTests.getCards( 
				Card.ACE_CLUBS,
				Card.KING_CLUBS,
				Card.JACK_CLUBS,
				Card.TEN_CLUBS,
				Card.TWO_CLUBS);
		PlayerHand hand2 = new PlayerHand(cards2,new ArrayList<Card>());
		int compare = hand1.compareTo(hand2);
		boolean isGreater = compare>0;
		assertEquals("Results",true,isGreater);
	}
	
	@Test
	public void testFlushVsStraight() {
		List<Card> cards1 = AllHandRankingTests.getCards( 
				Card.ACE_CLUBS,
				Card.KING_CLUBS,
				Card.JACK_CLUBS,
				Card.TEN_CLUBS,
				Card.NINE_CLUBS);
		PlayerHand hand1 = new PlayerHand(cards1,new ArrayList<Card>());
		List<Card> cards2 = AllHandRankingTests.getCards( 
				Card.ACE_CLUBS,
				Card.KING_CLUBS,
				Card.JACK_CLUBS,
				Card.TEN_CLUBS,
				Card.QUEEN_DIAMONDS);
		PlayerHand hand2 = new PlayerHand(cards2,new ArrayList<Card>());
		int compare = hand1.compareTo(hand2);
		boolean isGreater = compare>0;
		assertEquals("Results",true,isGreater);
	}
}
