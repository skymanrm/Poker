package hand;

import java.util.ArrayList;
import java.util.List;

public class Table {
	private List<Seat> seats;
	private Seat buttonSeat;
	private int maxSeats;
	
	public Table(int maxSeats){
		this.maxSeats = maxSeats;
		seats = new ArrayList<Seat>();
		for(int i = 0; i < maxSeats;i++){
			seats.add(Seat.emptySeat(i));
		}
	}
	
	public Hand newHand(){
		buttonSeat = getNextButtonSeat();
		if(buttonSeat != null){
			List<Player> players = getOccupiedSeats();
			if(players.size()>1)
				return new Hand(players);
		}
		return null;
	}
	
	public void addPlayer(Player player, int index){
		Seat seat = seats.get(index);
		if(seat.isOccupied()){
			throw new IllegalArgumentException("Adding a player to an occupied seat");
		}
		seat.setPlayer(player);
	}
	
	public void removePlayer(int index){
		Seat seat = seats.get(index);
		if(!seat.isOccupied()){
			throw new IllegalArgumentException("Removing a player from an unoccupied seat");
		}
		seat.removePlayer();
	}
	
	private Seat getNextButtonSeat(){
		int index = (buttonSeat == null) ? -1 : buttonSeat.getIndex();
		for(int i =0 ; i<maxSeats; i++){
			index = (index + 1) % maxSeats;
			Seat seat = seats.get(index);
			if(seat.isOccupied() && seat != buttonSeat)
				return seat;
		}
		return null;
	}
	
	private List<Player> getOccupiedSeats(){
		List<Player> players = new ArrayList<Player>();
		int index = buttonSeat.getIndex();
		for(int i =0 ; i<maxSeats; i++){
			Seat seat = seats.get(index);
			if(seat.isOccupied())
				players.add(seat.getPlayer());
			index = (index + 1) % maxSeats;
		}
		return players;
	}
	
	public Seat getButtonSeat(){
		return buttonSeat;
	}
	
	public Seat getSeat(int index){
		return seats.get(index);
	}
}
