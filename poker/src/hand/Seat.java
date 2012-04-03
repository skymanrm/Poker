package hand;

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
}
