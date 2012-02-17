package card;

import java.util.List;

public class DealingRound extends BettingRound {

	private final List<Visibility> visibilities;
	
	public DealingRound(int startingPosition,List<HandPlayer> handPlayer, List<Visibility> visibilities) {
		super(startingPosition, handPlayer);
		this.visibilities = visibilities;
	}

	public List<Visibility> getVisibilities() {
		return visibilities;
	}

}
