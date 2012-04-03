package hand;

import java.util.ArrayList;
import java.util.List;

public class Table {
	private List<Seat> seats;
	private Seat buttonSeat;
	
	public Table(short maxSeats){
		seats = new ArrayList<Seat>();
		for(short i = 0; i < maxSeats;i++){
			seats.add(Seat.emptySeat(i));
		}
	}
	
	public void newHand(){
		
	}
	
	public void addPlayer(Player player, short index){
		Seat seat = seats.get(index);
		if(seat.isOccupied()){
			throw new IllegalArgumentException("Adding a player to an occupied seat");
		}
		seat.setPlayer(player);
	}
	
	public void removePlayer(short index){
		Seat seat = seats.get(index);
		if(!seat.isOccupied()){
			throw new IllegalArgumentException("Removing a player from an unoccupied seat");
		}
		seat.removePlayer();
	}
	
	private List<Seat> getSeatsWithPlayers(){
		return null;
	}
}
