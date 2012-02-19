package round;

import java.util.List;

import player.HandPlayer;


import card.Visibility;


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
