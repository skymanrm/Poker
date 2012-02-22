package player;

public class Player {
	
	private String name;
	private int bankroll;
	private Boolean autoMuck;
	
	public Player(String name){
		this.setName(name);
		this.setBankroll(0);
		this.setAutoMuck(false);
	}
	
	public Player(String name, int bankroll){
		this.name = name;
		this.setBankroll(bankroll);
		this.setAutoMuck(false);
	}

	public Player(Player player){
		this.name = player.name;
		this.bankroll = player.bankroll;
		this.autoMuck = player.autoMuck;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBankroll() {
		return bankroll;
	}

	public void setBankroll(int bankroll) {
		if(bankroll < 0){
			throw new IllegalArgumentException("Player Bankroll set to a negative value");
		}
		else{
			this.bankroll = bankroll;
		}
	}
	
	public void increaseBankroll(int amount){
		bankroll+=amount;
	}
	
	public void decreaseBankroll(int amount){
		if(amount > bankroll){
			throw new IllegalArgumentException("Trying to subtract more from a player than he has");
		}
		bankroll-=amount;
	}
	
	public String toString(){
		return this.name+" "+this.bankroll;
	}

	public Boolean getAutoMuck() {
		return autoMuck;
	}

	public void setAutoMuck(Boolean autoMuck) {
		this.autoMuck = autoMuck;
	}
}
