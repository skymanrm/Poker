package card;

import java.util.List;

public class DrawAction extends Action {

	private final List<Card> discards;
	
	public DrawAction(long time, Round<DrawAction> round, HandPlayer handPlayer, List<Card> discards) {
		super(time, round, handPlayer);
		this.discards = discards;
	}

	public List<Card> getDiscards() {
		return discards;
	}
}
