package round;

import table.Hand;

public interface RoundManager {
	public Round<?> getRoundForIndex(Hand hand, int index);
}
