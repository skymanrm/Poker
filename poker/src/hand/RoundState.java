package hand;

public class RoundState {

	private boolean hasActed;
	private long amountCommittedToRound;
	
	public RoundState(){
		this.setHasActed(false);
		this.amountCommittedToRound = 0;
	}

	public boolean hasActed() {
		return hasActed;
	}

	public void setHasActed(boolean hasActed) {
		this.hasActed = hasActed;
	}

	public long getAmountCommittedToRound() {
		return amountCommittedToRound;
	}

	public void increaseAmountCommittedToRound(long amount){
		amountCommittedToRound+=amount;
	}
}
