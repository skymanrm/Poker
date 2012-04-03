package hand;

import java.util.ArrayList;
import java.util.Collections;
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
	
	public void newHand(){
		buttonSeat = getNextButtonSeat();
		if(buttonSeat != null){
			new Hand(getOccupiedSeats());
		}
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
		List<Seat> occupiedSeats = new ArrayList<Seat>(seats);
		for(Seat seat: seats){
			if(seat.isOccupied())
				occupiedSeats.add(seat);
		}
		Collections.sort(occupiedSeats, Seat.positionComparator(buttonSeat.getIndex(), maxSeats));
		List<Player> players = new ArrayList<Player>();
		for(Seat seat: occupiedSeats)
			players.add(seat.getPlayer());
		return players;
	}
	
	public Seat getButtonSeat(){
		return buttonSeat;
	}
	
	public Seat getSeat(int index){
		return seats.get(index);
	}
}
