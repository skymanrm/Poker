package card;

public abstract class RoundManager {

	private final Hand hand;
	
	public RoundManager(Hand hand){
		this.hand = hand;
	}
	
	public abstract Round<?> getDealingRoundForIndex(int index);

	public Hand getHand() {
		return hand;
	}
}
