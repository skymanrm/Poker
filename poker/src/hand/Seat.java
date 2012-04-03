package hand;

public class Seat {
	
	private final short index;
	private boolean occupied;
	private Player player;
	
	public static Seat emptySeat(short index){
		return new Seat(index);
	}
	
	public Seat(short index){
		this.index = index;
		occupied = false;
		player = null;
	}

	public short getIndex() {
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
}
