package card;

public enum Rank{ 
	
	ACE(12,"Ace", "Aces","A"),
    KING(11,"King", "Kings","K"), 
    QUEEN(10,"Queen", "Queens","Q"), 
    JACK(9,"Jack", "Jacks","J"), 
    TEN(8,"Ten", "Tens","10"), 
    NINE(7,"Nine", "Nines","9"), 
    EIGHT(6,"Eight", "Eights","8"),
    SEVEN(5,"Seven", "Sevens","7"),
    SIX(4,"Six", "Sixes","6"),
    FIVE(3,"Five", "Fives","5"), 
    FOUR(2,"Four", "Fours","4"), 
    THREE(1,"Three", "Threes","3"), 
    TWO(0,"Two", "Twos","2");
	
	public final int rankIndex;
	public final String singularName;
	public final String pluralName;
	public final String shortName;
	Rank(int rankIndex, String singularName, String pluralName, String shortName){
		this.rankIndex = rankIndex;
		this.singularName = singularName;
		this.pluralName = pluralName;
		this.shortName = shortName;
	}
}
