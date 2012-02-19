package round;

import card.Hand;

public interface RoundManager {
	public Round<?> getRoundForIndex(Hand hand, int index);
}
