package table;

import java.util.ArrayList;
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
	private boolean[] seats;
	
	public Table(int maxSeats, GameType gameType, int smallLimit, int bigLimit){
		tablePlayers = new ArrayList<TablePlayer>();
		buttonSeat = maxSeats-1;
		this.setMaxSeats(maxSeats);
		this.setCurrentGameType(gameType);
		this.setSmallLimit(smallLimit);
		this.setBigLimit(bigLimit);
		initSeats();
	}
	
	public Hand newHand(){
		List<TablePlayer> readyPlayers = this.getReadyTablePlayers();
		if(readyPlayers.size() < 2){
			throw new IllegalArgumentException("At least two players must be ready to start a hand");
		}
		this.moveButton(readyPlayers);
		return new Hand(currentGameType, this, tablePlayers);
	}
	
	private void initSeats(){
		seats = new boolean[maxSeats];
		for(int i = 0; i<maxSeats;i++)
			seats[i] = false;
	}
	
	public int getButtonSeat() {
		return buttonSeat;
	}

	//Can't be called when only 1 person is playing or it will infinitely loop
	public void moveButton(List<TablePlayer> readyPlayers){
		int currentSeat = (buttonSeat+1) % maxSeats;
		while(true){
			for(TablePlayer player : readyPlayers){
				if(player.getAbsoluteSeat() == currentSeat){
					this.buttonSeat = currentSeat;
					return;
				}
			}
			currentSeat++;
			currentSeat %= maxSeats;
		}
	}
	
	public void setButtonSeat(int buttonSeat) {
		this.buttonSeat = buttonSeat;
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
			if(tablePlayer.getTableStatus() == TableStatus.PLAYING){
				playersInHand.add(tablePlayer);
			}
		}
		return playersInHand;
	} 
	
	public void addPlayer(Player player, int tableBankroll, int absoluteSeat, TableStatus tableStatus){
		if(tablePlayers.size() == maxSeats){
			throw new IllegalArgumentException("Adding player when table is full");
		}
		//TODO redundant
		if(absoluteSeat < 0 || absoluteSeat >= maxSeats || seats[absoluteSeat]){
			throw new IllegalArgumentException("Adding player to invalid seat number");
		}
		TablePlayer playerToAdd = new TablePlayer(player,this,tableBankroll,absoluteSeat,tableStatus);
		addPlayerToList(playerToAdd);
		seats[absoluteSeat] = true;
	}
	private void addPlayerToList(TablePlayer player){
		int counter = 0;
		System.out.println(player.getName()+" is getting added");
		for(TablePlayer tablePlayer: tablePlayers){
			if(tablePlayer.getAbsoluteSeat()>player.getAbsoluteSeat()){
				System.out.println(tablePlayer.getName()+" seat is greater");
				tablePlayers.add(counter, player);
				return;
			}
			counter++;
		}
		tablePlayers.add(player);
	}
	public void removePlayer(TablePlayer player){
		if(!tablePlayers.contains(player)){
			throw new IllegalArgumentException("Removing player that is not at table");
		}
		tablePlayers.remove(player);
		seats[player.getAbsoluteSeat()] = false;
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
		returnString+="\nButton Seat: "+this.buttonSeat;
		returnString+="\nLimits: "+this.smallLimit+"/"+this.bigLimit;
		for(TablePlayer player: this.getTablePlayers()){
			returnString+="\n"+player.toString();
		}
		return returnString;
	}
}
