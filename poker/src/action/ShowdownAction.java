package action;

import player.HandPlayer;
import round.Round;

public class ShowdownAction extends Action {

	private final boolean muck;
	
	public ShowdownAction(long time, Round<ShowdownAction> round, HandPlayer handPlayer, boolean muck) {
		super(time, round, handPlayer);
		this.muck = muck;
	}

	public boolean isMuck() {
		return muck;
	}

}
