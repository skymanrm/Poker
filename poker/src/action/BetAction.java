package action;

import hand.Player;

public class BetAction implements Action {

	private final BetType betType;
	private final long amount;
	private final Player player;
	private final long time;
	
	public BetAction(Player player, long time, BetType betType, long amount) {
		this.player = player;
		this.time = time;
		this.betType = betType;
		this.amount = amount;
	}

	public BetType getBetType() {
		return betType;
	}

	public long getAmount() {
		return amount;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public long getTime() {
		return time;
	}

}
