package hand;

public class Player {
	private long bankroll;
	private String name;
	private boolean allIn;
	private boolean hasActed;
	
	public Player(String name, long bankroll) {
		this.bankroll = bankroll;
		this.name = name;
		this.allIn = false;
		this.setHasActed(false);
	}
	
	public long getBankroll() {
		return bankroll;
	}
	
	public void setBankroll(long bankroll) {
		this.bankroll = bankroll;
	}
	
	public void increaseBankroll(long amount){
		this.bankroll += amount;
	}
	
	public void decreaseBankroll(long amount){
		if(amount>bankroll){
			throw new IllegalArgumentException("Removing more than bankroll has");
		}
		this.bankroll -= amount;
	}
	
	public void addToPot(Hand hand, long amount){
		decreaseBankroll(amount);
		hand.addToPot(amount);
		if(bankroll==0)
			setAllIn(true);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public boolean isAllIn() {
		return allIn;
	}

	public void setAllIn(boolean allIn) {
		this.allIn = allIn;
	}

	public boolean hasActed() {
		return hasActed;
	}

	public void setHasActed(boolean hasActed) {
		this.hasActed = hasActed;
	}
}
