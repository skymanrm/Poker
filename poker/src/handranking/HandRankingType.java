package handranking;

public enum HandRankingType {
	HIGHCARD(0), 
	PAIR(1), 
	TWOPAIR(2), 
	TRIPS(3), 
	STRAIGHT(4),
    FLUSH(5), 
    FULLHOUSE(6), 
    QUADS(7), 
    STRAIGHTFLUSH(8), 
    ROYALFLUSH(9);
	
	public final int rankIndex;
	
	HandRankingType(int rankIndex){
		this.rankIndex = rankIndex;
	}
}
