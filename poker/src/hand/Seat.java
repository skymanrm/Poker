package hand;

import java.util.Comparator;

public class Seat {
	
	private final int index;
	private boolean occupied;
	private Player player;
	
	public static Seat emptySeat(int index){
		return new Seat(index);
	}
	
	public Seat(int index){
		this.index = index;
		occupied = false;
		player = null;
	}

	public int getIndex() {
		return index;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
		this.occupied = true;
	}
	
	public void removePlayer(){
		player = null;
		occupied = false;
	}
	
	public static Comparator<Seat> positionComparator(final int buttonPosition, final int maxSeats){
		class PositionComparator implements Comparator<Seat>{
			@Override
			public int compare(Seat arg0, Seat arg1) {
				int index1 = arg0.getIndex() - buttonPosition;
				int index2 = arg1.getIndex() - buttonPosition;
				if(index1 < 0){
					index1 = maxSeats - index1;
				}
				if(index2 < 0){
					index2 = maxSeats - index2;
				}
				return index1-index2;
			}
		}
		return new PositionComparator();
	}
}
