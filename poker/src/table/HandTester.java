package table;

import handranking.FormedHand;

import java.util.ArrayList;
import java.util.List;

import card.Card;
import card.Rank;
import card.Suit;

public class HandTester {

	public static void showHandWithList(List<Card> cards){
		FormedHand fh = new FormedHand(cards);
		System.out.println(fh.toString());
	}
	public static List<Card> getRandom(int number){
		List<Card> cards = Card.newDeck();
		return cards.subList(0, number);
	}
	
	@Deprecated
	public static List<Card> getRoyalFlush(){
		List<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Rank.ACE,Suit.SPADES));
		cards.add(new Card(Rank.KING,Suit.SPADES));
		cards.add(new Card(Rank.QUEEN,Suit.SPADES));
		cards.add(new Card(Rank.JACK,Suit.SPADES));
		cards.add(new Card(Rank.TEN,Suit.SPADES));
		return cards;
	}
	
	@Deprecated
	public static List<Card> getRoyalFlush2(){
		List<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Rank.KING,Suit.SPADES));
		cards.add(new Card(Rank.ACE,Suit.SPADES));
		cards.add(new Card(Rank.QUEEN,Suit.SPADES));
		cards.add(new Card(Rank.JACK,Suit.SPADES));
		cards.add(new Card(Rank.TEN,Suit.SPADES));
		return cards;
	}
	
	@Deprecated
	public static List<Card> getRoyalFlush3(){
		List<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Rank.KING,Suit.HEARTS));
		cards.add(new Card(Rank.ACE,Suit.CLUBS));
		cards.add(new Card(Rank.KING,Suit.SPADES));
		cards.add(new Card(Rank.ACE,Suit.SPADES));
		cards.add(new Card(Rank.QUEEN,Suit.SPADES));
		cards.add(new Card(Rank.JACK,Suit.SPADES));
		cards.add(new Card(Rank.TEN,Suit.SPADES));
		return cards;
	}
	
	@Deprecated
	public static List<Card> getStraightFlush(){
		List<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Rank.KING,Suit.SPADES));
		cards.add(new Card(Rank.NINE,Suit.SPADES));
		cards.add(new Card(Rank.QUEEN,Suit.SPADES));
		cards.add(new Card(Rank.JACK,Suit.SPADES));
		cards.add(new Card(Rank.TEN,Suit.SPADES));
		return cards;
	}
	
	@Deprecated
	public static List<Card> getStraightFlush2(){
		List<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Rank.TWO,Suit.SPADES));
		cards.add(new Card(Rank.THREE,Suit.SPADES));
		cards.add(new Card(Rank.FIVE,Suit.SPADES));
		cards.add(new Card(Rank.FOUR,Suit.SPADES));
		cards.add(new Card(Rank.ACE,Suit.SPADES));
		return cards;
	}
}
