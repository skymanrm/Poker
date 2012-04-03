package action;

public class BetAction extends Action {

	private final BetType betType;
	private final long amount;
	
	public BetAction(long time, BetType betType, long amount) {
		super(time);
		this.betType = betType;
		this.amount = amount;
	}

	public BetType getBetType() {
		return betType;
	}

	public long getAmount() {
		return amount;
	}

}
