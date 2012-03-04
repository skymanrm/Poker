package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import handranking.HandRankingType;

import org.junit.Test;

import table.FormedHand;

import card.Card;

public class HandRankingTest {

	private List<Card> cards;
	private FormedHand fh;
	private FormedHand fh2;
	
	@Test
	public void testStraightFlush() {
		cards = HandTester.formatCards("AS,KS,QS,JS,10S");
		fh = new FormedHand(cards);
		assertEquals("Result", fh.getType(), HandRankingType.STRAIGHT_FLUSH);
		
		cards = HandTester.formatCards("KS,AS,QS,JS,10S");
		fh = new FormedHand(cards);
		assertEquals("Result", fh.getType(), HandRankingType.STRAIGHT_FLUSH);
		
	}
	
	@Test
	public void testQuads() {
		cards = HandTester.formatCards("KS,KC,KH,KD,AH");
		fh = new FormedHand(cards);
		assertEquals("Result", fh.getType(), HandRankingType.QUADS);
	
		cards = HandTester.formatCards("KS,KC,JH,TH,KH,KD,AH");
		fh = new FormedHand(cards);
		assertEquals("Result", fh.getType(), HandRankingType.QUADS);
	}

	@Test
	public void testFullHouse() {
		cards = HandTester.formatCards("KS,KC,KH,AD,AH");
		fh = new FormedHand(cards);
		assertEquals("Result", fh.getType(), HandRankingType.FULL_HOUSE);
	
		cards = HandTester.formatCards("JS,JC,JH,AH,KH,KD,AD");
		fh = new FormedHand(cards);
		assertEquals("Result", fh.getType(), HandRankingType.FULL_HOUSE);
	}
	
	@Test
	public void QuadsBeatsFullHouse() {
		cards = HandTester.formatCards("KS,KC,KH,KD,AH");
		fh = new FormedHand(cards);
	
		cards = HandTester.formatCards("JS,JC,JH,AH,KH,KD,AD");
		fh2 = new FormedHand(cards);
		
		List<FormedHand> list = new ArrayList<FormedHand>();
		list.add(fh);
		list.add(fh2);
		
		assertEquals("Result", Collections.max(list), fh);
	}
	
	@Test
	public void testingComparison() {
		List<FormedHand> list = new ArrayList<FormedHand>();
		cards = HandTester.formatCards("KS,KC,KH,KD,AH");
		fh = new FormedHand(cards);
		list.add(fh);
		
		cards = HandTester.formatCards("KS,KC,JH,KH,KD");
		list.add(new FormedHand(cards));
		
		assertEquals("Result", Collections.max(list), fh);
	}
	
	@Test
	public void testingTie() {
		cards = HandTester.formatCards("KS,KC,KH,KD,AH");
		fh = new FormedHand(cards);
		
		cards = HandTester.formatCards("KS,KC,AH,KH,KD");
		fh2 = new FormedHand(cards);
		
		int compare = fh.compareTo(fh2);
		assertEquals("Result", compare, 0);
	}
}
