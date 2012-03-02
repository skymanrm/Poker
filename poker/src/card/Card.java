package card;

import java.util.Collections;
import java.util.Stack;

public class Card implements Comparable<Card>{
    
    private final Rank rank;
    private final Suit suit;
    private Visibility visibility;
    
    public Card(Rank rank, Suit suit) {
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
		return rank.rankIndex - other.rank.rankIndex;
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
		return rank.shortName+suit.shortName;
	}
}
