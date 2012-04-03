package card;

public enum Suit { 
	CLUBS("Clubs","C"), 
	DIAMONDS("Diamonds","D"), 
	HEARTS("Hearts","H"), 
	SPADES("Spades","S"); 
	
	private final String pluralName;
	private final String shortName;
	
	private Suit(String pluralName, String shortName){
		this.pluralName = pluralName;
		this.shortName = shortName;
	}

	public String getPluralName() {
		return pluralName;
	}

	public String getShortName() {
		return shortName;
	}
}
