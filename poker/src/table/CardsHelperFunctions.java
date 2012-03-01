package table;

import java.util.ArrayList;
import java.util.List;

import card.Card;
import card.Rank;
import card.Suit;

public class CardsHelperFunctions {

	//passed
	public static List<Card> getRoyalFlush(){
		List<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Rank.ACE,Suit.SPADES));
		cards.add(new Card(Rank.KING,Suit.SPADES));
		cards.add(new Card(Rank.QUEEN,Suit.SPADES));
		cards.add(new Card(Rank.JACK,Suit.SPADES));
		cards.add(new Card(Rank.TEN,Suit.SPADES));
		return cards;
	}
	
	//passed
	public static List<Card> getRoyalFlush2(){
		List<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Rank.KING,Suit.SPADES));
		cards.add(new Card(Rank.ACE,Suit.SPADES));
		cards.add(new Card(Rank.QUEEN,Suit.SPADES));
		cards.add(new Card(Rank.JACK,Suit.SPADES));
		cards.add(new Card(Rank.TEN,Suit.SPADES));
		return cards;
	}
	
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
}
