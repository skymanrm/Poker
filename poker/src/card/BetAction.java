package card;

public class BetAction extends Action {

	private final int amount;
	private final BetActionType betActionType;
	
	public BetAction(long time, Round<BetAction> round, HandPlayer handPlayer, BetActionType betActionType, int amount) {
		super(time, round, handPlayer);
		this.betActionType = betActionType;
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public BetActionType getBetActionType() {
		return betActionType;
	}

}
