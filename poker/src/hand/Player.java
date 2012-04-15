package hand;


public class Player {

	private long bankroll;
	private String name;

	public Player(String name, long bankroll) {
		this.bankroll = bankroll;
		this.name = name;
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
	
	public void addToPot(Hand hand, RoundState roundState, long amount){
		decreaseBankroll(amount);
		hand.addToPot(amount);
		roundState.increaseAmountCommittedToRound(amount);
//		if(bankroll==0)
//			setAllIn(true);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Player [bankroll=" + bankroll + ", name=" + name + "]";
	}
}
