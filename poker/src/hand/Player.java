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
		this.bankroll -= amount;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
