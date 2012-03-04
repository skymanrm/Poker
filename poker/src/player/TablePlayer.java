package player;

import table.Table;
import table.TableStatus;

public class TablePlayer{
	
	private final Table table;
	private final Player player;
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
		player.decreaseBankroll(tableBankroll);
		tableBuyin = tableBankroll;
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

	public String toString(){
		return getName()+" TableBankroll: "+tableBuyin;
	}

	public Table getTable() {
		return table;
	}

	public Player getPlayer() {
		return player;
	}
	
	public String getName(){
		return player.getName();
	}
}
