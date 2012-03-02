package card;

public enum Suit { 
	CLUBS("C"), 
	DIAMONDS("D"), 
	HEARTS("H"), 
	SPADES("S"); 
	
	public final String shortName;
	
	Suit(String shortName){
		this.shortName = shortName;
	}
}
