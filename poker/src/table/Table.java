package table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import player.Player;
import player.TablePlayer;

public class Table {
	
	private final List<TablePlayer> tablePlayers;
	private int buttonSeat;
	private int maxSeats;
	private GameType currentGameType;
	private int smallLimit;
	private int bigLimit;
	private List<Boolean> seatHasPlayer;
	
	public Table(int maxSeats, GameType gameType, int smallLimit, int bigLimit){
		tablePlayers = new ArrayList<TablePlayer>();
		this.setMaxSeats(maxSeats);
		this.setCurrentGameType(gameType);
		this.setSmallLimit(smallLimit);
		this.setBigLimit(bigLimit);
		this.buttonSeat = -1;
		this.seatHasPlayer = new ArrayList<Boolean>();
		for(int i = 0; i<maxSeats;i++){
			seatHasPlayer.add(false);
		}
	}
	
	public Hand newHand(){
		List<TablePlayer> readyPlayers = this.getReadyTablePlayers();
		if(readyPlayers.size() < 2){
			System.out.println("Table Breaking, Not Enough Players");
			return null;
		}
		moveButton(readyPlayers);
		return new Hand(currentGameType, this, readyPlayers);
	}

	public void moveButton(List<TablePlayer> readyPlayers){
		if(buttonSeat == -1){
			buttonSeat = readyPlayers.get(0).getAbsoluteSeat();
			return;
		}
		else{
			while(true){
				buttonSeat = (buttonSeat +1)%maxSeats;
				if(seatHasPlayer.get(buttonSeat)){
					return;
				}
			}
		}
	}

	public int getMaxSeats() {
		return maxSeats;
	}

	public void setMaxSeats(int maxSeats) {
		if(maxSeats > 10 || maxSeats < 2){
			throw new IllegalArgumentException("Number of Table Seats can only be between 2 and 10");
		}
		this.maxSeats = maxSeats;
	}

	public GameType getCurrentGameType() {
		return currentGameType;
	}

	public void setCurrentGameType(GameType currentGameType) {
		this.currentGameType = currentGameType;
	}

	public int getSmallLimit() {
		return smallLimit;
	}

	public void setSmallLimit(int smallLimit) {
		this.smallLimit = smallLimit;
	}

	public int getBigLimit() {
		return bigLimit;
	}

	public void setBigLimit(int bigLimit) {
		this.bigLimit = bigLimit;
	}

	public List<TablePlayer> getTablePlayers() {
		return tablePlayers;
	}

	public List<TablePlayer> getReadyTablePlayers(){
		List<TablePlayer> playersInHand = new ArrayList<TablePlayer>();
		for(TablePlayer tablePlayer: getTablePlayers()){
			if(tablePlayer.getTableBankroll()==0){
				tablePlayer.setTableStatus(TableStatus.SITTING_OUT);
			}
			else if(tablePlayer.getTableStatus() == TableStatus.PLAYING){
				playersInHand.add(tablePlayer);
			}
		}
		return playersInHand;
	} 
	
	public void addPlayer(Player player, int tableBankroll, int absoluteSeat, TableStatus tableStatus){
		if(!seatHasPlayer.get(absoluteSeat)){
			TablePlayer playerToAdd = new TablePlayer(player,this,tableBankroll,absoluteSeat,tableStatus);
			tablePlayers.add(playerToAdd);
			Collections.sort(tablePlayers);
			seatHasPlayer.set(absoluteSeat, true);
		}
		else{
			throw new IllegalArgumentException("Seat is occupied or invalid");
		}
	}

	public void removePlayer(TablePlayer player){
		tablePlayers.remove(player);
		seatHasPlayer.set(player.getAbsoluteSeat(), false);
		cashOut(player);
	}
	
	private void cashOut(TablePlayer player){
		int amount = player.getTableBankroll();
		player.getPlayer().increaseBankroll(amount);
		player.decreaseTableBankroll(amount);
	}
	
	public String toString(){
		String returnString = this.currentGameType.toString();
		returnString+="\nMax Seats: "+this.maxSeats;
		returnString+="\nLimits: "+this.smallLimit+"/"+this.bigLimit;
		for(TablePlayer player: this.getTablePlayers()){
			returnString+="\n"+player.toString();
		}
		return returnString;
	}
	
	public int getButtonSeat() {
		return buttonSeat;
	}

	public void setButtonSeat(int buttonSeat) {
		this.buttonSeat = buttonSeat;
	}
}
