package test_ranking;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import card.Card;
import card.HandRanking;
import card.PlayerHand;

@RunWith(Suite.class)
@SuiteClasses({StraightFlushTest.class,
	FourOfAKindTest.class,
	FullHouseTest.class,
	FlushTest.class,
	StraightTest.class,
	ThreeOfAKindTest.class,
	TwoPairTest.class,
	PairTest.class,
	HighCardTest.class})
public class AllHandRankingTests {
	
	public static List<Card> getCards(Card...cards){
		return Arrays.asList(cards);
	}
	
	public static void testCards(HandRanking handRanking, Card...cards){
		PlayerHand ph = new PlayerHand(Arrays.asList(cards), new ArrayList<Card>());
		assertEquals("Result",handRanking,ph.getHandRanking());
	}
}
