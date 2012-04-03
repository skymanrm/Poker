package hand;

public class Player {
	private long bankroll;
	private String name;
	private boolean allIn;
	
	public Player(String name, long bankroll) {
		this.bankroll = bankroll;
		this.name = name;
		this.allIn = false;
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
		this.bankroll -= amount;
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
}
