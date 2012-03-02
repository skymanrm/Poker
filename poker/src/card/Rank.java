package card;

public enum Rank{ 
	
	ACE(12,"Ace", "Aces"),
    KING(11,"King", "Kings"), 
    QUEEN(10,"Queen", "Queens"), 
    JACK(9,"Jack", "Jacks"), 
    TEN(8,"Ten", "Tens"), 
    NINE(7,"Nine", "Nines"), 
    EIGHT(6,"Eight", "Eights"),
    SEVEN(5,"Seven", "Sevens"),
    SIX(4,"Six", "Sixes"),
    FIVE(3,"Five", "Fives"), 
    FOUR(2,"Four", "Fours"), 
    THREE(1,"Three", "Threes"), 
    TWO(0,"Two", "Twos");
	
	public final int rankIndex;
	public final String singularName;
	public final String pluralName;
	
	Rank(int rankIndex, String singularName, String pluralName){
		this.rankIndex = rankIndex;
		this.singularName = singularName;
		this.pluralName = pluralName;
	}
}
