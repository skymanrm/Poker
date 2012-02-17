package card;

public class TablePlayer implements Comparable<TablePlayer>{
	
	private final Player player;
	private final Table table;
	
	private int tableBuyin;
	private int absoluteSeat;
	private TableStatus tableStatus;
	
	public TablePlayer(Player player,Table table, int tableBankroll, int absoluteSeat, TableStatus tableStatus){
		this.player = player;
		this.table = table;
		this.setAbsoluteSeat(absoluteSeat);
		this.setTableStatus(tableStatus);
		this.setTableBankroll(tableBankroll);
	}

	public int getTableBankroll() {
		return tableBuyin;
	}

	public void increaseTableBankroll(int amount){
		tableBuyin+=amount;
	}
	
	public void decreaseTableBankroll(int amount){
		if(amount > tableBuyin){
			throw new IllegalArgumentException("Trying to subtract more from a player than he has at the table");
		}
		tableBuyin-=amount;
	}
	
	//TODO need to change for rebuys
	public void setTableBankroll(int tableBankroll) {
		if(tableBankroll > player.getBankroll() || tableBankroll < 0){
			throw new IllegalArgumentException("Table Buyin Amount is Invalid");
		}
		this.player.decreaseBankroll(tableBankroll);
		this.tableBuyin = tableBankroll;
	}

	public int getAbsoluteSeat() {
		return absoluteSeat;
	}

	public void setAbsoluteSeat(int absoluteSeat) {
		for(TablePlayer tablePlayer : table.getTablePlayers()){
			if(tablePlayer.absoluteSeat == absoluteSeat){
				throw new IllegalArgumentException("Seated in Table Seat Already Occupied");
			}
		}
		this.absoluteSeat = absoluteSeat;
	}

	public TableStatus getTableStatus() {
		return tableStatus;
	}

	public void setTableStatus(TableStatus tableStatus) {
		this.tableStatus = tableStatus;
	}

	public Player getPlayer() {
		return player;
	}

	public String toString(){
		String playerString = this.player.toString();
		String tablePlayerString = "Absolute Seat: "
		+this.absoluteSeat+" "+this.tableStatus.toString();
		return playerString+"\n"+tablePlayerString;
	}

	public Table getTable() {
		return table;
	}

	@Override
	public int compareTo(TablePlayer o) {
		if(absoluteSeat > o.absoluteSeat){
			return 1;
		}
		else{
			return -1;
		}
	}
}
