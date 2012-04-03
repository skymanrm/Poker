package card;

import java.util.Comparator;

public enum Card{
	
	ACE_SPADES(Rank.ACE,Suit.SPADES),
	TWO_SPADES(Rank.TWO,Suit.SPADES),
	THREE_SPADES(Rank.THREE,Suit.SPADES),
	FOUR_SPADES(Rank.FOUR,Suit.SPADES),
	FIVE_SPADES(Rank.FIVE,Suit.SPADES),
	SIX_SPADES(Rank.SIX,Suit.SPADES),
	SEVEN_SPADES(Rank.SEVEN,Suit.SPADES),
	EIGHT_SPADES(Rank.EIGHT,Suit.SPADES),
	NINE_SPADES(Rank.NINE,Suit.SPADES),
	TEN_SPADES(Rank.TEN,Suit.SPADES),
	JACK_SPADES(Rank.JACK,Suit.SPADES),
	QUEEN_SPADES(Rank.QUEEN,Suit.SPADES),
	KING_SPADES(Rank.KING,Suit.SPADES),
	
	ACE_HEARTS(Rank.ACE,Suit.HEARTS),
	TWO_HEARTS(Rank.TWO,Suit.HEARTS),
	THREE_HEARTS(Rank.THREE,Suit.HEARTS),
	FOUR_HEARTS(Rank.FOUR,Suit.HEARTS),
	FIVE_HEARTS(Rank.FIVE,Suit.HEARTS),
	SIX_HEARTS(Rank.SIX,Suit.HEARTS),
	SEVEN_HEARTS(Rank.SEVEN,Suit.HEARTS),
	EIGHT_HEARTS(Rank.EIGHT,Suit.HEARTS),
	NINE_HEARTS(Rank.NINE,Suit.HEARTS),
	TEN_HEARTS(Rank.TEN,Suit.HEARTS),
	JACK_HEARTS(Rank.JACK,Suit.HEARTS),
	QUEEN_HEARTS(Rank.QUEEN,Suit.HEARTS),
	
	KING_HEARTS(Rank.KING,Suit.HEARTS),
	ACE_DIAMONDS(Rank.ACE,Suit.DIAMONDS),
	TWO_DIAMONDS(Rank.TWO,Suit.DIAMONDS),
	THREE_DIAMONDS(Rank.THREE,Suit.DIAMONDS),
	FOUR_DIAMONDS(Rank.FOUR,Suit.DIAMONDS),
	FIVE_DIAMONDS(Rank.FIVE,Suit.DIAMONDS),
	SIX_DIAMONDS(Rank.SIX,Suit.DIAMONDS),
	SEVEN_DIAMONDS(Rank.SEVEN,Suit.DIAMONDS),
	EIGHT_DIAMONDS(Rank.EIGHT,Suit.DIAMONDS),
	NINE_DIAMONDS(Rank.NINE,Suit.DIAMONDS),
	TEN_DIAMONDS(Rank.TEN,Suit.DIAMONDS),
	JACK_DIAMONDS(Rank.JACK,Suit.DIAMONDS),
	QUEEN_DIAMONDS(Rank.QUEEN,Suit.DIAMONDS),
	KING_DIAMONDS(Rank.KING,Suit.DIAMONDS),
	
	ACE_CLUBS(Rank.ACE,Suit.CLUBS),
	TWO_CLUBS(Rank.TWO,Suit.CLUBS),
	THREE_CLUBS(Rank.THREE,Suit.CLUBS),
	FOUR_CLUBS(Rank.FOUR,Suit.CLUBS),
	FIVE_CLUBS(Rank.FIVE,Suit.CLUBS),
	SIX_CLUBS(Rank.SIX,Suit.CLUBS),
	SEVEN_CLUBS(Rank.SEVEN,Suit.CLUBS),
	EIGHT_CLUBS(Rank.EIGHT,Suit.CLUBS),
	NINE_CLUBS(Rank.NINE,Suit.CLUBS),
	TEN_CLUBS(Rank.TEN,Suit.CLUBS),
	JACK_CLUBS(Rank.JACK,Suit.CLUBS),
	QUEEN_CLUBS(Rank.QUEEN,Suit.CLUBS),
	KING_CLUBS(Rank.KING,Suit.CLUBS);
	
	//Should be made lazy loading accessors
	public static final Comparator<Card> highComparator = new HighComparator();
	public static final Comparator<Card> lowComparator = new LowComparator();
	
	private final Rank rank;
	private final Suit suit;
	
	private Card(Rank rank, Suit suit){
		this.rank = rank;
		this.suit = suit;
	}
	
	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public String toString(){
		return rank.getShortName()+suit.getShortName();
	}
	
	public String formalName(){
		return rank.getSingularName()+" of "+suit.getPluralName();
	}
	
	private static class HighComparator implements Comparator<Card>{

		@Override
		public int compare(Card arg0, Card arg1) {
			return arg0.rank.getRankIndex() - arg1.rank.getRankIndex();
		}
		
	}
	
	private static class LowComparator implements Comparator<Card>{
		
		@Override
		public int compare(Card arg0, Card arg1) {
			return arg1.rank.getRankIndex() - arg0.rank.getRankIndex();
		}

	}
}
