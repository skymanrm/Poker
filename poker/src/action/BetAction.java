package action;

import player.HandPlayer;
import round.Round;

public class BetAction extends Action {

	private final int amount;
	private final BetActionType betActionType;
	
	public BetAction(Round<BetAction> round, HandPlayer handPlayer, BetActionType betActionType, int amount) {
		super(round, handPlayer);
		this.betActionType = betActionType;
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public BetActionType getBetActionType() {
		return betActionType;
	}

	public String toString(){
		String s = "BetAction: ";
		s+="\tAmount: "+amount;
		s+="\tType: "+betActionType.toString();
		return s;
	}
}
