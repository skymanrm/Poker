package card;

import java.util.Collections;
import java.util.Stack;

public class Card implements Comparable<Card>{
	
    public enum Rank { DEUCE, THREE, FOUR, FIVE, SIX,
        SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE }

    public enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES }
    
    private final Rank rank;
    private final Suit suit;
    private Visibility visibility;
    
    protected Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
        this.setVisibility(Visibility.PRIVATE);
    }

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public boolean hasSameSuit(Suit suit){
		return this.suit == suit;
	}

	@Override
	public int compareTo(Card other) {
		int hash = rank.hashCode();
		int otherHash = other.rank.hashCode();
		int difference = hash - otherHash;
		
		if(difference !=0){
			return difference;
		}
		return 0;
	}

    public static Stack<Card> newDeck() {
		Stack<Card> newDeck = new Stack<Card>();
		
		for (Suit suit : Suit.values())
            for (Rank rank : Rank.values())
                newDeck.push(new Card(rank, suit));
		
        Collections.shuffle(newDeck);
        return newDeck;
    }

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
	
	public String toString(){
		return rank.toString()+" "+suit.toString()+" "+visibility.toString();
	}
}
